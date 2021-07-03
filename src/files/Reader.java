package files;

import java.util.HashMap;

/**
 * Reader
 */
public abstract class Reader {

    /**
     * Read the contents of the given file.
     *
     * @param String  file
     * @param HashMap contents
     *
     * @return HashMap
     */
    public abstract void read(String filePath);
}
