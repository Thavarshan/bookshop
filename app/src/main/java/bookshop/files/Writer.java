package bookshop.files;

public abstract class Writer {

    /**
     * Write the given contents to the given file.
     *
     * @param String filePath
     * @param String contents
     *
     * @return void
     */
    public abstract void write(String filePath, String contents);
}
