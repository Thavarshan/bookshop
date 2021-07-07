package bookshop.files;

import java.util.ArrayList;

public abstract class Reader {

    /**
     * All data collected from file.
     *
     * @var ArrayList
     */
    ArrayList<String[]> data = new ArrayList<String[]>();

    /**
     * Read the contents of the given file and transfer it on to the given list.
     *
     * @param String filePath
     *
     * @return ArrayList<String[]>
     */
    public abstract ArrayList<String[]> read(String filePath);
}
