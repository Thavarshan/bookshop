package bookshop.ui;

import java.awt.event.*;
import javax.swing.*;

import bookshop.auth.Hasher;

public class Register extends GUI implements ActionListener, PlacesComponents {

    /**
     * The email text field.
     *
     * @var JTextField
     */
    JTextField emailText = null;

    /**
     * The password text field.
     *
     * @var JPasswordField
     */
    JPasswordField passwordText = null;

    /**
     * The role text field.
     *
     * @var JTextField
     */
    JTextField roleText = null;

    /**
     * The login button.
     *
     * @var JButton
     */
    JButton createButton = null;

    /**
     * Login message.
     *
     * @var JLabel
     */
    JLabel message = null;

    /**
     * Build the UI.
     *
     * @return void
     */
    public void build() {
        this.frame = new JFrame("Bookshop | Add New User");
        this.frame.setSize(350, 300);
        this.frame.setDefaultCloseOperation(defaultCloseOperation());

        JPanel panel = new JPanel();
        this.frame.add(panel);
        placeComponents(panel);

        this.frame.setVisible(true);
        this.frame.setResizable(false);
    }

    /**
     * Place UI components on the default panel.
     *
     * @param JPanel panel
     *
     * @return void
     */
    public void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel emailLabel = new JLabel("Email");
        emailLabel.setBounds(10, 20, 80, 25);
        panel.add(emailLabel);

        this.emailText = new JTextField(20);
        this.emailText.setBounds(100, 20, 180, 25);
        panel.add(this.emailText);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(10, 50, 80, 25);
        panel.add(passwordLabel);

        this.passwordText = new JPasswordField(20);
        this.passwordText.setBounds(100, 50, 180, 25);
        panel.add(this.passwordText);

        JLabel roleLabel = new JLabel("Role");
        roleLabel.setBounds(10, 80, 80, 25);
        panel.add(roleLabel);

        this.roleText = new JTextField(20);
        this.roleText.setBounds(100, 80, 180, 25);
        panel.add(this.roleText);

        this.createButton = new JButton("Add User");
        this.createButton.setBounds(10, 110, 120, 25);
        this.createButton.addActionListener(this);
        panel.add(this.createButton);

        this.message = new JLabel("Do not forget the password you added.");
        this.message.setBounds(10, 140, 248, 25);
        panel.add(this.message);
    }

    /**
     * Invoked when an action occurs.
     *
     * @param event the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent event) {
        try {
            String password = Hasher.make(new String(this.passwordText.getPassword()));

            String[] details = { this.emailText.getText(), password, this.roleText.getText() };

            this.db().insert("users", details);

            this.db().persist();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
