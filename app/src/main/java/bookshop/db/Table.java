package bookshop.db;

import java.util.ArrayList;

public class Table {

    /**
     * The table name.
     *
     * @var String
     */
    String name;

    /**
     * The rows of data.
     *
     * @var ArrayList<String>
     */
    ArrayList<String[]> data = new ArrayList<String[]>();

    /**
     * Set the name of the table.
     *
     * @return void
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Set the name of the table.
     *
     * @return void
     */
    public void addData(String[] row) {
        this.data.add(row);
    }

    /**
     * Get all data rows in this table.
     *
     * @return ArrayList<String[]>
     */
    public ArrayList<String[]> getData() {
        return this.data;
    }
}
