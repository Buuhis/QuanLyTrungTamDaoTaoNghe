package model;

import java.util.Date;

public class DangKyHoc {
    private Date ngayDangKy;
    private String trangThai;

    // Constructor
    public DangKyHoc(Date ngayDangKy, String trangThai) {
        this.ngayDangKy = ngayDangKy;
        this.trangThai = trangThai;
    }

    // Getter và Setter
    public Date getNgayDangKy() { return ngayDangKy; }
    public void setNgayDangKy(Date ngayDangKy) { this.ngayDangKy = ngayDangKy; }
    public String getTrangThai() { return trangThai; }
    public void setTrangThai(String trangThai) { this.trangThai = trangThai; }

    @Override
    public String toString() {
        return "Ngay dang ky: " + ngayDangKy + ", Trang thai: " + trangThai;
    }
}