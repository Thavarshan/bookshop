package bookshop.ui;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import bookshop.Application;
import bookshop.db.Manager;
import bookshop.auth.Hasher;

public class GUI extends JFrame {

    /**
     * The application database instance.
     *
     * @var Manager
     */
    protected Application app = null;

    /**
     * The default view that should be displayed when app is first run.
     *
     * @var JPanel
     */
    protected JPanel defaultPanel = null;

    /**
     * The login view.
     *
     * @var JPanel
     */
    protected JPanel loginPanel = new JPanel();

    /**
     * The books listing view.
     *
     * @var JPanel
     */
    protected JPanel booksPanel = new JPanel();

    /**
     * The add new user view.
     *
     * @var JPanel
     */
    protected JPanel userPanel = new JPanel();

    /**
     * Set the default application instance.
     *
     * @param Appliation app
     *
     * @return void
     */
    public void setApplication(Application app) {
        this.app = app;
    }

    /**
     * Get the default application instance.
     *
     * @param Appliation app
     *
     * @return Application
     */
    public Application getApplication() {
        return this.app;
    }

    /**
     * Get the application database manager instance.
     *
     * @return Manager
     */
    public Manager db() {
        return this.app.db;
    }

    /**
     * Build the UI.
     *
     * @return void
     */
    public void build() {
        this.setSize(475, 500);
        this.setDefaultCloseOperation(this.defaultCloseOperation());
        this.setTitle("Bookshop | Bookshop Information System");

        this.add(this.defaultPanel());

        this.setVisible(true);
        this.setResizable(false);
    }

    /**
     * Build the login view.
     *
     * @return JPanel
     */
    public JPanel loginPanel() {
        this.loginPanel.setLayout(null);

        JLabel emailLabel = new JLabel("Email");
        emailLabel.setBounds(10, 20, 80, 25);
        this.loginPanel.add(emailLabel);

        JTextField emailText = new JTextField(20);
        emailText.setBounds(100, 20, 180, 25);
        this.loginPanel.add(emailText);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(10, 50, 80, 25);
        this.loginPanel.add(passwordLabel);

        JPasswordField passwordText = new JPasswordField(20);
        passwordText.setBounds(100, 50, 180, 25);
        this.loginPanel.add(passwordText);

        JLabel message = new JLabel("Please login to continue.");
        message.setBounds(10, 110, 248, 25);
        this.loginPanel.add(message);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(10, 80, 80, 25);
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                String[] credentials = { emailText.getText(), new String(passwordText.getPassword()) };

                if (app.login(credentials)) {
                    if (app.auth.user().hasRole("staff")) {
                        setContentPane(booksPanel());
                    } else {
                        setContentPane(userPanel());
                    }

                    invalidate();
                    validate();

                    return;
                } else {
                    message.setText("Login failed.");

                    return;
                }
            }
        });
        this.loginPanel.add(loginButton);

        return this.loginPanel;
    }

    /**
     * Build the books listing view.
     *
     * @return JPanel
     */
    public JPanel booksPanel() {
        this.booksPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        JLabel searchLable = new JLabel("Search");
        JTextField searchText = new JTextField(20);
        this.booksPanel.add(searchLable);
        this.booksPanel.add(searchText);

        String[] columnNames = { "Title", "Author", "Category", "Price", "Publisher" };
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

        for (String[] book : this.books()) {
            tableModel.addRow(book);
        }

        JTable table = new JTable(tableModel);
        TableRowSorter sorter = new TableRowSorter<>(tableModel);
        table.setRowSorter(sorter);

        JScrollPane scrollPane = new JScrollPane(table);
        this.booksPanel.add(scrollPane);

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

        return this.booksPanel;
    }

    /**
     * Build the add new user view.
     *
     * @return JPanel
     */
    public JPanel userPanel() {
        this.userPanel.setLayout(null);

        JLabel emailLabel = new JLabel("Email");
        emailLabel.setBounds(10, 20, 80, 25);
        this.userPanel.add(emailLabel);

        JTextField emailText = new JTextField(20);
        emailText.setBounds(100, 20, 180, 25);
        this.userPanel.add(emailText);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(10, 50, 80, 25);
        this.userPanel.add(passwordLabel);

        JPasswordField passwordText = new JPasswordField(20);
        passwordText.setBounds(100, 50, 180, 25);
        this.userPanel.add(passwordText);

        JLabel roleLabel = new JLabel("Role");
        roleLabel.setBounds(10, 80, 80, 25);
        this.userPanel.add(roleLabel);

        JTextField roleText = new JTextField(20);
        roleText.setBounds(100, 80, 180, 25);
        this.userPanel.add(roleText);

        JButton createButton = new JButton("Add User");
        createButton.setBounds(10, 110, 120, 25);
        createButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                try {
                    String password = Hasher.make(new String(passwordText.getPassword()));
                    String[] details = { emailText.getText(), password, roleText.getText() };
                    db().insert("users", details);
                    db().persist();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        });
        this.userPanel.add(createButton);

        JLabel message = new JLabel("Do not forget the password you added.");
        message.setBounds(10, 140, 248, 25);
        this.userPanel.add(message);

        return this.userPanel;
    }

    /**
     * Set the default panel.
     *
     * @return void
     */
    public void setDefaultPanel(JPanel panel) {
        this.defaultPanel = panel;
    }

    /**
     * Get the default view that should be displayed when app is first run.
     *
     * @return JPanel
     */
    public JPanel defaultPanel() {
        return this.defaultPanel;
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
     * The default close operation on GUIs.
     *
     * @return int
     */
    public int defaultCloseOperation() {
        this.app.terminate();

        return JFrame.EXIT_ON_CLOSE;
    }
}
