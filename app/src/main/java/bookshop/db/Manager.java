package bookshop.db;

import java.util.HashMap;
import bookshop.files.CSVReader;

public class Manager {

    /**
     * The database reader.
     *
     * @var CSVReader
     */
    CSVReader reader = new CSVReader();

    /**
     * Location where all data files are stored.
     *
     * @var String
     */
    String databasePath = "data";

    /**
     * List of all tables available for the application.
     *
     * @var String[]
     */
    String[] tables = { "users.csv", "books.csv" };

    /**
     * All the data loadedinto the application.
     *
     * @var HashMap<String, Table>
     */
    HashMap<String, Table> data = new HashMap<String, Table>();

    /**
     * Load data into application memory.
     *
     * @return void
     */
    public void loadData() {
        for (String file : this.tables) {
            Table table = new Table();

            String name = file.replace(".csv", "");
            table.setName(name);

            for (String[] row : reader.read(this.databasePath + "/" + file)) {
                table.addData(row);
            }

            this.data.put(name, table);
        }
    }

    /**
     * Get all the data available in the application memory.
     *
     * @return HashMap<String, Table>
     */
    public HashMap<String, Table> dataSet() {
        return this.data;
    }

    /**
     * Get the specific table from the list of tables.
     *
     * @return Table
     */
    public Table getTable(String name) {
        return this.data.get(name);
    }
}
