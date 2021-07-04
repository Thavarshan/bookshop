package bookshop.files;

import org.junit.Test;
import static org.junit.Assert.*;

public class CSVWriterTest {

    @Test
    public void canWriteToFiles() {
        String contents = "geoff@gumpert.com,matrix209,staff";
        CSVWriter writer = new CSVWriter();

        try {
            writer.write("./fixtures/users.csv", contents);

            assertTrue(true);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }
}
