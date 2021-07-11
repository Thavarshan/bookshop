package bookshop.ui;

import java.awt.event.*;
import javax.swing.*;

public class Login extends GUI implements ActionListener, HasComponents {

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
     * The login button.
     *
     * @var JButton
     */
    JButton loginButton = null;

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
        this.frame = new JFrame("Bookshop | Login");
        this.frame.setSize(350, 170);
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

        this.loginButton = new JButton("Login");
        this.loginButton.setBounds(10, 80, 80, 25);
        this.loginButton.addActionListener(this);
        panel.add(this.loginButton);

        this.message = new JLabel("Please login to continue.");
        this.message.setBounds(10, 110, 248, 25);
        panel.add(this.message);
    }

    /**
     * Invoked when an action occurs.
     *
     * @param event the event to be processed
     *
     * @return void
     */
    @Override
    public void actionPerformed(ActionEvent event) {
        String[] credentials = { this.emailText.getText(), new String(this.passwordText.getPassword()) };

        if (this.app.auth.attempt(credentials)) {
            this.message.setText("You are loggen in.");
        } else {
            this.message.setText("Login failed.");
        }
    }
}
