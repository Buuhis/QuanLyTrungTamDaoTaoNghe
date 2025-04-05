package controller;

import model.GiangVien;
import model.IQuanLy;
import view.GiangVienView;
import util.FileManager;
import util.CustomException;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuanLyGiangVien implements IQuanLy<GiangVien> {
    private ArrayList<GiangVien> danhSachGiangVien;
    private GiangVienView view;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    public QuanLyGiangVien(GiangVienView view) throws CustomException {
        this.view = view;
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
            if (view.getNgaySinh() == null) {
                throw new CustomException(CustomException.INVALID_DATA, "Vui lòng chọn ngày sinh!");
            }
            GiangVien gv = new GiangVien(
                view.getMaGiangVien(),
                view.getHoTen(),
                view.getNgaySinh(),
                view.getGioiTinh(),
                view.getSdt(),
                view.getEmail(),
                view.getDiaChi(),
                view.getMaGiangVien(),
                view.getTrinhDo(),
                view.getBangCap(),
                Integer.parseInt(view.getKinhNghiem())
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
            if (view.getNgaySinh() == null) {
                throw new CustomException(CustomException.INVALID_DATA, "Vui lòng chọn ngày sinh!");
            }
            GiangVien gv = new GiangVien(
                view.getMaGiangVien(),
                view.getHoTen(),
                view.getNgaySinh(),
                view.getGioiTinh(),
                view.getSdt(),
                view.getEmail(),
                view.getDiaChi(),
                view.getMaGiangVien(),
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
            xoa(view.getMaGiangVien());
        } catch (CustomException e) {
            view.showMessage(e.getMessage());
        }
    }

    private void timKiemGiangVien() {
        GiangVien gv = timKiem(view.getMaGiangVien());
        if (gv != null) {
            view.showMessage(gv.toString());
        } else {
            view.showMessage("Không tìm thấy giảng viên!");
        }
    }

    private void capNhatBang() {
        Object[][] data = new Object[danhSachGiangVien.size()][8];
        for (int i = 0; i < danhSachGiangVien.size(); i++) {
            GiangVien gv = danhSachGiangVien.get(i);
            data[i] = new Object[]{
                gv.getMaGiangVien(),
                gv.getHoTen(),
                gv.getSdt(),
                gv.getEmail(),
                gv.getDiaChi(),
                gv.getTrinhDo(),
                gv.getBangCap(),
                gv.getKinhNghiem()
            };
        }
        view.setTableData(data);
    }

    public ArrayList<GiangVien> getDanhSachGiangVien() {
        return danhSachGiangVien;
    }
}
