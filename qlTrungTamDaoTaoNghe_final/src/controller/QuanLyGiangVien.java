package controller;

import model.GiangVien;
import model.KhoaHoc;
import model.IQuanLy;
import view.GiangVienView;
import util.FileManager;
import util.CustomException;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class QuanLyGiangVien implements IQuanLy<GiangVien> {
    private ArrayList<GiangVien> danhSachGiangVien;
    private GiangVienView view;
    private QuanLyKhoaHoc quanLyKhoaHoc;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    public QuanLyGiangVien(GiangVienView view, QuanLyKhoaHoc quanLyKhoaHoc) throws CustomException {
        this.view = view;
        this.quanLyKhoaHoc = quanLyKhoaHoc;
        this.danhSachGiangVien = new ArrayList<>();
        try {
            danhSachGiangVien = FileManager.readGiangVienFromFile("giangvien.txt");
        } catch (Exception e) {
            throw new CustomException(CustomException.FILE_ERROR, "Không thể đọc file giảng viên: " + e.getMessage());
        }
        initView();
    }

    private void initView() {
        view.addThemListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { themGiangVien(); }
        });
        view.addSuaListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { suaGiangVien(); }
        });
        view.addXoaListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { xoaGiangVien(); }
        });
        view.addTimKiemListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { timKiemGiangVien(); }
        });
        view.addDatLaiListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.resetFields();
            }
        });
        view.addCapNhatListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = view.getTable().getSelectedRow();
                if (selectedRow >= 0) {
                    String maGiangVien = view.getTable().getValueAt(selectedRow, 0).toString();
                    GiangVien gv = timKiem(maGiangVien);
                    view.updateFieldsFromSelectedRow(gv);
                } else {
                    view.showMessage("Vui lòng chọn một dòng trong bảng!");
                }
            }
        });

        capNhatBang();
    }

    @Override
    public void them(GiangVien gv) throws CustomException {
        for (GiangVien existing : danhSachGiangVien) {
            if (existing.getMaGiangVien().equals(gv.getMaGiangVien())) {
                throw new CustomException(CustomException.DUPLICATE_ID, "Mã giảng viên đã tồn tại!");
            }
        }
        danhSachGiangVien.add(gv);
        try {
            FileManager.writeGiangVienToFile(danhSachGiangVien, "giangvien.txt");
        } catch (Exception e) {
            throw new CustomException(CustomException.FILE_ERROR, "Không thể ghi file giảng viên: " + e.getMessage());
        }
        capNhatBang();
        view.showMessage("Thêm giảng viên thành công!");
    }

    @Override
    public void sua(GiangVien gv) throws CustomException {
        for (int i = 0; i < danhSachGiangVien.size(); i++) {
            if (danhSachGiangVien.get(i).getMaGiangVien().equals(gv.getMaGiangVien())) {
                danhSachGiangVien.set(i, gv);
                try {
                    FileManager.writeGiangVienToFile(danhSachGiangVien, "giangvien.txt");
                } catch (Exception e) {
                    throw new CustomException(CustomException.FILE_ERROR, "Không thể ghi file giảng viên: " + e.getMessage());
                }
                capNhatBang();
                view.showMessage("Sửa giảng viên thành công!");
                return;
            }
        }
        throw new CustomException(CustomException.INVALID_DATA, "Không tìm thấy giảng viên để sửa!");
    }

    @Override
    public void xoa(String ma) throws CustomException {
        if (quanLyKhoaHoc != null) {
            for (KhoaHoc kh : quanLyKhoaHoc.getDanhSachKhoaHoc()) {
                if (ma.equals(kh.getMaGiangVien())) {
                    throw new CustomException(CustomException.INVALID_DATA, "Không thể xóa giảng viên vì đang được phân công dạy khóa học: " + kh.getTenKhoa());
                }
            }
        }
        for (GiangVien gv : danhSachGiangVien) {
            if (gv.getMaGiangVien().equals(ma)) {
                danhSachGiangVien.remove(gv);
                try {
                    FileManager.writeGiangVienToFile(danhSachGiangVien, "giangvien.txt");
                } catch (Exception e) {
                    throw new CustomException(CustomException.FILE_ERROR, "Không thể ghi file giảng viên: " + e.getMessage());
                }
                capNhatBang();
                view.showMessage("Xóa giảng viên thành công!");
                return;
            }
        }
        throw new CustomException(CustomException.INVALID_DATA, "Không tìm thấy giảng viên để xóa!");
    }

    @Override
    public GiangVien timKiem(String ma) {
        for (GiangVien gv : danhSachGiangVien) {
            if (gv.getMaGiangVien().equals(ma)) {
                return gv;
            }
        }
        return null;
    }

    private void themGiangVien() {
        try {
            String maGiangVien = view.getMaGiangVien();
            String hoTen = view.getHoTen();
            String sdt = view.getSdt();
            String email = view.getEmail();
            String diaChi = view.getDiaChi();
            String trinhDo = view.getTrinhDo();
            String bangCap = view.getBangCap();
            String kinhNghiem = view.getKinhNghiem();

            if (maGiangVien == null || maGiangVien.trim().isEmpty() ||
                hoTen == null || hoTen.trim().isEmpty() ||
                sdt == null || sdt.trim().isEmpty() ||
                email == null || email.trim().isEmpty() ||
                diaChi == null || diaChi.trim().isEmpty() ||
                trinhDo == null || trinhDo.trim().isEmpty() ||
                bangCap == null || bangCap.trim().isEmpty() ||
                kinhNghiem == null || kinhNghiem.trim().isEmpty() ||
                view.getNgaySinh() == null) {
                throw new CustomException(CustomException.INVALID_DATA, "Không đủ thông tin!");
            }

            GiangVien gv = new GiangVien(
                maGiangVien,
                hoTen,
                view.getNgaySinh(),
                view.getGioiTinh(),
                sdt,
                email,
                diaChi,
                trinhDo,
                bangCap,
                Integer.parseInt(kinhNghiem)
            );
            them(gv);
        } catch (CustomException e) {
            view.showMessage(e.getMessage());
        } catch (NumberFormatException e) {
            view.showMessage("Kinh nghiệm phải là số!");
        }
    }

    private void suaGiangVien() {
        try {
            String maGiangVien = view.getMaGiangVien();
            if (maGiangVien == null || maGiangVien.trim().isEmpty()) {
                throw new CustomException(CustomException.INVALID_DATA, "Vui lòng nhập mã giảng viên!");
            }

            int confirm = JOptionPane.showConfirmDialog(
                view,
                "Bạn có chắc chắn muốn sửa giảng viên với mã: " + maGiangVien + "?",
                "Xác nhận sửa",
                JOptionPane.YES_NO_OPTION
            );

            if (confirm != JOptionPane.YES_OPTION) {
                return;
            }

            if (view.getNgaySinh() == null) {
                throw new CustomException(CustomException.INVALID_DATA, "Vui lòng chọn ngày sinh!");
            }
            GiangVien gv = new GiangVien(
                maGiangVien,
                view.getHoTen(),
                view.getNgaySinh(),
                view.getGioiTinh(),
                view.getSdt(),
                view.getEmail(),
                view.getDiaChi(),
                view.getTrinhDo(),
                view.getBangCap(),
                Integer.parseInt(view.getKinhNghiem())
            );
            sua(gv);
        } catch (CustomException e) {
            view.showMessage(e.getMessage());
        } catch (NumberFormatException e) {
            view.showMessage("Kinh nghiệm phải là số!");
        }
    }

    private void xoaGiangVien() {
        try {
            String maGiangVien = view.getMaGiangVien();
            if (maGiangVien == null || maGiangVien.trim().isEmpty()) {
                throw new CustomException(CustomException.INVALID_DATA, "Vui lòng nhập mã giảng viên!");
            }

            int confirm = JOptionPane.showConfirmDialog(
                view,
                "Bạn có chắc chắn muốn xóa giảng viên với mã: " + maGiangVien + "?",
                "Xác nhận xóa",
                JOptionPane.YES_NO_OPTION
            );

            if (confirm != JOptionPane.YES_OPTION) {
                return;
            }

            xoa(maGiangVien);
        } catch (CustomException e) {
            view.showMessage(e.getMessage());
        }
    }

    private void timKiemGiangVien() {
        GiangVien gv = timKiem(view.getMaGiangVien());
        if (gv != null) {
            StringBuilder message = new StringBuilder(gv.toString());
            if (quanLyKhoaHoc != null) {
                message.append("\nCác khóa học giảng dạy: ");
                boolean hasKhoaHoc = false;
                for (KhoaHoc kh : quanLyKhoaHoc.getDanhSachKhoaHoc()) {
                    if (gv.getMaGiangVien().equals(kh.getMaGiangVien())) {
                        message.append(kh.getTenKhoa()).append(", ");
                        hasKhoaHoc = true;
                    }
                }
                if (!hasKhoaHoc) {
                    message.append("Không có khóa học nào.");
                } else {
                    message.setLength(message.length() - 2);
                }
            }
            view.showMessage(message.toString());
        } else {
            view.showMessage("Không tìm thấy giảng viên!");
        }
    }

    private void capNhatBang() {
        Object[][] data = new Object[danhSachGiangVien.size()][9];
        for (int i = 0; i < danhSachGiangVien.size(); i++) {
            GiangVien gv = danhSachGiangVien.get(i);
            StringBuilder khoaHocStr = new StringBuilder();
            if (quanLyKhoaHoc != null) {
                for (KhoaHoc kh : quanLyKhoaHoc.getDanhSachKhoaHoc()) {
                    if (gv.getMaGiangVien().equals(kh.getMaGiangVien())) {
                        khoaHocStr.append(kh.getTenKhoa()).append(", ");
                    }
                }
                if (khoaHocStr.length() > 0) {
                    khoaHocStr.setLength(khoaHocStr.length() - 2);
                }
            }
            data[i] = new Object[]{
                gv.getMaGiangVien(),
                gv.getHoTen(),
                gv.getSdt(),
                gv.getEmail(),
                gv.getDiaChi(),
                gv.getTrinhDo(),
                gv.getBangCap(),
                gv.getKinhNghiem(),
                khoaHocStr.toString()
            };
        }
        view.setTableData(data);
    }

    public ArrayList<GiangVien> getDanhSachGiangVien() {
        return danhSachGiangVien;
    }
}