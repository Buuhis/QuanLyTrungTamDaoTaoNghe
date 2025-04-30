package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainView extends JFrame {
    private JButton btnQuanLyHocVien;
    private JButton btnQuanLyKhoaHoc;
    private JButton btnQuanLyGiangVien;
    private JButton btnThongKe;

    public MainView() {
        setTitle("Trung Tâm Đào Tạo Nghề");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center on screen
        setResizable(false); // Prevent resizing

        // Main panel with BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(new Color(240, 240, 240)); // Light gray background

        // Title
        JLabel lblTitle = new JLabel("HỆ THỐNG QUẢN LÝ ĐÀO TẠO NGHỀ", JLabel.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitle.setForeground(new Color(0, 51, 102)); // Dark blue color
        mainPanel.add(lblTitle, BorderLayout.NORTH);

        // Button panel with GridLayout (2x2 grid)
        JPanel buttonPanel = new JPanel(new GridLayout(2, 2, 20, 20));
        buttonPanel.setBackground(new Color(240, 240, 240)); // Same light gray background
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Initialize buttons with icons and text
        btnQuanLyHocVien = createStyledButton("Quản Lý Học Viên", "/icons/hocvien.png");
        btnQuanLyKhoaHoc = createStyledButton("Quản Lý Khóa Học", "/icons/khoahoc.png");
        btnQuanLyGiangVien = createStyledButton("Quản Lý Giảng Viên", "/icons/giangvien.png");
        btnThongKe = createStyledButton("Thống Kê Báo Cáo", "/icons/thongke.png");

        // Add buttons to the panel
        buttonPanel.add(btnQuanLyHocVien);
        buttonPanel.add(btnQuanLyKhoaHoc);
        buttonPanel.add(btnQuanLyGiangVien);
        buttonPanel.add(btnThongKe);

        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        add(mainPanel);
    }

    // Helper method to create styled buttons with icons
    private JButton createStyledButton(String text, String iconPath) {
        JButton button = new JButton();
        button.setText(text);
        button.setFont(new Font("Arial", Font.PLAIN, 16));
        button.setForeground(new Color(0, 51, 102)); // Dark blue text
        button.setBackground(Color.WHITE); // White background
        button.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1)); // Light gray border
        button.setFocusPainted(false);
        button.setVerticalTextPosition(SwingConstants.BOTTOM);
        button.setHorizontalTextPosition(SwingConstants.CENTER);

        // Add icon (replace iconPath with actual image paths or resources)
        try {
            ImageIcon icon = new ImageIcon(getClass().getResource(iconPath));
            Image scaledIcon = icon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
            button.setIcon(new ImageIcon(scaledIcon));
        } catch (Exception e) {
            System.err.println("Icon not found for " + text + ": " + e.getMessage());
            button.setIcon(null); // Fallback if icon is not found
        }

        // Add slight shadow effect using border
        button.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        return button;
    }

    // Methods to add ActionListeners (unchanged)
    public void addQuanLyHocVienListener(ActionListener listener) {
        btnQuanLyHocVien.addActionListener(listener);
    }

    public void addQuanLyKhoaHocListener(ActionListener listener) {
        btnQuanLyKhoaHoc.addActionListener(listener);
    }

    public void addQuanLyGiangVienListener(ActionListener listener) {
        btnQuanLyGiangVien.addActionListener(listener);
    }

    public void addThongKeListener(ActionListener listener) {
        btnThongKe.addActionListener(listener);
    }
}