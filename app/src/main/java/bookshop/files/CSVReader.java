package bookshop.files;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CSVReader extends Reader {

    /**
     * The default file reader instance.
     *
     * @var BufferedReader
     */
    BufferedReader fileReader = null;

    /**
     * The default delimiter used to seperate data.
     */
    final String DELIMITER = ",";

    /**
     * Read the contents of the given file and transfer it on to the given list.
     *
     * @param String              filePath
     * @param ArrayList<String[]> data
     *
     * @return ArrayList<String[]>
     */
    public ArrayList<String[]> read(String filePath, ArrayList<String[]> data) {
        try {
            String line = "";
            fileReader = new BufferedReader(new FileReader(convertToAbsolutePath(filePath)));

            while ((line = fileReader.readLine()) != null) {
                data.add(line.split(","));
            }

            fileReader.close();

            return data;
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    /**
     * Convert given relative path into absolute path.
     *
     * @param String filePath
     *
     * @return String
     */
    public String convertToAbsolutePath(String filePath) {
        Path currentRelativePath = Paths.get(filePath);

        return currentRelativePath.toAbsolutePath().toString();
    }
}
