package bookshop.files;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import static org.junit.Assert.*;

public class CSVWriterTest {

    @Test
    public void stringReturnedInQuotesWhenEscapeSpecialCharactersForCommaContainingData() {
        CSVWriter writer = new CSVWriter();
        String content = "three,two,one";
        String escapedData = writer.escapeSpecialCharacters(content);

        String expectedData = "\"three,two,one\"";
        assertEquals(expectedData, escapedData);
    }

    @Test
    public void stringReturnedFormattedWhenEscapeSpecialCharactersQuoteContainingData() {
        CSVWriter writer = new CSVWriter();
        String content = "She said \"Hello\"";
        String escapedData = writer.escapeSpecialCharacters(content);

        String expectedData = "\"She said \"\"Hello\"\"\"";
        assertEquals(expectedData, escapedData);
    }

    @Test
    public void stringReturnedInQuotesWhenEscapeSpecialCharacters() {
        CSVWriter writer = new CSVWriter();
        String dataNewline = "This contains\na newline";
        String dataCarriageReturn = "This contains\r\na newline and carriage return";
        String escapedDataNl = writer.escapeSpecialCharacters(dataNewline);
        String escapedDataCr = writer.escapeSpecialCharacters(dataCarriageReturn);

        String expectedData = "This contains a newline";
        assertEquals(expectedData, escapedDataNl);
        String expectedDataCr = "This contains a newline and carriage return";
        assertEquals(expectedDataCr, escapedDataCr);
    }

    @Test
    public void stringReturnedUnchangedWhenEacapeSpecialCharacters() {
        CSVWriter writer = new CSVWriter();
        String content = "This is nothing special";
        String returnedData = writer.escapeSpecialCharacters(content);

        assertEquals(content, returnedData);
    }

    @Test
    public void writeListToCSVFile() {
        List<String[]> dataLines = new ArrayList<String[]>();
        dataLines.add(new String[] { "james@may.com", "thGrandTour", "staff" });
        dataLines.add(new String[] { "jerremy@clarckson.com", "thGrandTour", "staff" });

        CSVWriter writer = new CSVWriter();
        writer.write(resourcePath(), dataLines);

        File csvOutputFile = new File(resourcePath());
        assertTrue(csvOutputFile.exists());
    }

    public String resourcePath() {
        String resourceName = "users.csv";

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(resourceName).getFile());

        return file.getAbsolutePath();
    }
}
