package model;

import java.util.Date;

public class DiemDanh {
    private Date ngay;
    private String trangThai;

    // Constructor
    public DiemDanh(Date ngay, String trangThai) {
        this.ngay = ngay;
        this.trangThai = trangThai;
    }

    // Getter và Setter
    public Date getNgay() { return ngay; }
    public void setNgay(Date ngay) { this.ngay = ngay; }
    public String getTrangThai() { return trangThai; }
    public void setTrangThai(String trangThai) { this.trangThai = trangThai; }

    @Override
    public String toString() {
        return "Ngay: " + ngay + ", Trang thai: " + trangThai;
    }
}