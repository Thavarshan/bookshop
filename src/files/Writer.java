package files;

/**
 * Writer
 */
public abstract class Writer {

    /**
     * Read the contents of the given file.
     *
     * @param String file
     * @param String contents
     *
     * @return HashMap
     */
    public abstract void write(String file, String contents);
}
