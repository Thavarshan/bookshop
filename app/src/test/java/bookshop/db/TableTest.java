package bookshop.db;

import org.junit.Test;
import static org.junit.Assert.*;

public class TableTest {

    @Test
    public void tablesHaveName() {
        Table table = new Table();
        table.setName("books");

        assertEquals("books", table.getName());
    }

    @Test
    public void tablesHaveRowsOfData() {
        Table table = new Table();
        table.setName("books");
        String[] row = { "Data Smart", "John Foreman", "data_science", "235", "Wiley" };
        table.addData(row);

        assertArrayEquals(row, table.getData().get(0));
    }
}
