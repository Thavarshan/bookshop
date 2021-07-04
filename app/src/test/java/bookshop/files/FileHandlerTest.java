package bookshop.files;

import java.io.File;
import org.junit.Test;
import static org.junit.Assert.*;

public class FileHandlerTest {

    @Test
    public void createNewFiles() {
        String filePath = "./test.txt";

        try {
            FileHandler.create(filePath, false);
        } catch (Exception e) {
            fail(e.getMessage());
        }

        File nativeManager = new File(filePath);

        assertTrue(nativeManager.exists());
        nativeManager.delete();
    }

    @Test
    public void determineFileExists() {
        String filePath = "./test.txt";

        assertFalse(FileHandler.exists(filePath));

        File nativeManager = new File(filePath);

        try {
            nativeManager.createNewFile();
        } catch (Exception e) {
            fail(e.getMessage());
        }

        assertTrue(FileHandler.exists(filePath));

        nativeManager.delete();
    }

    @Test
    public void deleteExistingFile() {
        String filePath = "./test.txt";
        File nativeManager = new File(filePath);

        try {
            nativeManager.createNewFile();

            assertTrue(nativeManager.exists());
        } catch (Exception e) {
            fail(e.getMessage());
        }

        FileHandler.delete(filePath);
        assertFalse(nativeManager.exists());
    }
}
