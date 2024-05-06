import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;

public class Homepage extends JFrame {
    private JPanel summaryOrdersPanel; // Added variable for summary orders panel
    private JTextField totalPriceField; // Added text field for total price

    private double totalPrice = 0.0; // Variable to keep track of total price

    public Homepage() {
        // Create the homepage frame
        setTitle("Homepage - Restaurant Ordering System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a panel to hold the container and images/buttons
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Create container panel with gray background and label
        JPanel containerPanel = new JPanel();
        containerPanel.setBackground(Color.GRAY);
        JLabel titleLabel = new JLabel("Restaurant Ordering System");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20)); // Set font for title
        titleLabel.setForeground(Color.WHITE); // Set text color to white
        containerPanel.add(titleLabel);
        // Add container panel to the main panel at the top
        mainPanel.add(containerPanel, BorderLayout.NORTH);

        // Create a panel to hold the images, cake names, prices, and buttons
        JPanel imagePanel = new JPanel();
        imagePanel.setLayout(new GridLayout(0, 3, 10, 10)); // 3 images per row with spacing

        try {
            // Load images from file using class loader
            ImageIcon image1 = new ImageIcon(getClass().getResource("/images/image1.jpg"));
            ImageIcon image2 = new ImageIcon(getClass().getResource("/images/image2.jpg"));
            ImageIcon image3 = new ImageIcon(getClass().getResource("/images/image3.jpg"));

            // Create scaled icons
            ImageIcon scaledIcon1 = new ImageIcon(image1.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH));
            ImageIcon scaledIcon2 = new ImageIcon(image2.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH));
            ImageIcon scaledIcon3 = new ImageIcon(image3.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH));

            // Create panels for each image, cake name, price, and button
            imagePanel.add(createImageButtonPanel(scaledIcon1, "Cake 1", "400.00"));
            imagePanel.add(createImageButtonPanel(scaledIcon2, "Cake 2", "350.00"));
            imagePanel.add(createImageButtonPanel(scaledIcon3, "Cake 3", "450.00"));
        } catch (Exception e) {
            e.printStackTrace();
            // Handle error loading images
            JOptionPane.showMessageDialog(this, "Error loading images: " + e.getMessage(), "Image Loading Error", JOptionPane.ERROR_MESSAGE);
        }

        // Add the image panel to the main panel
        mainPanel.add(imagePanel, BorderLayout.CENTER);

        // Create panel for the summary of orders
        JPanel summaryPanel = new JPanel(new BorderLayout());
        summaryPanel.setBackground(Color.GRAY);
        JLabel summaryLabel = new JLabel("Summary of Orders");
        summaryLabel.setFont(new Font("Arial", Font.BOLD, 16));
        summaryLabel.setForeground(Color.WHITE);
        summaryLabel.setHorizontalAlignment(SwingConstants.CENTER);
        summaryPanel.add(summaryLabel, BorderLayout.NORTH);
        // Add summary panel to the main panel on the right side
        mainPanel.add(summaryPanel, BorderLayout.EAST);

        // Set preferred width for the summary panel (adjust as needed)
        summaryPanel.setPreferredSize(new Dimension(300, getHeight()));

        // Initialize summaryOrdersPanel
        summaryOrdersPanel = new JPanel();
        summaryOrdersPanel.setLayout(new BoxLayout(summaryOrdersPanel, BoxLayout.Y_AXIS)); // Use BoxLayout to stack cake names vertically

        // Add the summaryOrdersPanel to the summaryPanel
        JScrollPane summaryScrollPane = new JScrollPane(summaryOrdersPanel); // Add scroll pane for better viewing if there are many orders
        summaryPanel.add(summaryScrollPane, BorderLayout.CENTER);

        // Add text field for total price
        totalPriceField = new JTextField();
        totalPriceField.setEditable(false); // Make it disabled
        totalPriceField.setHorizontalAlignment(JTextField.CENTER);
        summaryPanel.add(totalPriceField, BorderLayout.SOUTH);

        // Add the main panel to the content pane instead of the frame itself
        getContentPane().add(mainPanel);

        // Pack the frame to fit its components
        pack();

        // Center the homepage frame on the screen
        setLocationRelativeTo(null);
    }

    // Method to create a panel with an image, cake name, price, and a button with rounded border
    private JPanel createImageButtonPanel(Icon icon, String cakeName, String cakePrice) {
        // Create panel to hold image, cake name, price, and button
        JPanel panel = new JPanel(new BorderLayout());

        // Create label for the image
        JLabel label = new JLabel(icon);
        panel.add(label, BorderLayout.CENTER);

        // Create label for cake name
        JLabel nameLabel = new JLabel(cakeName);
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Create label for cake price
        JLabel priceLabel = new JLabel(cakePrice);
        priceLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Create button for placing order
        JButton orderButton = new JButton("Place Order");
        orderButton.setFocusPainted(false);
        orderButton.setContentAreaFilled(true); // Make background visible
        orderButton.setBorderPainted(false); // Remove default button border

        // Set rounded border for button
        orderButton.setBorder(new CompoundBorder(
                new RoundedBorder(10), // Rounded border
                BorderFactory.createEmptyBorder(5, 10, 5, 10) // Empty border for spacing
        ));

        // Set blue background color for button
        orderButton.setBackground(new Color(30, 144, 255)); // RGB for Dodger Blue color

        // Set foreground (text) color for button
        orderButton.setForeground(Color.WHITE); // White text color

        // Add action listener to the button
        orderButton.addActionListener(e -> {
            updateSummaryOrders(cakeName, cakePrice);
        });

        // Create panel to hold cake name, price, and buttons
        JPanel infoPanel = new JPanel(new BorderLayout());
        infoPanel.add(nameLabel, BorderLayout.NORTH); // Position cake name above the price
        infoPanel.add(priceLabel, BorderLayout.CENTER); // Position price below the cake name
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER)); // Panel to hold buttons
        buttonPanel.add(orderButton);
        infoPanel.add(buttonPanel, BorderLayout.SOUTH); // Position buttons below the price

        // Add components to panel
        panel.add(infoPanel, BorderLayout.SOUTH); // Position cake name, price, and buttons below the image

        return panel;
    }

    // Method to update the summary orders panel
    private void updateSummaryOrders(String cakeName, String cakePrice) {
        JLabel newCakeLabel = new JLabel(cakeName + " - " + cakePrice);
        newCakeLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center align cake name
        summaryOrdersPanel.add(createSummaryItemPanel(newCakeLabel)); // Add new cake item to the summary orders panel
        summaryOrdersPanel.revalidate(); // Refresh layout to reflect changes
        summaryOrdersPanel.repaint(); // Repaint the summary orders panel

        // Update total price
        totalPrice += Double.parseDouble(cakePrice);
        totalPriceField.setText("Total Price: " + totalPrice);
    }

    // Method to create a panel for each summary item with remove button
    private JPanel createSummaryItemPanel(JLabel itemLabel) {
        JPanel panel = new JPanel(new BorderLayout());

        // Create remove button
        JButton removeButton = new JButton("Remove");
        removeButton.setFocusPainted(false);
        removeButton.setPreferredSize(new Dimension(80, 25)); // Set preferred size for remove button
        removeButton.addActionListener(e -> {
            // Remove item from summary
            Container parent = panel.getParent();
            parent.remove(panel);
            parent.revalidate();
            parent.repaint();

            // Update total price
            String[] itemData = itemLabel.getText().split(" - ");
            totalPrice -= Double.parseDouble(itemData[1]);
            totalPriceField.setText("Total Price: " + totalPrice);
        });

        // Add components to panel
        panel.add(itemLabel, BorderLayout.CENTER);
        panel.add(removeButton, BorderLayout.EAST);

        return panel;
    }

    // Custom border class for rounded corners
    private static class RoundedBorder extends AbstractBorder {
        private final int radius;

        public RoundedBorder(int radius) {
            this.radius = radius;
        }

        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            super.paintBorder(c, g, x, y, width, height);
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
            g2.dispose();
        }

        public Insets getBorderInsets(Component c) {
            return new Insets(this.radius + 1, this.radius + 1, this.radius + 2, this.radius);
        }

        public Insets getBorderInsets(Component c, Insets insets) {
            insets.left = insets.top = insets.right = insets.bottom = this.radius + 1;
            return insets;
        }

        public boolean isBorderOpaque() {
            return true;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Homepage homepage = new Homepage();
            homepage.setVisible(true);
        });
    }
}
