package controller;

import model.KhoaHoc;
import model.GiangVien;
import model.IQuanLy;
import view.KhoaHocView;
import util.FileManager;
import util.CustomException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class QuanLyKhoaHoc implements IQuanLy<KhoaHoc> {
    private ArrayList<KhoaHoc> danhSachKhoaHoc;
    private KhoaHocView view;
    private QuanLyGiangVien quanLyGiangVien;

    public QuanLyKhoaHoc(KhoaHocView view, QuanLyGiangVien quanLyGiangVien) throws CustomException {
        this.view = view;
        this.quanLyGiangVien = quanLyGiangVien;
        this.danhSachKhoaHoc = new ArrayList<>();
        try {
            danhSachKhoaHoc = FileManager.readKhoaHocFromFile("khoahoc.txt");
        } catch (Exception e) {
            throw new CustomException(CustomException.FILE_ERROR, "Không thể đọc file khóa học: " + e.getMessage());
        }
        initView();
    }

    private void initView() {
        updateGiangVienComboBox();

        view.addThemListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { themKhoaHoc(); }
        });
        view.addSuaListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { suaKhoaHoc(); }
        });
        view.addXoaListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { xoaKhoaHoc(); }
        });
        view.addTimKiemListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { timKiemKhoaHoc(); }
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
                    String maKhoa = view.getTable().getValueAt(selectedRow, 0).toString();
                    KhoaHoc kh = timKiem(maKhoa);
                    view.updateFieldsFromSelectedRow(kh);
                } else {
                    view.showMessage("Vui lòng chọn một dòng trong bảng!");
                }
            }
        });

        capNhatBang();
    }

    public void updateGiangVienComboBox() {
        if (quanLyGiangVien != null) {
            ArrayList<GiangVien> danhSachGiangVien = quanLyGiangVien.getDanhSachGiangVien();
            String[] maGiangVien = new String[danhSachGiangVien.size() + 1];
            maGiangVien[0] = "";
            for (int i = 0; i < danhSachGiangVien.size(); i++) {
                maGiangVien[i + 1] = danhSachGiangVien.get(i).getMaGiangVien();
            }
            view.setGiangVienItems(maGiangVien);
        } else {
            view.setGiangVienItems(new String[]{""});
        }
    }

    @Override
    public void them(KhoaHoc kh) throws CustomException {
        for (KhoaHoc existing : danhSachKhoaHoc) {
            if (existing.getMaKhoa().equals(kh.getMaKhoa())) {
                throw new CustomException(CustomException.DUPLICATE_ID, "Mã khóa học đã tồn tại!");
            }
        }
        if (kh.getMaGiangVien() != null && !kh.getMaGiangVien().isEmpty()) {
            GiangVien gv = quanLyGiangVien.timKiem(kh.getMaGiangVien());
            if (gv == null) {
                throw new CustomException(CustomException.INVALID_DATA, "Giảng viên không tồn tại!");
            }
        }
        danhSachKhoaHoc.add(kh);
        try {
            FileManager.writeKhoaHocToFile(danhSachKhoaHoc, "khoahoc.txt");
        } catch (Exception e) {
            throw new CustomException(CustomException.FILE_ERROR, "Không thể ghi file khóa học: " + e.getMessage());
        }
        updateGiangVienComboBox();
        capNhatBang();
        view.showMessage("Thêm khóa học thành công!");
    }

    @Override
    public void sua(KhoaHoc kh) throws CustomException {
        for (int i = 0; i < danhSachKhoaHoc.size(); i++) {
            if (danhSachKhoaHoc.get(i).getMaKhoa().equals(kh.getMaKhoa())) {
                if (kh.getMaGiangVien() != null && !kh.getMaGiangVien().isEmpty()) {
                    GiangVien gv = quanLyGiangVien.timKiem(kh.getMaGiangVien());
                    if (gv == null) {
                        throw new CustomException(CustomException.INVALID_DATA, "Giảng viên không tồn tại!");
                    }
                }
                danhSachKhoaHoc.set(i, kh);
                try {
                    FileManager.writeKhoaHocToFile(danhSachKhoaHoc, "khoahoc.txt");
                } catch (Exception e) {
                    throw new CustomException(CustomException.FILE_ERROR, "Không thể ghi file khóa học: " + e.getMessage());
                }
                updateGiangVienComboBox();
                capNhatBang();
                view.showMessage("Sửa khóa học thành công!");
                return;
            }
        }
        throw new CustomException(CustomException.INVALID_DATA, "Không tìm thấy khóa học để sửa!");
    }

    @Override
    public void xoa(String ma) throws CustomException {
        for (KhoaHoc kh : danhSachKhoaHoc) {
            if (kh.getMaKhoa().equals(ma)) {
                danhSachKhoaHoc.remove(kh);
                try {
                    FileManager.writeKhoaHocToFile(danhSachKhoaHoc, "khoahoc.txt");
                } catch (Exception e) {
                    throw new CustomException(CustomException.FILE_ERROR, "Không thể ghi file khóa học: " + e.getMessage());
                }
                updateGiangVienComboBox();
                capNhatBang();
                view.showMessage("Xóa khóa học thành công!");
                return;
            }
        }
        throw new CustomException(CustomException.INVALID_DATA, "Không tìm thấy khóa học để xóa!");
    }

    @Override
    public KhoaHoc timKiem(String ma) {
        for (KhoaHoc kh : danhSachKhoaHoc) {
            if (kh.getMaKhoa().equals(ma)) {
                return kh;
            }
        }
        return null;
    }

    private void themKhoaHoc() {
        try {
            String maKhoa = view.getMaKhoa();
            String tenKhoa = view.getTenKhoa();
            String thoiLuong = view.getThoiLuong();
            String hocPhi = view.getHocPhi();
            String moTa = view.getMoTa();
            String maGiangVien = view.getMaGiangVien();

            if (maKhoa == null || maKhoa.trim().isEmpty() ||
                tenKhoa == null || tenKhoa.trim().isEmpty() ||
                thoiLuong == null || thoiLuong.trim().isEmpty() ||
                hocPhi == null || hocPhi.trim().isEmpty() ||
                moTa == null || moTa.trim().isEmpty()) {
                throw new CustomException(CustomException.INVALID_DATA, "Không đủ thông tin!");
            }

            KhoaHoc kh = new KhoaHoc(
                maKhoa,
                tenKhoa,
                Integer.parseInt(thoiLuong),
                Double.parseDouble(hocPhi),
                moTa,
                maGiangVien
            );
            them(kh);
        } catch (CustomException e) {
            view.showMessage(e.getMessage());
        } catch (NumberFormatException e) {
            view.showMessage("Thời lượng và học phí phải là số!");
        }
    }

    private void suaKhoaHoc() {
        try {
            String maKhoa = view.getMaKhoa();
            if (maKhoa == null || maKhoa.trim().isEmpty()) {
                throw new CustomException(CustomException.INVALID_DATA, "Vui lòng nhập mã khóa học!");
            }

            int confirm = JOptionPane.showConfirmDialog(
                view,
                "Bạn có chắc chắn muốn sửa khóa học với mã: " + maKhoa + "?",
                "Xác nhận sửa",
                JOptionPane.YES_NO_OPTION
            );

            if (confirm != JOptionPane.YES_OPTION) {
                return;
            }

            KhoaHoc kh = new KhoaHoc(
                maKhoa,
                view.getTenKhoa(),
                Integer.parseInt(view.getThoiLuong()),
                Double.parseDouble(view.getHocPhi()),
                view.getMoTa(),
                view.getMaGiangVien()
            );
            sua(kh);
        } catch (CustomException e) {
            view.showMessage(e.getMessage());
        } catch (NumberFormatException e) {
            view.showMessage("Thời lượng và học phí phải là số!");
        }
    }

    private void xoaKhoaHoc() {
        try {
            String maKhoa = view.getMaKhoa();
            if (maKhoa == null || maKhoa.trim().isEmpty()) {
                throw new CustomException(CustomException.INVALID_DATA, "Vui lòng nhập mã khóa học!");
            }

            int confirm = JOptionPane.showConfirmDialog(
                view,
                "Bạn có chắc chắn muốn xóa khóa học với mã: " + maKhoa + "?",
                "Xác nhận xóa",
                JOptionPane.YES_NO_OPTION
            );

            if (confirm != JOptionPane.YES_OPTION) {
                return;
            }

            xoa(maKhoa);
        } catch (CustomException e) {
            view.showMessage(e.getMessage());
        }
    }

    private void timKiemKhoaHoc() {
        KhoaHoc kh = timKiem(view.getMaKhoa());
        if (kh != null) {
            view.showMessage(kh.toString());
        } else {
            view.showMessage("Không tìm thấy khóa học!");
        }
    }

    private void capNhatBang() {
        Object[][] data = new Object[danhSachKhoaHoc.size()][6];
        for (int i = 0; i < danhSachKhoaHoc.size(); i++) {
            KhoaHoc kh = danhSachKhoaHoc.get(i);
            String tenGiangVien = "";
            if (kh.getMaGiangVien() != null && !kh.getMaGiangVien().isEmpty()) {
                GiangVien gv = quanLyGiangVien != null ? quanLyGiangVien.timKiem(kh.getMaGiangVien()) : null;
                if (gv != null) {
                    tenGiangVien = gv.getHoTen();
                } else {
                    tenGiangVien = "Không tìm thấy giảng viên";
                }
            }
            data[i] = new Object[]{
                kh.getMaKhoa(),
                kh.getTenKhoa(),
                kh.getThoiLuong(),
                kh.getHocPhi(),
                kh.getMoTa(),
                tenGiangVien
            };
        }
        view.setTableData(data);
    }

    public ArrayList<KhoaHoc> getDanhSachKhoaHoc() {
        return danhSachKhoaHoc;
    }
}