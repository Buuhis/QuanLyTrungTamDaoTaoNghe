package view;

import com.toedter.calendar.JDateChooser;
import model.GiangVien;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionListener;

public class GiangVienView extends JFrame {
    private JTextField txtMaGiangVien, txtHoTen, txtSdt, txtEmail, txtDiaChi, txtTrinhDo, txtBangCap, txtKinhNghiem;
    private JComboBox<String> cbGioiTinh;
    private JDateChooser dateNgaySinh;
    private JButton btnDatLai, btnThem, btnSua, btnXoa, btnTimKiem, btnCapNhat;
    private JTable table;
    private JScrollPane scrollPane;

    public GiangVienView() {
        setTitle("Quản Lý Giảng Viên");
        setSize(1000, 750);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(800, 600));

        getContentPane().setBackground(new Color(248, 249, 250));
        setLayout(new BorderLayout(10, 10));

        // Title
        JLabel lblTitle = new JLabel("QUẢN LÝ GIẢNG VIÊN", JLabel.CENTER);
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

        JLabel lblMaGiangVien = new JLabel("Mã Giảng Viên:");
        lblMaGiangVien.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtMaGiangVien = new JTextField(20);
        txtMaGiangVien.setFont(new Font("Segoe UI", Font.PLAIN, 14));

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
        personalPanel.add(lblMaGiangVien, gbc);
        gbc.gridx = 1;
        personalPanel.add(txtMaGiangVien, gbc);

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

        // Professional Info Panel
        JPanel proPanel = new JPanel(new GridBagLayout());
        proPanel.setBorder(BorderFactory.createTitledBorder("Thông Tin Chuyên Môn"));
        proPanel.setBackground(new Color(255, 255, 255));
        GridBagConstraints gbcPro = new GridBagConstraints();
        gbcPro.insets = new Insets(8, 8, 8, 8);
        gbcPro.fill = GridBagConstraints.HORIZONTAL;
        gbcPro.weightx = 1.0;

        JLabel lblTrinhDo = new JLabel("Trình Độ:");
        lblTrinhDo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtTrinhDo = new JTextField(20);
        txtTrinhDo.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        JLabel lblBangCap = new JLabel("Bằng Cấp:");
        lblBangCap.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtBangCap = new JTextField(20);
        txtBangCap.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        JLabel lblKinhNghiem = new JLabel("Kinh Nghiệm (năm):");
        lblKinhNghiem.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtKinhNghiem = new JTextField(20);
        txtKinhNghiem.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        gbcPro.gridx = 0; gbcPro.gridy = 0;
        proPanel.add(lblTrinhDo, gbcPro);
        gbcPro.gridx = 1;
        proPanel.add(txtTrinhDo, gbcPro);

        gbcPro.gridx = 0; gbcPro.gridy = 1;
        proPanel.add(lblBangCap, gbcPro);
        gbcPro.gridx = 1;
        proPanel.add(txtBangCap, gbcPro);

        gbcPro.gridx = 0; gbcPro.gridy = 2;
        proPanel.add(lblKinhNghiem, gbcPro);
        gbcPro.gridx = 1;
        proPanel.add(txtKinhNghiem, gbcPro);

        inputContainer.add(personalPanel);
        inputContainer.add(proPanel);

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
        String[] columnNames = {"Mã Giảng Viên", "Họ Tên", "SĐT", "Email", "Địa Chỉ", "Trình Độ", "Bằng Cấp", "Kinh Nghiệm", "Khóa Học Dạy"};
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
        scrollPane.setBorder(BorderFactory.createTitledBorder("Danh Sách Giảng Viên"));

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
    public String getMaGiangVien() { return txtMaGiangVien.getText(); }
    public String getHoTen() { return txtHoTen.getText(); }
    public java.util.Date getNgaySinh() { return dateNgaySinh.getDate(); }
    public boolean getGioiTinh() { return cbGioiTinh.getSelectedItem().equals("Nam"); }
    public String getSdt() { return txtSdt.getText(); }
    public String getEmail() { return txtEmail.getText(); }
    public String getDiaChi() { return txtDiaChi.getText(); }
    public String getTrinhDo() { return txtTrinhDo.getText(); }
    public String getBangCap() { return txtBangCap.getText(); }
    public String getKinhNghiem() { return txtKinhNghiem.getText(); }
    public JTable getTable() { return table; }

    public void setTableData(Object[][] data) {
        table.setModel(new DefaultTableModel(data, new String[]{"Mã Giảng Viên", "Họ Tên", "SĐT", "Email", "Địa Chỉ", "Trình Độ", "Bằng Cấp", "Kinh Nghiệm", "Khóa Học Dạy"}));
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Thông Báo", JOptionPane.INFORMATION_MESSAGE);
    }

    public void resetFields() {
        txtMaGiangVien.setText("");
        txtHoTen.setText("");
        dateNgaySinh.setDate(null);
        cbGioiTinh.setSelectedIndex(0);
        txtSdt.setText("");
        txtEmail.setText("");
        txtDiaChi.setText("");
        txtTrinhDo.setText("");
        txtBangCap.setText("");
        txtKinhNghiem.setText("");
        clearFieldErrors();
    }

    public void addThemListener(ActionListener listener) { btnThem.addActionListener(listener); }
    public void addSuaListener(ActionListener listener) { btnSua.addActionListener(listener); }
    public void addXoaListener(ActionListener listener) { btnXoa.addActionListener(listener); }
    public void addTimKiemListener(ActionListener listener) { btnTimKiem.addActionListener(listener); }
    public void addDatLaiListener(ActionListener listener) { btnDatLai.addActionListener(listener); }
    public void addCapNhatListener(ActionListener listener) { btnCapNhat.addActionListener(listener); }

    public void updateFieldsFromSelectedRow(GiangVien gv) {
        if (gv != null) {
            txtMaGiangVien.setText(gv.getMaGiangVien());
            txtHoTen.setText(gv.getHoTen());
            dateNgaySinh.setDate(gv.getNgaySinh());
            cbGioiTinh.setSelectedItem(gv.isGioiTinh() ? "Nam" : "Nữ");
            txtSdt.setText(gv.getSdt());
            txtEmail.setText(gv.getEmail());
            txtDiaChi.setText(gv.getDiaChi());
            txtTrinhDo.setText(gv.getTrinhDo());
            txtBangCap.setText(gv.getBangCap());
            txtKinhNghiem.setText(String.valueOf(gv.getKinhNghiem()));
        } else {
            showMessage("Vui lòng chọn một dòng trong bảng!");
        }
    }

    public void highlightFieldError(JTextField field) {
        field.setBorder(BorderFactory.createLineBorder(new Color(220, 53, 69), 2));
    }

    public void clearFieldErrors() {
        JTextField[] fields = {txtMaGiangVien, txtHoTen, txtSdt, txtEmail, txtDiaChi, txtTrinhDo, txtBangCap, txtKinhNghiem};
        for (JTextField field : fields) {
            field.setBorder(UIManager.getBorder("TextField.border"));
        }
    }
}