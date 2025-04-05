package model;

import java.util.Date;

public class HocVien extends Nguoi {
    private String maHocVien;
    private Date ngayDangKy;
    private String trangThai;

    // Constructor
    public HocVien(String ma, String hoTen, Date ngaySinh, boolean gioiTinh, String sdt, String email, String diaChi, 
                   String maHocVien, Date ngayDangKy, String trangThai) {
        super(ma, hoTen, ngaySinh, gioiTinh, sdt, email, diaChi);
        this.maHocVien = maHocVien;
        this.ngayDangKy = ngayDangKy;
        this.trangThai = trangThai;
    }

    // Getter và Setter
    public String getMaHocVien() { return maHocVien; }
    public void setMaHocVien(String maHocVien) { this.maHocVien = maHocVien; }
    public Date getNgayDangKy() { return ngayDangKy; }
    public void setNgayDangKy(Date ngayDangKy) { this.ngayDangKy = ngayDangKy; }
    public String getTrangThai() { return trangThai; }
    public void setTrangThai(String trangThai) { this.trangThai = trangThai; }

    @Override
    public String toString() {
        return super.toString() + ", Ma hoc vien: " + maHocVien + ", Ngay dang ky: " + ngayDangKy + 
               ", Trang thai: " + trangThai;
    }
}