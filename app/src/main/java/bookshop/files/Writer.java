package bookshop.files;

import java.util.List;

public abstract class Writer {

    /**
     * Write the given contents to the given file.
     *
     * @param String         filePath
     * @param List<String[]> dataLines
     *
     * @return void
     */
    public abstract void write(String filePath, List<String[]> dataLines);
}
