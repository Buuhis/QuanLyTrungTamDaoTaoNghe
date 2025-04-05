package controller;

import model.KhoaHoc;
import model.IQuanLy;
import view.KhoaHocView;
import util.FileManager;
import util.CustomException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuanLyKhoaHoc implements IQuanLy<KhoaHoc> {
    private ArrayList<KhoaHoc> danhSachKhoaHoc;
    private KhoaHocView view;

    public QuanLyKhoaHoc(KhoaHocView view) throws CustomException {
        this.view = view;
        this.danhSachKhoaHoc = new ArrayList<>();
        try {
            danhSachKhoaHoc = FileManager.readKhoaHocFromFile("khoahoc.txt");
        } catch (Exception e) {
            throw new CustomException(CustomException.FILE_ERROR, "Không thể đọc file khóa học: " + e.getMessage());
        }
        initView();
    }

    private void initView() {
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
        capNhatBang();
    }

    @Override
    public void them(KhoaHoc kh) throws CustomException {
        for (KhoaHoc existing : danhSachKhoaHoc) {
            if (existing.getMaKhoa().equals(kh.getMaKhoa())) {
                throw new CustomException(CustomException.DUPLICATE_ID, "Mã khóa học đã tồn tại!");
            }
        }
        danhSachKhoaHoc.add(kh);
        try {
            FileManager.writeKhoaHocToFile(danhSachKhoaHoc, "khoahoc.txt");
        } catch (Exception e) {
            throw new CustomException(CustomException.FILE_ERROR, "Không thể ghi file khóa học: " + e.getMessage());
        }
        capNhatBang();
        view.showMessage("Thêm khóa học thành công!");
    }

    @Override
    public void sua(KhoaHoc kh) throws CustomException {
        for (int i = 0; i < danhSachKhoaHoc.size(); i++) {
            if (danhSachKhoaHoc.get(i).getMaKhoa().equals(kh.getMaKhoa())) {
                danhSachKhoaHoc.set(i, kh);
                try {
                    FileManager.writeKhoaHocToFile(danhSachKhoaHoc, "khoahoc.txt");
                } catch (Exception e) {
                    throw new CustomException(CustomException.FILE_ERROR, "Không thể ghi file khóa học: " + e.getMessage());
                }
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
            KhoaHoc kh = new KhoaHoc(
                view.getMaKhoa(),
                view.getTenKhoa(),
                Integer.parseInt(view.getThoiLuong()),
                Double.parseDouble(view.getHocPhi()),
                view.getMoTa()
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
            KhoaHoc kh = new KhoaHoc(
                view.getMaKhoa(),
                view.getTenKhoa(),
                Integer.parseInt(view.getThoiLuong()),
                Double.parseDouble(view.getHocPhi()),
                view.getMoTa()
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
            xoa(view.getMaKhoa());
        } catch (CustomException e) {
            view.showMessage(e.getMessage());
        }
    }

    private void timKiemKhoaHoc() {
        KhoaHoc kh = timKiem(view.getMaKhoa()); // Sửa từ "Khoahoc" thành "KhoaHoc"
        if (kh != null) {
            view.showMessage(kh.toString());
        } else {
            view.showMessage("Không tìm thấy khóa học!");
        }
    }

    private void capNhatBang() {
        Object[][] data = new Object[danhSachKhoaHoc.size()][5];
        for (int i = 0; i < danhSachKhoaHoc.size(); i++) {
            KhoaHoc kh = danhSachKhoaHoc.get(i);
            data[i] = new Object[]{kh.getMaKhoa(), kh.getTenKhoa(), kh.getThoiLuong(), kh.getHocPhi(), kh.getMoTa()};
        }
        view.setTableData(data);
    }

    public ArrayList<KhoaHoc> getDanhSachKhoaHoc() {
        return danhSachKhoaHoc;
    }
}