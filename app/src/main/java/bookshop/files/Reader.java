package bookshop.files;

public abstract class Reader {

    /**
     * Read the contents of the given file.
     *
     * @param String filePath
     *
     * @return String
     */
    public abstract String read(String filePath);
}
