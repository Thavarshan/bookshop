package bookshop.db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import bookshop.files.CSVReader;
import bookshop.files.CSVWriter;

public class Manager {

    /**
     * The database reader.
     *
     * @var CSVReader
     */
    CSVReader reader = new CSVReader();

    /**
     * The database writer instance.
     *
     * @var CSVWriter
     */
    CSVWriter writer = new CSVWriter();

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
    String[] tables = { "books.csv", "users.csv" };

    /**
     * All the data loaded into the application.
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

            ArrayList<String[]> rows = reader.read(this.databasePath + "/" + file);

            for (String[] row : rows) {
                table.addData(row);
            }

            rows.clear();

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

    /**
     * Insert a new record into the data base.
     *
     * @return void
     */
    public void insert(String table, String[] row) {
        getTable(table).addData(row);
    }

    /**
     * Re-write the modified data from memory to file.
     *
     * @return void
     */
    public void persist() {
        dataSet().forEach((table, data) -> {
            writer.write(this.databasePath + "/" + table + ".csv", data.getData());
        });
    }
}
