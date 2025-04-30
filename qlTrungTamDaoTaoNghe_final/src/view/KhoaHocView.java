package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import model.KhoaHoc;
import java.awt.*;
import java.awt.event.ActionListener;

public class KhoaHocView extends JFrame {
    private JTextField txtMaKhoa, txtTenKhoa, txtThoiLuong, txtHocPhi, txtMoTa;
    private JComboBox<String> cbGiangVien;
    private JButton btnDatLai, btnThem, btnSua, btnXoa, btnTimKiem, btnCapNhat;
    private JTable table;
    private JScrollPane scrollPane;

    public KhoaHocView() {
        setTitle("Quản Lý Khóa Học");
        setSize(900, 650);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(700, 500));

        getContentPane().setBackground(new Color(248, 249, 250));
        setLayout(new BorderLayout(10, 10));

        // Title
        JLabel lblTitle = new JLabel("QUẢN LÝ KHÓA HỌC", JLabel.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTitle.setForeground(new Color(33, 37, 41));
        lblTitle.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        add(lblTitle, BorderLayout.NORTH);

        // Input Panel
        JPanel inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setBorder(BorderFactory.createTitledBorder("Thông Tin Khóa Học"));
        inputPanel.setBackground(new Color(255, 255, 255));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        JLabel lblMaKhoa = new JLabel("Mã Khóa Học:");
        lblMaKhoa.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtMaKhoa = new JTextField(20);
        txtMaKhoa.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        JLabel lblTenKhoa = new JLabel("Tên Khóa Học:");
        lblTenKhoa.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtTenKhoa = new JTextField(20);
        txtTenKhoa.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        JLabel lblThoiLuong = new JLabel("Thời Lượng (giờ):");
        lblThoiLuong.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtThoiLuong = new JTextField(20);
        txtThoiLuong.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        JLabel lblHocPhi = new JLabel("Học Phí:");
        lblHocPhi.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtHocPhi = new JTextField(20);
        txtHocPhi.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        JLabel lblMoTa = new JLabel("Mô Tả:");
        lblMoTa.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtMoTa = new JTextField(20);
        txtMoTa.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        JLabel lblGiangVien = new JLabel("Giảng Viên:");
        lblGiangVien.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        cbGiangVien = new JComboBox<>();
        cbGiangVien.setFont(new Font("Segoe UI", Font.PLAIN, 14));

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

        gbc.gridx = 0; gbc.gridy = 5;
        inputPanel.add(lblGiangVien, gbc);
        gbc.gridx = 1;
        inputPanel.add(cbGiangVien, gbc);

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
        topPanel.add(inputPanel, BorderLayout.CENTER);
        topPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Table
        String[] columnNames = {"Mã Khóa Học", "Tên Khóa Học", "Thời Lượng", "Học Phí", "Mô Tả", "Giảng Viên"};
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
        scrollPane.setBorder(BorderFactory.createTitledBorder("Danh Sách Khóa Học"));

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
    public String getMaKhoa() { return txtMaKhoa.getText(); }
    public String getTenKhoa() { return txtTenKhoa.getText(); }
    public String getThoiLuong() { return txtThoiLuong.getText(); }
    public String getHocPhi() { return txtHocPhi.getText(); }
    public String getMoTa() { return txtMoTa.getText(); }
    public String getMaGiangVien() { return (String) cbGiangVien.getSelectedItem(); }
    public JTable getTable() { return table; }

    public void setGiangVienItems(String[] maGiangVien) {
        cbGiangVien.setModel(new DefaultComboBoxModel<>(maGiangVien));
    }

    public void setTableData(Object[][] data) {
        table.setModel(new DefaultTableModel(data, new String[]{"Mã Khóa Học", "Tên Khóa Học", "Thời Lượng", "Học Phí", "Mô Tả", "Giảng Viên"}));
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Thông Báo", JOptionPane.INFORMATION_MESSAGE);
    }

    public void resetFields() {
        txtMaKhoa.setText("");
        txtTenKhoa.setText("");
        txtThoiLuong.setText("");
        txtHocPhi.setText("");
        txtMoTa.setText("");
        cbGiangVien.setSelectedIndex(0);
        clearFieldErrors();
    }

    public void addThemListener(ActionListener listener) { btnThem.addActionListener(listener); }
    public void addSuaListener(ActionListener listener) { btnSua.addActionListener(listener); }
    public void addXoaListener(ActionListener listener) { btnXoa.addActionListener(listener); }
    public void addTimKiemListener(ActionListener listener) { btnTimKiem.addActionListener(listener); }
    public void addDatLaiListener(ActionListener listener) { btnDatLai.addActionListener(listener); }
    public void addCapNhatListener(ActionListener listener) { btnCapNhat.addActionListener(listener); }

    public void updateFieldsFromSelectedRow(KhoaHoc kh) {
        if (kh != null) {
            txtMaKhoa.setText(kh.getMaKhoa());
            txtTenKhoa.setText(kh.getTenKhoa());
            txtThoiLuong.setText(String.valueOf(kh.getThoiLuong()));
            txtHocPhi.setText(String.valueOf(kh.getHocPhi()));
            txtMoTa.setText(kh.getMoTa());
            cbGiangVien.setSelectedItem(kh.getMaGiangVien() != null ? kh.getMaGiangVien() : "");
        } else {
            showMessage("Vui lòng chọn một dòng trong bảng!");
        }
    }

    public void highlightFieldError(JTextField field) {
        field.setBorder(BorderFactory.createLineBorder(new Color(220, 53, 69), 2));
    }

    public void clearFieldErrors() {
        JTextField[] fields = {txtMaKhoa, txtTenKhoa, txtThoiLuong, txtHocPhi, txtMoTa};
        for (JTextField field : fields) {
            field.setBorder(UIManager.getBorder("TextField.border"));
        }
    }
}