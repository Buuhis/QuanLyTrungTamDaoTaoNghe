package controller;

import model.HocVien;
import model.KhoaHoc;
import model.IQuanLy;
import view.HocVienView;
import util.FileManager;
import util.CustomException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class QuanLyHocVien implements IQuanLy<HocVien> {
    private ArrayList<HocVien> danhSachHocVien;
    private HocVienView view;
    private QuanLyKhoaHoc quanLyKhoaHoc;

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

    public QuanLyHocVien(HocVienView hocVienView, QuanLyKhoaHoc quanLyKhoaHoc) throws CustomException {
        this.view = hocVienView;
        this.quanLyKhoaHoc = quanLyKhoaHoc;
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
        view.addDatLaiListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.resetFields();
            }
        });
        view.addCapNhatListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { hienThiThongTinHocVien(); }
        });

        if (quanLyKhoaHoc != null) {
            ArrayList<KhoaHoc> danhSachKhoaHoc = quanLyKhoaHoc.getDanhSachKhoaHoc();
            String[] maKhoaHoc = new String[danhSachKhoaHoc.size()];
            for (int i = 0; i < danhSachKhoaHoc.size(); i++) {
                maKhoaHoc[i] = danhSachKhoaHoc.get(i).getMaKhoa();
            }
            view.setKhoaHocItems(maKhoaHoc);

            view.addKhoaHocListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String maKhoa = view.getMaKhoaHoc();
                    if (maKhoa != null) {
                        KhoaHoc kh = quanLyKhoaHoc.timKiem(maKhoa);
                        if (kh != null) {
                            view.setTenKhoaHoc(kh.getTenKhoa());
                        } else {
                            view.setTenKhoaHoc("");
                        }
                    }
                }
            });
        }

        capNhatBang();
    }

    private void hienThiThongTinHocVien() {
        int selectedRow = view.getTable().getSelectedRow();
        if (selectedRow >= 0) {
            view.setMaHocVien((String) view.getTable().getValueAt(selectedRow, 0));
            view.setHoTen((String) view.getTable().getValueAt(selectedRow, 1));
            view.setSdt((String) view.getTable().getValueAt(selectedRow, 2));
            view.setEmail((String) view.getTable().getValueAt(selectedRow, 3));
            view.setDiaChi((String) view.getTable().getValueAt(selectedRow, 4));
            view.setKetQuaDanhGia((String) view.getTable().getValueAt(selectedRow, 5));
            view.setMaKhoaHoc((String) view.getTable().getValueAt(selectedRow, 6));
            view.setTenKhoaHoc((String) view.getTable().getValueAt(selectedRow, 7));
            view.setSoBuoiVang(String.valueOf(view.getTable().getValueAt(selectedRow, 8)));

            HocVien hv = timKiem(view.getMaHocVien());
            if (hv != null) {
                view.setGioiTinh(hv.isGioiTinh());
                view.setNgaySinh(hv.getNgaySinh());
            }
        } else {
            view.showMessage("Vui lòng chọn một học viên từ bảng trước khi bấm Cập nhật!");
        }
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
            String maHocVien = view.getMaHocVien();
            String hoTen = view.getHoTen();
            String sdt = view.getSdt();
            String email = view.getEmail();
            String diaChi = view.getDiaChi();
            String ketQuaDanhGia = view.getKetQuaDanhGia();
            String maKhoaHoc = view.getMaKhoaHoc();
            String soBuoiVangStr = view.getSoBuoiVang();

            if (maHocVien == null || maHocVien.trim().isEmpty() ||
                hoTen == null || hoTen.trim().isEmpty() ||
                sdt == null || sdt.trim().isEmpty() ||
                email == null || email.trim().isEmpty() ||
                diaChi == null || diaChi.trim().isEmpty() ||
                ketQuaDanhGia == null || ketQuaDanhGia.trim().isEmpty() ||
                view.getNgaySinh() == null ||
                maKhoaHoc == null || maKhoaHoc.trim().isEmpty()) {
                throw new CustomException(CustomException.INVALID_DATA, "Không đủ thông tin!");
            }

            int soBuoiVang = 0;
            if (!soBuoiVangStr.trim().isEmpty()) {
                soBuoiVang = Integer.parseInt(soBuoiVangStr);
                if (soBuoiVang < 0) {
                    throw new CustomException(CustomException.INVALID_DATA, "Số buổi vắng không được âm!");
                }
            }

            HocVien hv = new HocVien(
                maHocVien,
                hoTen,
                view.getNgaySinh(),
                view.getGioiTinh(),
                sdt,
                email,
                diaChi,
                ketQuaDanhGia,
                maKhoaHoc,
                soBuoiVang
            );
            them(hv);
        } catch (NumberFormatException e) {
            view.showMessage("Số buổi vắng phải là một số hợp lệ!");
        } catch (CustomException e) {
            view.showMessage(e.getMessage());
        }
    }

    private void suaHocVien() {
        try {
            String maHocVien = view.getMaHocVien();
            if (maHocVien == null || maHocVien.trim().isEmpty()) {
                throw new CustomException(CustomException.INVALID_DATA, "Vui lòng nhập mã học viên!");
            }

            int confirm = JOptionPane.showConfirmDialog(
                view,
                "Bạn có chắc chắn muốn sửa học viên với mã: " + maHocVien + "?",
                "Xác nhận sửa",
                JOptionPane.YES_NO_OPTION
            );

            if (confirm != JOptionPane.YES_OPTION) {
                return;
            }

            if (view.getNgaySinh() == null) {
                throw new CustomException(CustomException.INVALID_DATA, "Vui lòng chọn ngày sinh!");
            }
            String maKhoaHoc = view.getMaKhoaHoc();
            if (maKhoaHoc == null || maKhoaHoc.trim().isEmpty()) {
                throw new CustomException(CustomException.INVALID_DATA, "Vui lòng chọn khóa học!");
            }

            HocVien existingHv = timKiem(maHocVien);
            if (existingHv == null) {
                throw new CustomException(CustomException.INVALID_DATA, "Không tìm thấy học viên!");
            }

            String soBuoiVangStr = view.getSoBuoiVang();
            int soBuoiVang = existingHv.getSoBuoiVang();
            if (!soBuoiVangStr.trim().isEmpty()) {
                soBuoiVang = Integer.parseInt(soBuoiVangStr);
                if (soBuoiVang < 0) {
                    throw new CustomException(CustomException.INVALID_DATA, "Số buổi vắng không được âm!");
                }
            }

            HocVien hv = new HocVien(
                maHocVien,
                view.getHoTen(),
                view.getNgaySinh(),
                view.getGioiTinh(),
                view.getSdt(),
                view.getEmail(),
                view.getDiaChi(),
                view.getKetQuaDanhGia(),
                maKhoaHoc,
                soBuoiVang
            );
            sua(hv);
        } catch (NumberFormatException e) {
            view.showMessage("Số buổi vắng phải là một số hợp lệ!");
        } catch (CustomException e) {
            view.showMessage(e.getMessage());
        }
    }

    private void xoaHocVien() {
        try {
            String maHocVien = view.getMaHocVien();
            if (maHocVien == null || maHocVien.trim().isEmpty()) {
                throw new CustomException(CustomException.INVALID_DATA, "Vui lòng nhập mã học viên!");
            }

            int confirm = JOptionPane.showConfirmDialog(
                view,
                "Bạn có chắc chắn muốn xóa học viên với mã: " + maHocVien + "?",
                "Xác nhận xóa",
                JOptionPane.YES_NO_OPTION
            );

            if (confirm != JOptionPane.YES_OPTION) {
                return;
            }

            xoa(maHocVien);
        } catch (CustomException e) {
            view.showMessage(e.getMessage());
        }
    }

    private void timKiemHocVien() {
        HocVien hv = timKiem(view.getMaHocVien());
        if (hv != null) {
            view.showMessage(hv.toString());
            view.setSoBuoiVang(String.valueOf(hv.getSoBuoiVang()));
        } else {
            view.showMessage("Không tìm thấy học viên!");
        }
    }

    private void capNhatBang() {
        Object[][] data = new Object[danhSachHocVien.size()][9];
        for (int i = 0; i < danhSachHocVien.size(); i++) {
            HocVien hv = danhSachHocVien.get(i);
            String maKhoaHoc = hv.getMaKhoaHoc();
            String tenKhoaHoc = "";
            
            if (quanLyKhoaHoc != null && maKhoaHoc != null) {
                KhoaHoc kh = quanLyKhoaHoc.timKiem(maKhoaHoc);
                if (kh != null) {
                    tenKhoaHoc = kh.getTenKhoa();
                }
            }

            data[i] = new Object[]{
                hv.getMaHocVien(),
                hv.getHoTen(),
                hv.getSdt(),
                hv.getEmail(),
                hv.getDiaChi(),
                hv.getKetQuaDanhGia(),
                maKhoaHoc != null ? maKhoaHoc : "",
                tenKhoaHoc,
                hv.getSoBuoiVang()
            };
        }
        view.setTableData(data);
    }

    public ArrayList<HocVien> getDanhSachHocVien() {
        return danhSachHocVien;
    }
}