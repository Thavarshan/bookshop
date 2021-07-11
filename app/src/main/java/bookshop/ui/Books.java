package bookshop.ui;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;

public class Books extends GUI implements ActionListener {

    /**
     * Build the UI.
     *
     * @return void
     */
    public void build() {
        this.frame = new JFrame("Bookshop | Manage Books");
        this.frame.setSize(475, 500);
        this.frame.setDefaultCloseOperation(defaultCloseOperation());
        this.frame.setLayout(new FlowLayout(FlowLayout.CENTER));

        JLabel searchLable = new JLabel("Search");
        JTextField searchText = new JTextField(20);
        this.frame.add(searchLable);
        this.frame.add(searchText);

        String[] columnNames = { "Title", "Author", "Category", "Price", "Publisher" };
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

        for (String[] book : books()) {
            tableModel.addRow(book);
        }

        JTable table = new JTable(tableModel);
        TableRowSorter sorter = new TableRowSorter<>(tableModel);
        table.setRowSorter(sorter);

        JScrollPane scrollPane = new JScrollPane(table);
        this.frame.add(scrollPane);

        searchText.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent event) {
                search(searchText.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent event) {
                search(searchText.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent event) {
                search(searchText.getText());
            }

            public void search(String text) {
                if (text.length() == 0) {
                    sorter.setRowFilter(null);
                } else {
                    sorter.setRowFilter(RowFilter.regexFilter(text));
                }
            }
        });

        this.frame.setVisible(true);
        this.frame.setResizable(false);
    }

    /**
     * Get the list of available books.
     *
     * @return ArrayList<String[]>
     */
    public ArrayList<String[]> books() {
        return this.db().getTable("books").getData();
    }

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
