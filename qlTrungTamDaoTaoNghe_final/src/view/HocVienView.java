package view;

import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.Date;

public class HocVienView extends JFrame {
    private JTextField txtMaHocVien, txtHoTen, txtSdt, txtEmail, txtDiaChi, txtSoBuoiVang;
    private JComboBox<String> cbGioiTinh, cbKhoaHoc, cbKetQuaDanhGia;
    private JTextField txtTenKhoaHoc;
    private JDateChooser dateNgaySinh;
    private JButton btnDatLai, btnThem, btnSua, btnXoa, btnTimKiem, btnCapNhat;
    private JTable table;
    private JScrollPane scrollPane;

    public HocVienView() {
        setTitle("Quản Lý Học Viên");
        setSize(1000, 750);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(800, 600)); // Ensure minimum size

        // Main layout
        getContentPane().setBackground(new Color(248, 249, 250));
        setLayout(new BorderLayout(10, 10));

        // Title
        JLabel lblTitle = new JLabel("QUẢN LÝ HỌC VIÊN", JLabel.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTitle.setForeground(new Color(33, 37, 41));
        lblTitle.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        add(lblTitle, BorderLayout.NORTH);

        // Input panels
        JPanel inputContainer = new JPanel(new GridLayout(1, 2, 10, 10));
        inputContainer.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        inputContainer.setBackground(new Color(248, 249, 250));

        // Personal Info Panel
        JPanel personalPanel = new JPanel(new GridBagLayout());
        personalPanel.setBorder(BorderFactory.createTitledBorder("Thông Tin Cá Nhân"));
        personalPanel.setBackground(new Color(255, 255, 255));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        JLabel lblMaHocVien = new JLabel("Mã Học Viên:");
        lblMaHocVien.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtMaHocVien = new JTextField(20);
        txtMaHocVien.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        JLabel lblHoTen = new JLabel("Họ Tên:");
        lblHoTen.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtHoTen = new JTextField(20);
        txtHoTen.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        JLabel lblNgaySinh = new JLabel("Ngày Sinh:");
        lblNgaySinh.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        dateNgaySinh = new JDateChooser();
        dateNgaySinh.setDateFormatString("dd-MM-yyyy");
        dateNgaySinh.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        JLabel lblGioiTinh = new JLabel("Giới Tính:");
        lblGioiTinh.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        cbGioiTinh = new JComboBox<>(new String[]{"Nam", "Nữ"});
        cbGioiTinh.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        JLabel lblSdt = new JLabel("Số Điện Thoại:");
        lblSdt.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtSdt = new JTextField(20);
        txtSdt.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtEmail = new JTextField(20);
        txtEmail.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        JLabel lblDiaChi = new JLabel("Địa Chỉ:");
        lblDiaChi.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtDiaChi = new JTextField(20);
        txtDiaChi.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        gbc.gridx = 0; gbc.gridy = 0;
        personalPanel.add(lblMaHocVien, gbc);
        gbc.gridx = 1;
        personalPanel.add(txtMaHocVien, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        personalPanel.add(lblHoTen, gbc);
        gbc.gridx = 1;
        personalPanel.add(txtHoTen, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        personalPanel.add(lblNgaySinh, gbc);
        gbc.gridx = 1;
        personalPanel.add(dateNgaySinh, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        personalPanel.add(lblGioiTinh, gbc);
        gbc.gridx = 1;
        personalPanel.add(cbGioiTinh, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        personalPanel.add(lblSdt, gbc);
        gbc.gridx = 1;
        personalPanel.add(txtSdt, gbc);

        gbc.gridx = 0; gbc.gridy = 5;
        personalPanel.add(lblEmail, gbc);
        gbc.gridx = 1;
        personalPanel.add(txtEmail, gbc);

        gbc.gridx = 0; gbc.gridy = 6;
        personalPanel.add(lblDiaChi, gbc);
        gbc.gridx = 1;
        personalPanel.add(txtDiaChi, gbc);

        // Course Info Panel
        JPanel coursePanel = new JPanel(new GridBagLayout());
        coursePanel.setBorder(BorderFactory.createTitledBorder("Thông Tin Khóa Học"));
        coursePanel.setBackground(new Color(255, 255, 255));
        GridBagConstraints gbcCourse = new GridBagConstraints();
        gbcCourse.insets = new Insets(8, 8, 8, 8);
        gbcCourse.fill = GridBagConstraints.HORIZONTAL;
        gbcCourse.weightx = 1.0;

        JLabel lblKetQuaDanhGia = new JLabel("Kết Quả Đánh Giá:");
        lblKetQuaDanhGia.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        cbKetQuaDanhGia = new JComboBox<>(new String[]{"Xuất Sắc", "Giỏi", "Khá", "Trung Bình", "Yếu", "Kém"});
        cbKetQuaDanhGia.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        JLabel lblKhoaHoc = new JLabel("Mã Khóa Học:");
        lblKhoaHoc.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        cbKhoaHoc = new JComboBox<>();
        cbKhoaHoc.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        JLabel lblTenKhoaHoc = new JLabel("Tên Khóa Học:");
        lblTenKhoaHoc.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtTenKhoaHoc = new JTextField(20);
        txtTenKhoaHoc.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtTenKhoaHoc.setEditable(false);
        txtTenKhoaHoc.setBackground(new Color(240, 240, 240));

        JLabel lblSoBuoiVang = new JLabel("Số Buổi Vắng:");
        lblSoBuoiVang.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtSoBuoiVang = new JTextField(20);
        txtSoBuoiVang.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        ((AbstractDocument) txtSoBuoiVang.getDocument()).setDocumentFilter(new DocumentFilter() {
            @Override
            public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
                if (string == null) return;
                if (string.matches("\\d*")) {
                    super.insertString(fb, offset, string, attr);
                }
            }

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                if (text == null) return;
                if (text.matches("\\d*")) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }
        });

        gbcCourse.gridx = 0; gbcCourse.gridy = 0;
        coursePanel.add(lblKetQuaDanhGia, gbcCourse);
        gbcCourse.gridx = 1;
        coursePanel.add(cbKetQuaDanhGia, gbcCourse);

        gbcCourse.gridx = 0; gbcCourse.gridy = 1;
        coursePanel.add(lblKhoaHoc, gbcCourse);
        gbcCourse.gridx = 1;
        coursePanel.add(cbKhoaHoc, gbcCourse);

        gbcCourse.gridx = 0; gbcCourse.gridy = 2;
        coursePanel.add(lblTenKhoaHoc, gbcCourse);
        gbcCourse.gridx = 1;
        coursePanel.add(txtTenKhoaHoc, gbcCourse);

        gbcCourse.gridx = 0; gbcCourse.gridy = 3;
        coursePanel.add(lblSoBuoiVang, gbcCourse);
        gbcCourse.gridx = 1;
        coursePanel.add(txtSoBuoiVang, gbcCourse);

        inputContainer.add(personalPanel);
        inputContainer.add(coursePanel);

        // Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.setBackground(new Color(248, 249, 250));

        btnDatLai = createStyledButton("Đặt Lại", new Color(108, 117, 125));
        btnThem = createStyledButton("Thêm", new Color(40, 167, 69));
        btnSua = createStyledButton("Sửa", new Color(255, 193, 7));
        btnXoa = createStyledButton("Xóa", new Color(220, 53, 69));
        btnTimKiem = createStyledButton("Tìm Kiếm", new Color(0, 123, 255));
        btnCapNhat = createStyledButton("Cập Nhật", new Color(111, 66, 193));

        buttonPanel.add(btnDatLai);
        buttonPanel.add(btnThem);
        buttonPanel.add(btnSua);
        buttonPanel.add(btnXoa);
        buttonPanel.add(btnTimKiem);
        buttonPanel.add(btnCapNhat);

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(inputContainer, BorderLayout.CENTER);
        topPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Table
        String[] columnNames = {"Mã Học Viên", "Họ Tên", "SĐT", "Email", "Địa Chỉ", "Kết Quả Đánh Giá", "Mã Khóa Học", "Tên Khóa Học", "Số Buổi Vắng"};
        table = new JTable(new DefaultTableModel(new Object[][]{}, columnNames)) {
            @Override
            public Component prepareRenderer(javax.swing.table.TableCellRenderer renderer, int row, int column) {
                Component c = super.prepareRenderer(renderer, row, column);
                if (row % 2 == 0) {
                    c.setBackground(new Color(248, 249, 250));
                } else {
                    c.setBackground(Color.WHITE);
                }
                return c;
            }
        };
        table.setRowHeight(30);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        table.getTableHeader().setBackground(new Color(0, 123, 255));
        table.getTableHeader().setForeground(Color.WHITE);
        scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Danh Sách Học Viên"));

        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    private JButton createStyledButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor.brighter());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor);
            }
        });
        return button;
    }

    // Getters and Setters
    public String getMaHocVien() { return txtMaHocVien.getText(); }
    public void setMaHocVien(String maHocVien) { txtMaHocVien.setText(maHocVien); }
    public String getHoTen() { return txtHoTen.getText(); }
    public void setHoTen(String hoTen) { txtHoTen.setText(hoTen); }
    public Date getNgaySinh() { return dateNgaySinh.getDate(); }
    public void setNgaySinh(Date ngaySinh) { dateNgaySinh.setDate(ngaySinh); }
    public boolean getGioiTinh() { return cbGioiTinh.getSelectedItem().equals("Nam"); }
    public void setGioiTinh(boolean gioiTinh) { cbGioiTinh.setSelectedItem(gioiTinh ? "Nam" : "Nữ"); }
    public String getSdt() { return txtSdt.getText(); }
    public void setSdt(String sdt) { txtSdt.setText(sdt); }
    public String getEmail() { return txtEmail.getText(); }
    public void setEmail(String email) { txtEmail.setText(email); }
    public String getDiaChi() { return txtDiaChi.getText(); }
    public void setDiaChi(String diaChi) { txtDiaChi.setText(diaChi); }
    public String getKetQuaDanhGia() { return (String) cbKetQuaDanhGia.getSelectedItem(); }
    public void setKetQuaDanhGia(String ketQuaDanhGia) { cbKetQuaDanhGia.setSelectedItem(ketQuaDanhGia); }
    public String getMaKhoaHoc() { return (String) cbKhoaHoc.getSelectedItem(); }
    public void setMaKhoaHoc(String maKhoaHoc) { cbKhoaHoc.setSelectedItem(maKhoaHoc); }
    public String getTenKhoaHoc() { return txtTenKhoaHoc.getText(); }
    public void setTenKhoaHoc(String tenKhoaHoc) { txtTenKhoaHoc.setText(tenKhoaHoc); }
    public String getSoBuoiVang() { return txtSoBuoiVang.getText(); }
    public void setSoBuoiVang(String soBuoiVang) { txtSoBuoiVang.setText(soBuoiVang); }

    public void setKhoaHocItems(String[] maKhoaHocArray) {
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(maKhoaHocArray);
        cbKhoaHoc.setModel(model);
    }

    public void setTableData(Object[][] data) {
        table.setModel(new DefaultTableModel(data, new String[]{"Mã Học Viên", "Họ Tên", "SĐT", "Email", "Địa Chỉ", "Kết Quả Đánh Giá", "Mã Khóa Học", "Tên Khóa Học", "Số Buổi Vắng"}));
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Thông Báo", JOptionPane.INFORMATION_MESSAGE);
    }

    public void resetFields() {
        txtMaHocVien.setText("");
        txtHoTen.setText("");
        dateNgaySinh.setDate(null);
        cbGioiTinh.setSelectedIndex(0);
        txtSdt.setText("");
        txtEmail.setText("");
        txtDiaChi.setText("");
        cbKetQuaDanhGia.setSelectedIndex(0);
        cbKhoaHoc.setSelectedIndex(-1);
        txtTenKhoaHoc.setText("");
        txtSoBuoiVang.setText("0");
        clearFieldErrors();
    }

    public void addThemListener(ActionListener listener) { btnThem.addActionListener(listener); }
    public void addSuaListener(ActionListener listener) { btnSua.addActionListener(listener); }
    public void addXoaListener(ActionListener listener) { btnXoa.addActionListener(listener); }
    public void addTimKiemListener(ActionListener listener) { btnTimKiem.addActionListener(listener); }
    public void addDatLaiListener(ActionListener listener) { btnDatLai.addActionListener(listener); }
    public void addCapNhatListener(ActionListener listener) { btnCapNhat.addActionListener(listener); }
    public void addTableMouseListener(MouseListener listener) { table.addMouseListener(listener); }
    public void addKhoaHocListener(ActionListener listener) { cbKhoaHoc.addActionListener(listener); }
    public JTable getTable() { return table; }

    // Highlight invalid fields
    public void highlightFieldError(JTextField field) {
        field.setBorder(BorderFactory.createLineBorder(new Color(220, 53, 69), 2));
    }

    public void clearFieldErrors() {
        JTextField[] fields = {txtMaHocVien, txtHoTen, txtSdt, txtEmail, txtDiaChi, txtSoBuoiVang};
        for (JTextField field : fields) {
            field.setBorder(UIManager.getBorder("TextField.border"));
        }
    }
}