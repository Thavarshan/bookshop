package bookshop.files;

import java.util.ArrayList;

public abstract class Reader {

    /**
     * Read the contents of the given file and transfer it on to the given list.
     *
     * @param String              filePath
     * @param ArrayList<String[]> data
     *
     * @return ArrayList<String[]>
     */
    public abstract ArrayList<String[]> read(String filePath, ArrayList<String[]> data);
}
