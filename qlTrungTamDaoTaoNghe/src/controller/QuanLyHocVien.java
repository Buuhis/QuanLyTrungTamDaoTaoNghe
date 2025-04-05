package controller;

import model.HocVien;
import model.IQuanLy;
import view.HocVienView;
import util.FileManager;
import util.CustomException;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuanLyHocVien implements IQuanLy<HocVien> {
    private ArrayList<HocVien> danhSachHocVien;
    private HocVienView view;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    public QuanLyHocVien(HocVienView view) throws CustomException {
        this.view = view;
        this.danhSachHocVien = new ArrayList<>();
        try {
            danhSachHocVien = FileManager.readHocVienFromFile("hocvien.txt");
        } catch (Exception e) {
            throw new CustomException(CustomException.FILE_ERROR, "Không thể đọc file học viên: " + e.getMessage());
        }
        initView();
    }

    private void initView() {
        view.addThemListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { themHocVien(); }
        });
        view.addSuaListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { suaHocVien(); }
        });
        view.addXoaListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { xoaHocVien(); }
        });
        view.addTimKiemListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { timKiemHocVien(); }
        });
        capNhatBang();
    }

    @Override
    public void them(HocVien hv) throws CustomException {
        for (HocVien existing : danhSachHocVien) {
            if (existing.getMaHocVien().equals(hv.getMaHocVien())) {
                throw new CustomException(CustomException.DUPLICATE_ID, "Mã học viên đã tồn tại!");
            }
        }
        danhSachHocVien.add(hv);
        try {
            FileManager.writeHocVienToFile(danhSachHocVien, "hocvien.txt");
        } catch (Exception e) {
            throw new CustomException(CustomException.FILE_ERROR, "Không thể ghi file học viên: " + e.getMessage());
        }
        capNhatBang();
        view.showMessage("Thêm học viên thành công!");
    }

    @Override
    public void sua(HocVien hv) throws CustomException {
        for (int i = 0; i < danhSachHocVien.size(); i++) {
            if (danhSachHocVien.get(i).getMaHocVien().equals(hv.getMaHocVien())) {
                danhSachHocVien.set(i, hv);
                try {
                    FileManager.writeHocVienToFile(danhSachHocVien, "hocvien.txt");
                } catch (Exception e) {
                    throw new CustomException(CustomException.FILE_ERROR, "Không thể ghi file học viên: " + e.getMessage());
                }
                capNhatBang();
                view.showMessage("Sửa học viên thành công!");
                return;
            }
        }
        throw new CustomException(CustomException.INVALID_DATA, "Không tìm thấy học viên để sửa!");
    }

    @Override
    public void xoa(String ma) throws CustomException {
        for (HocVien hv : danhSachHocVien) {
            if (hv.getMaHocVien().equals(ma)) {
                danhSachHocVien.remove(hv);
                try {
                    FileManager.writeHocVienToFile(danhSachHocVien, "hocvien.txt");
                } catch (Exception e) {
                    throw new CustomException(CustomException.FILE_ERROR, "Không thể ghi file học viên: " + e.getMessage());
                }
                capNhatBang();
                view.showMessage("Xóa học viên thành công!");
                return;
            }
        }
        throw new CustomException(CustomException.INVALID_DATA, "Không tìm thấy học viên để xóa!");
    }

    @Override
    public HocVien timKiem(String ma) {
        for (HocVien hv : danhSachHocVien) {
            if (hv.getMaHocVien().equals(ma)) {
                return hv;
            }
        }
        return null;
    }

    private void themHocVien() {
        try {
            if (view.getNgaySinh() == null || view.getNgayDangKy() == null) {
                throw new CustomException(CustomException.INVALID_DATA, "Vui lòng chọn ngày sinh và ngày đăng ký!");
            }
            HocVien hv = new HocVien(
                view.getMaHocVien(),
                view.getHoTen(),
                view.getNgaySinh(),
                view.getGioiTinh(),
                view.getSdt(),
                view.getEmail(),
                view.getDiaChi(),
                view.getMaHocVien(),
                view.getNgayDangKy(),
                view.getTrangThai()
            );
            them(hv);
        } catch (CustomException e) {
            view.showMessage(e.getMessage());
        }
    }

    private void suaHocVien() {
        try {
            if (view.getNgaySinh() == null || view.getNgayDangKy() == null) {
                throw new CustomException(CustomException.INVALID_DATA, "Vui lòng chọn ngày sinh và ngày đăng ký!");
            }
            HocVien hv = new HocVien(
                view.getMaHocVien(),
                view.getHoTen(),
                view.getNgaySinh(),
                view.getGioiTinh(),
                view.getSdt(),
                view.getEmail(),
                view.getDiaChi(),
                view.getMaHocVien(),
                view.getNgayDangKy(),
                view.getTrangThai()
            );
            sua(hv);
        } catch (CustomException e) {
            view.showMessage(e.getMessage());
        }
    }

    private void xoaHocVien() {
        try {
            xoa(view.getMaHocVien());
        } catch (CustomException e) {
            view.showMessage(e.getMessage());
        }
    }

    private void timKiemHocVien() {
        HocVien hv = timKiem(view.getMaHocVien());
        if (hv != null) {
            view.showMessage(hv.toString());
        } else {
            view.showMessage("Không tìm thấy học viên!");
        }
    }

    private void capNhatBang() {
        Object[][] data = new Object[danhSachHocVien.size()][7];
        for (int i = 0; i < danhSachHocVien.size(); i++) {
            HocVien hv = danhSachHocVien.get(i);
            data[i] = new Object[]{
                hv.getMaHocVien(),
                hv.getHoTen(),
                hv.getSdt(),
                hv.getEmail(),
                hv.getDiaChi(),
                dateFormat.format(hv.getNgayDangKy()),
                hv.getTrangThai()
            };
        }
        view.setTableData(data);
    }

    public ArrayList<HocVien> getDanhSachHocVien() {
        return danhSachHocVien;
    }
}
