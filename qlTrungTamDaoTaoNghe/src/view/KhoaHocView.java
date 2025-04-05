package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class KhoaHocView extends JFrame {
    private JTextField txtMaKhoa, txtTenKhoa, txtThoiLuong, txtHocPhi, txtMoTa;
    private JButton btnThem, btnSua, btnXoa, btnTimKiem;
    private JTable table;
    private JScrollPane scrollPane;

    public KhoaHocView() {
        setTitle("Quản Lý Khóa Học");
        setSize(900, 600); // Tăng kích thước cửa sổ
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Sử dụng BorderLayout cho bố cục chính
        setLayout(new BorderLayout(10, 10));

        // Panel chính cho phần nhập liệu và nút (phía trên)
        JPanel topPanel = new JPanel(new BorderLayout(10, 10));

        // Panel nhập liệu
        JPanel inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setBorder(BorderFactory.createTitledBorder("Thông Tin Khóa Học"));
        inputPanel.setBackground(new Color(245, 245, 245));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Khởi tạo các trường nhập liệu
        JLabel lblMaKhoa = new JLabel("Mã Khóa Học:");
        txtMaKhoa = new JTextField(20);
        JLabel lblTenKhoa = new JLabel("Tên Khóa Học:");
        txtTenKhoa = new JTextField(20); // Thêm dòng này để khởi tạo txtTenKhoa
        JLabel lblThoiLuong = new JLabel("Thời Lượng (giờ):");
        txtThoiLuong = new JTextField(20);
        JLabel lblHocPhi = new JLabel("Học Phí:");
        txtHocPhi = new JTextField(20);
        JLabel lblMoTa = new JLabel("Mô Tả:");
        txtMoTa = new JTextField(20);

        // Thêm các thành phần vào panel nhập liệu
        gbc.gridx = 0; gbc.gridy = 0;
        inputPanel.add(lblMaKhoa, gbc);
        gbc.gridx = 1;
        inputPanel.add(txtMaKhoa, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        inputPanel.add(lblTenKhoa, gbc);
        gbc.gridx = 1;
        inputPanel.add(txtTenKhoa, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        inputPanel.add(lblThoiLuong, gbc);
        gbc.gridx = 1;
        inputPanel.add(txtThoiLuong, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        inputPanel.add(lblHocPhi, gbc);
        gbc.gridx = 1;
        inputPanel.add(txtHocPhi, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        inputPanel.add(lblMoTa, gbc);
        gbc.gridx = 1;
        inputPanel.add(txtMoTa, gbc);

        // Panel chứa các nút
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        btnThem = new JButton("Thêm");
        btnSua = new JButton("Sửa");
        btnXoa = new JButton("Xóa");
        btnTimKiem = new JButton("Tìm Kiếm");

        // Tùy chỉnh giao diện nút
        btnThem.setBackground(new Color(0, 153, 76));
        btnThem.setForeground(Color.WHITE);
        btnSua.setBackground(new Color(255, 165, 0));
        btnSua.setForeground(Color.WHITE);
        btnXoa.setBackground(new Color(255, 69, 0));
        btnXoa.setForeground(Color.WHITE);
        btnTimKiem.setBackground(new Color(0, 102, 204));
        btnTimKiem.setForeground(Color.WHITE);

        buttonPanel.add(btnThem);
        buttonPanel.add(btnSua);
        buttonPanel.add(btnXoa);
        buttonPanel.add(btnTimKiem);

        // Thêm inputPanel và buttonPanel vào topPanel
        topPanel.add(inputPanel, BorderLayout.CENTER);
        topPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Bảng hiển thị dữ liệu
        String[] columnNames = {"Mã Khóa", "Tên Khóa", "Thời Lượng", "Học Phí", "Mô Tả"};
        Object[][] data = {};
        table = new JTable(data, columnNames);
        table.setRowHeight(25);
        table.setGridColor(Color.LIGHT_GRAY);
        table.setShowGrid(true);
        scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Danh Sách Khóa Học"));

        // Thêm các thành phần vào frame
        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER); // Bảng chiếm phần lớn không gian
    }

    // Getter cho các trường nhập liệu
    public String getMaKhoa() { return txtMaKhoa.getText(); }
    public String getTenKhoa() { return txtTenKhoa.getText(); }
    public String getThoiLuong() { return txtThoiLuong.getText(); }
    public String getHocPhi() { return txtHocPhi.getText(); }
    public String getMoTa() { return txtMoTa.getText(); }

    // Setter cho bảng
    public void setTableData(Object[][] data) {
        table.setModel(new javax.swing.table.DefaultTableModel(data, new String[]{"Mã Khóa", "Tên Khóa", "Thời Lượng", "Học Phí", "Mô Tả"}));
    }

    // Hiển thị thông báo
    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    // Thêm ActionListener cho các nút
    public void addThemListener(ActionListener listener) { btnThem.addActionListener(listener); }
    public void addSuaListener(ActionListener listener) { btnSua.addActionListener(listener); }
    public void addXoaListener(ActionListener listener) { btnXoa.addActionListener(listener); }
    public void addTimKiemListener(ActionListener listener) { btnTimKiem.addActionListener(listener); }
}
