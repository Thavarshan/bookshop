package bookshop.files;

import java.io.File;
import java.io.IOException;

public class FileHandler {

    /**
     * Determine if the given file exists.
     *
     * @param String filePath
     *
     * @return boolean
     */
    public static boolean exists(String filePath) {
        File fileHandler = new File(filePath);

        return fileHandler.exists();
    }

    /**
     * Delete the given file.
     *
     * @param String filePath
     *
     * @return void
     */
    public static void delete(String filePath) {
        File fileHandler = new File(filePath);

        if (!fileHandler.exists()) {
            return;
        }

        fileHandler.delete();
    }

    /**
     * Create new file.
     *
     * @param String  filePath
     * @param boolean force
     *
     * @return void
     *
     * @throws IOException
     */
    public static void create(String filePath, boolean force) throws IOException {
        File fileHandler = new File(filePath);

        if (fileHandler.exists() && !force) {
            return;
        }

        FileHandler.delete(filePath);

        fileHandler.createNewFile();
    }
}
