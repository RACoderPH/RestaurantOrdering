import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;

public class LoginFrame extends JFrame {
    public LoginFrame() {
        // Create the login frame
        setTitle("Login - Restaurant Ordering System");
        setSize(1000, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create panels for left and right sides
        JPanel leftPanel = new JPanel();
        leftPanel.setPreferredSize(new Dimension(500, 250));
        leftPanel.setBackground(Color.CYAN);

        JPanel rightPanel = new JPanel();
        rightPanel.setPreferredSize(new Dimension(500, 250));
        rightPanel.setBackground(Color.WHITE);
           
        JPanel LoginPanel = new JPanel();
        LoginPanel.setPreferredSize(new Dimension(250, 125));
        
        // Create password label and text field
     // Create username label and text field
        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField(20);

        // Create password label and text field
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField(20);

        
         JButton loginButton = new JButton("Login");
        // Add username label and text field to LoginPanel
        LoginPanel.add(usernameLabel);
        LoginPanel.add(usernameField);

        // Add password label and text field to LoginPanel
        LoginPanel.add(passwordLabel);
        LoginPanel.add(passwordField);
        LoginPanel.setBackground(Color.RED);
        
        
        

        // Add login button to LoginPanel
        LoginPanel.add(loginButton);
        rightPanel.add(LoginPanel,BorderLayout.CENTER);
        
        // Add panels to the frame with BorderLayout
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(leftPanel, BorderLayout.WEST);
        getContentPane().add(rightPanel, BorderLayout.EAST);

        // Center the login frame on the screen
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        // Create and display the login frame
        SwingUtilities.invokeLater(() -> {
            LoginFrame loginFrame = new LoginFrame();
            loginFrame.setVisible(true);
        });
    }
}
