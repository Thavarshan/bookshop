package bookshop.files;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CSVWriter extends Writer {

    /**
     * Write the given contents to the given file.
     *
     * @param String         filePath
     * @param List<String[]> dataLines
     *
     * @return void
     */
    public void write(String filePath, List<String[]> dataLines) {
        PrintWriter printWriter = createFileWriter(filePath);

        dataLines.stream().map(data -> convertToCSV(data)).forEach(printWriter::println);

        printWriter.close();
    }

    /**
     * Create the file writer instance.
     *
     * @param String filePath
     *
     * @return PrintWriter
     */
    protected PrintWriter createFileWriter(String filePath) {
        PrintWriter printWriter = null;

        try {
            FileWriter fileWriter = new FileWriter(filePath, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            printWriter = new PrintWriter(bufferedWriter);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return printWriter;
    }

    /**
     * Convert given content to CSV compatible format.
     *
     * @param String[] content
     *
     * @return String
     */
    public String convertToCSV(String[] content) {
        return Stream.of(content).map(this::escapeSpecialCharacters).collect(Collectors.joining(","));
    }

    /**
     * Cleanup string of all un-allowable characters.
     *
     * @param String content
     *
     * @param String
     */
    public String escapeSpecialCharacters(String content) {
        String escapedData = content.replaceAll("\\R", " ");

        if (content.contains(",") || content.contains("\"") || content.contains("'")) {
            escapedData = "\"" + content.replace("\"", "\"\"") + "\"";
        }

        return escapedData;
    }
}
