package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class LoginView extends JFrame {
    private JTextField idField;
    private JPasswordField passwordField;
    private JCheckBox showPasswordCheckBox;
    private JButton loginButton;

    public LoginView() {
        setTitle("Đăng Nhập Hệ Thống");
        setSize(450, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(new Color(245, 245, 245));

        JLabel titleLabel = new JLabel("ĐĂNG NHẬP", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(0, 102, 204));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        JPanel inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setBackground(new Color(245, 245, 245));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.gridx = 0;

        // Username label
        JLabel idLabel = new JLabel("Tên đăng nhập");
        idLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.insets = new Insets(10, 10, 2, 10); // Giảm khoảng cách dưới từ 10 xuống 2
        gbc.gridy = 0;
        inputPanel.add(idLabel, gbc);

        // Username field
        idField = new JTextField();
        idField.setFont(new Font("Arial", Font.PLAIN, 14));
        idField.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        gbc.insets = new Insets(2, 10, 10, 10); // Giảm khoảng cách trên từ 10 xuống 2
        gbc.gridy = 1;
        inputPanel.add(idField, gbc);

        // Password label
        JLabel passwordLabel = new JLabel("Mật khẩu");
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.insets = new Insets(10, 10, 2, 10); // Giảm khoảng cách dưới từ 10 xuống 2
        gbc.gridy = 2;
        inputPanel.add(passwordLabel, gbc);

        // Password field
        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Arial", Font.PLAIN, 14));
        passwordField.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        gbc.insets = new Insets(2, 10, 10, 10); // Giảm khoảng cách trên từ 10 xuống 2
        gbc.gridy = 3;
        inputPanel.add(passwordField, gbc);

        // Show Password checkbox
        showPasswordCheckBox = new JCheckBox("Hiển thị mật khẩu");
        showPasswordCheckBox.setFont(new Font("Arial", Font.PLAIN, 12));
        showPasswordCheckBox.setBackground(new Color(245, 245, 245));
        showPasswordCheckBox.addActionListener(e -> {
            if (showPasswordCheckBox.isSelected()) {
                passwordField.setEchoChar((char) 0);
            } else {
                passwordField.setEchoChar('•');
            }
        });
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridy = 4;
        inputPanel.add(showPasswordCheckBox, gbc);

        // Add vertical glue to push components up
        gbc.gridy = 5;
        gbc.weighty = 1.0;
        inputPanel.add(Box.createVerticalGlue(), gbc);

        mainPanel.add(inputPanel, BorderLayout.CENTER);

        loginButton = new JButton("Đăng Nhập");
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        loginButton.setBackground(new Color(0, 102, 204));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        loginButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(new Color(245, 245, 245));
        buttonPanel.add(loginButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    public String getId() {
        return idField.getText();
    }

    public String getPassword() {
        return new String(passwordField.getPassword());
    }

    public void addLoginListener(ActionListener listener) {
        loginButton.addActionListener(listener);
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginView().setVisible(true));
    }
}