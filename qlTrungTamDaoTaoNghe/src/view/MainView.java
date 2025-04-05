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
        setTitle("Quản Lý Trung Tâm Đào Tạo Nghề");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Căn giữa màn hình

        // Sử dụng BorderLayout cho bố cục chính
        setLayout(new BorderLayout());

        // Tiêu đề
        JLabel lblTitle = new JLabel("HỆ THỐNG QUẢN LÝ TRUNG TÂM ĐÀO TẠO NGHỀ", JLabel.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitle.setForeground(new Color(0, 102, 204)); // Màu xanh dương
        lblTitle.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(lblTitle, BorderLayout.NORTH);

        // Panel chứa các nút
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 1, 10, 10)); // 4 hàng, 1 cột, khoảng cách 10px
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 100, 20, 100)); // Padding
        buttonPanel.setBackground(new Color(240, 248, 255)); // Màu nền nhạt

        // Khởi tạo các nút
        btnQuanLyHocVien = new JButton("Quản Lý Học Viên");
        btnQuanLyKhoaHoc = new JButton("Quản Lý Khóa Học");
        btnQuanLyGiangVien = new JButton("Quản Lý Giảng Viên");
        btnThongKe = new JButton("Thống Kê Báo Cáo");

        // Tùy chỉnh giao diện nút
        Font buttonFont = new Font("Arial", Font.PLAIN, 16);
        Color buttonColor = new Color(0, 153, 76); // Màu xanh lá
        Color buttonTextColor = Color.WHITE;

        btnQuanLyHocVien.setFont(buttonFont);
        btnQuanLyHocVien.setBackground(buttonColor);
        btnQuanLyHocVien.setForeground(buttonTextColor);

        btnQuanLyKhoaHoc.setFont(buttonFont);
        btnQuanLyKhoaHoc.setBackground(buttonColor);
        btnQuanLyKhoaHoc.setForeground(buttonTextColor);

        btnQuanLyGiangVien.setFont(buttonFont);
        btnQuanLyGiangVien.setBackground(buttonColor);
        btnQuanLyGiangVien.setForeground(buttonTextColor);

        btnThongKe.setFont(buttonFont);
        btnThongKe.setBackground(new Color(255, 165, 0)); // Màu cam cho nút thống kê
        btnThongKe.setForeground(buttonTextColor);

        // Thêm các nút vào panel
        buttonPanel.add(btnQuanLyHocVien);
        buttonPanel.add(btnQuanLyKhoaHoc);
        buttonPanel.add(btnQuanLyGiangVien);
        buttonPanel.add(btnThongKe);

        // Thêm panel vào frame
        add(buttonPanel, BorderLayout.CENTER);
    }

    // Các phương thức để thêm ActionListener
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
