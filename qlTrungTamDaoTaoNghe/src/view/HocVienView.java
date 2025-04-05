package view;

import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class HocVienView extends JFrame {
    private JTextField txtMaHocVien, txtHoTen, txtSdt, txtEmail, txtDiaChi, txtTrangThai;
    private JComboBox<String> cbGioiTinh;
    private JDateChooser dateNgaySinh, dateNgayDangKy;
    private JButton btnThem, btnSua, btnXoa, btnTimKiem;
    private JTable table;
    private JScrollPane scrollPane;

    public HocVienView() {
        setTitle("Quản Lý Học Viên");
        setSize(900, 700); // Tăng kích thước cửa sổ
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Sử dụng BorderLayout cho bố cục chính
        setLayout(new BorderLayout(10, 10));

        // Panel chính cho phần nhập liệu và nút (phía trên)
        JPanel topPanel = new JPanel(new BorderLayout(10, 10));

        // Panel nhập liệu
        JPanel inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setBorder(BorderFactory.createTitledBorder("Thông Tin Học Viên"));
        inputPanel.setBackground(new Color(245, 245, 245)); // Màu nền nhạt
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Khoảng cách giữa các thành phần
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Khởi tạo các trường nhập liệu
        JLabel lblMaHocVien = new JLabel("Mã Học Viên:");
        txtMaHocVien = new JTextField(20);
        JLabel lblHoTen = new JLabel("Họ Tên:");
        txtHoTen = new JTextField(20);
        JLabel lblNgaySinh = new JLabel("Ngày Sinh:");
        dateNgaySinh = new JDateChooser();
        dateNgaySinh.setDateFormatString("dd-MM-yyyy");
        JLabel lblGioiTinh = new JLabel("Giới Tính:");
        cbGioiTinh = new JComboBox<>(new String[]{"Nam", "Nữ"});
        JLabel lblSdt = new JLabel("Số Điện Thoại:");
        txtSdt = new JTextField(20);
        JLabel lblEmail = new JLabel("Email:");
        txtEmail = new JTextField(20);
        JLabel lblDiaChi = new JLabel("Địa Chỉ:");
        txtDiaChi = new JTextField(20);
        JLabel lblNgayDangKy = new JLabel("Ngày Đăng Ký:");
        dateNgayDangKy = new JDateChooser();
        dateNgayDangKy.setDateFormatString("dd-MM-yyyy");
        JLabel lblTrangThai = new JLabel("Trạng Thái:");
        txtTrangThai = new JTextField(20);

        // Thêm các thành phần vào panel nhập liệu
        gbc.gridx = 0; gbc.gridy = 0;
        inputPanel.add(lblMaHocVien, gbc);
        gbc.gridx = 1;
        inputPanel.add(txtMaHocVien, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        inputPanel.add(lblHoTen, gbc);
        gbc.gridx = 1;
        inputPanel.add(txtHoTen, gbc); // Sửa lỗi: xóa ký tự "A" thừa

        gbc.gridx = 0; gbc.gridy = 2;
        inputPanel.add(lblNgaySinh, gbc);
        gbc.gridx = 1;
        inputPanel.add(dateNgaySinh, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        inputPanel.add(lblGioiTinh, gbc);
        gbc.gridx = 1;
        inputPanel.add(cbGioiTinh, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        inputPanel.add(lblSdt, gbc);
        gbc.gridx = 1;
        inputPanel.add(txtSdt, gbc);

        gbc.gridx = 0; gbc.gridy = 5;
        inputPanel.add(lblEmail, gbc);
        gbc.gridx = 1;
        inputPanel.add(txtEmail, gbc);

        gbc.gridx = 0; gbc.gridy = 6;
        inputPanel.add(lblDiaChi, gbc);
        gbc.gridx = 1;
        inputPanel.add(txtDiaChi, gbc);

        gbc.gridx = 0; gbc.gridy = 7;
        inputPanel.add(lblNgayDangKy, gbc);
        gbc.gridx = 1;
        inputPanel.add(dateNgayDangKy, gbc);

        gbc.gridx = 0; gbc.gridy = 8;
        inputPanel.add(lblTrangThai, gbc);
        gbc.gridx = 1;
        inputPanel.add(txtTrangThai, gbc);

        // Panel chứa các nút
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        btnThem = new JButton("Thêm");
        btnSua = new JButton("Sửa");
        btnXoa = new JButton("Xóa");
        btnTimKiem = new JButton("Tìm Kiếm");

        // Tùy chỉnh giao diện nút
        btnThem.setBackground(new Color(0, 153, 76)); // Xanh lá
        btnThem.setForeground(Color.WHITE);
        btnSua.setBackground(new Color(255, 165, 0)); // Cam
        btnSua.setForeground(Color.WHITE);
        btnXoa.setBackground(new Color(255, 69, 0)); // Đỏ
        btnXoa.setForeground(Color.WHITE);
        btnTimKiem.setBackground(new Color(0, 102, 204)); // Xanh dương
        btnTimKiem.setForeground(Color.WHITE);

        buttonPanel.add(btnThem);
        buttonPanel.add(btnSua);
        buttonPanel.add(btnXoa);
        buttonPanel.add(btnTimKiem);

        // Thêm inputPanel và buttonPanel vào topPanel
        topPanel.add(inputPanel, BorderLayout.CENTER);
        topPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Bảng hiển thị dữ liệu (phía dưới)
        String[] columnNames = {"Mã Học Viên", "Họ Tên", "SĐT", "Email", "Địa Chỉ", "Ngày Đăng Ký", "Trạng Thái"};
        Object[][] data = {};
        table = new JTable(data, columnNames);
        table.setRowHeight(25);
        table.setGridColor(Color.LIGHT_GRAY);
        table.setShowGrid(true);
        scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Danh Sách Học Viên"));

        // Thêm các thành phần vào frame
        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER); // Bảng chiếm phần lớn không gian
    }

    // Getter cho các trường nhập liệu
    public String getMaHocVien() { return txtMaHocVien.getText(); }
    public String getHoTen() { return txtHoTen.getText(); }
    public java.util.Date getNgaySinh() { return dateNgaySinh.getDate(); }
    public boolean getGioiTinh() { return cbGioiTinh.getSelectedItem().equals("Nam"); }
    public String getSdt() { return txtSdt.getText(); }
    public String getEmail() { return txtEmail.getText(); }
    public String getDiaChi() { return txtDiaChi.getText(); }
    public java.util.Date getNgayDangKy() { return dateNgayDangKy.getDate(); }
    public String getTrangThai() { return txtTrangThai.getText(); }

    // Setter cho bảng
    public void setTableData(Object[][] data) {
        table.setModel(new javax.swing.table.DefaultTableModel(data, new String[]{"Mã Học Viên", "Họ Tên", "SĐT", "Email", "Địa Chỉ", "Ngày Đăng Ký", "Trạng Thái"}));
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
