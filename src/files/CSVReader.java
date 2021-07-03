package files;

import java.io.File;
import java.util.HashMap;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;

/**
 * CSVReader
 */
public class CSVReader extends Reader {

    /**
     * Read the contents of the given file.
     *
     * @param String  file
     * @param HashMap contents
     *
     * @return HashMap
     */
    public void read(String filePath) {
        System.out.println(filePath);
    }
}
