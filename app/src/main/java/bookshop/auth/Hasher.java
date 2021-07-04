package bookshop.auth;

import java.security.SecureRandom;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.SecretKeyFactory;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class Hasher {

    public static final String PBKDF2_ALGORITHM = "PBKDF2WithHmacSHA1";

    /**
     * The application salt value.
     *
     * @var byte
     */
    protected static byte[] salt;

    // The following constants may be changed without breaking existing hashes.
    public static final int SALT_BYTES = 24;
    public static final int HASH_BYTES = 24;
    public static final int PBKDF2_ITERATIONS = 1000;

    public static final int ITERATION_INDEX = 0;
    public static final int SALT_INDEX = 1;
    public static final int PBKDF2_INDEX = 2;

    /**
     * Set a applcation specific salt value.
     *
     * @param byte value
     *
     * @return void
     */
    public static void setSaltValule(byte[] value) {
        Hasher.salt = value;
    }

    /**
     * Generate a default salt value.
     *
     * @return void
     */
    public static void generateSalt() {
        if (Hasher.salt != null) {
            return;
        }

        SecureRandom random = new SecureRandom();

        Hasher.salt = new byte[SALT_BYTES];

        random.nextBytes(Hasher.salt);
    }

    /**
     * Returns a salted PBKDF2 hash of the value.
     *
     * @param value the value to hash
     * @return a salted PBKDF2 hash of the value
     */
    public static String make(String value) throws NoSuchAlgorithmException, InvalidKeySpecException {
        return make(value.toCharArray());
    }

    /**
     * Returns a salted PBKDF2 hash of the value.
     *
     * @param value the value to hash
     * @return a salted PBKDF2 hash of the value
     */
    public static String make(char[] value) throws NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] hash = pbkdf2(value, Hasher.salt, PBKDF2_ITERATIONS, HASH_BYTES);

        return PBKDF2_ITERATIONS + ":" + toHex(Hasher.salt) + ":" + toHex(hash);
    }

    /**
     * Validates a value using a hash.
     *
     * @param value    the value to check
     * @param goodHash the hash of the valid value
     * @return true if the value is correct, false if not
     */
    public static boolean check(String value, String goodHash)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        return check(value.toCharArray(), goodHash);
    }

    /**
     * Validates a value using a hash.
     *
     * @param value       the value to check
     * @param hashedValue the hash of the valid value
     * @return true if the value is correct, false if not
     */
    public static boolean check(char[] value, String hashedValue)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        // Decode the hash into its parameters
        String[] params = hashedValue.split(":");

        int iterations = Integer.parseInt(params[ITERATION_INDEX]);
        byte[] salt = fromHex(params[SALT_INDEX]);
        byte[] hash = fromHex(params[PBKDF2_INDEX]);

        // Compute the hash of the provided value, using the same salt,
        // iteration count, and hash length
        byte[] testHash = pbkdf2(value, salt, iterations, hash.length);

        // Compare the hashes in constant time. The value is correct if
        // both hashes match.
        return slowEquals(hash, testHash);
    }

    /**
     * Compares two byte arrays in length-constant time. This comparison method is
     * used so that value hashes cannot be extracted from an on-line system using a
     * timing attack and then attacked off-line.
     *
     * @param a the first byte array
     * @param b the second byte array
     * @return true if both byte arrays are the same, false if not
     */
    private static boolean slowEquals(byte[] a, byte[] b) {
        int diff = a.length ^ b.length;

        for (int i = 0; i < a.length && i < b.length; i++) {
            diff |= a[i] ^ b[i];
        }

        return diff == 0;
    }

    /**
     * Computes the PBKDF2 hash of a value.
     *
     * @param value      the value to hash.
     * @param salt       the salt
     * @param iterations the iteration count (slowness factor)
     * @param bytes      the length of the hash to compute in bytes
     * @return the PBDKF2 hash of the value
     */
    private static byte[] pbkdf2(char[] value, byte[] salt, int iterations, int bytes)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        PBEKeySpec spec = new PBEKeySpec(value, salt, iterations, bytes * 8);

        SecretKeyFactory skf = SecretKeyFactory.getInstance(PBKDF2_ALGORITHM);

        return skf.generateSecret(spec).getEncoded();
    }

    /**
     * Converts a string of hexadecimal characters into a byte array.
     *
     * @param hex the hex string
     * @return the hex string decoded into a byte array
     */
    private static byte[] fromHex(String hex) {
        byte[] binary = new byte[hex.length() / 2];

        for (int i = 0; i < binary.length; i++) {
            binary[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
        }

        return binary;
    }

    /**
     * Converts a byte array into a hexadecimal string.
     *
     * @param array the byte array to convert
     * @return a length*2 character string encoding the byte array
     */
    private static String toHex(byte[] array) {
        BigInteger bi = new BigInteger(1, array);

        String hex = bi.toString(16);

        int paddingLength = (array.length * 2) - hex.length();

        if (paddingLength > 0) {
            return String.format("%0" + paddingLength + "d", 0) + hex;
        } else {
            return hex;
        }
    }
}
