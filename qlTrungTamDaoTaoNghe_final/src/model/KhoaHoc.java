package model;

public class KhoaHoc {
    private String maKhoa;
    private String tenKhoa;
    private int thoiLuong; // Đơn vị: giờ
    private double hocPhi;
    private String moTa;
    private String maGiangVien; // Thêm trường mã giảng viên

    // Constructor
    public KhoaHoc(String maKhoa, String tenKhoa, int thoiLuong, double hocPhi, String moTa, String maGiangVien) {
        this.maKhoa = maKhoa;
        this.tenKhoa = tenKhoa;
        this.thoiLuong = thoiLuong; // Sửa lỗi: gán đúng tham số
        this.hocPhi = hocPhi;       // Sửa lỗi: gán đúng tham số
        this.moTa = moTa;
        this.maGiangVien = maGiangVien; // Sửa lỗi: gán đúng tham số
    }

    // Getter và Setter
    public String getMaKhoa() { return maKhoa; }
    public void setMaKhoa(String maKhoa) { this.maKhoa = maKhoa; }
    public String getTenKhoa() { return tenKhoa; }
    public void setTenKhoa(String tenKhoa) { this.tenKhoa = tenKhoa; }
    public int getThoiLuong() { return thoiLuong; }
    public void setThoiLuong(int thoiLuong) { this.thoiLuong = thoiLuong; }
    public double getHocPhi() { return hocPhi; }
    public void setHocPhi(double hocPhi) { this.hocPhi = hocPhi; }
    public String getMoTa() { return moTa; }
    public void setMoTa(String moTa) { this.moTa = moTa; }
    public String getMaGiangVien() { return maGiangVien; }
    public void setMaGiangVien(String maGiangVien) { this.maGiangVien = maGiangVien; }

    @Override
    public String toString() {
        return "Ma khoa: " + maKhoa + ", Ten khoa: " + tenKhoa + ", Thoi luong: " + thoiLuong + 
               ", Hoc phi: " + hocPhi + ", Mo ta: " + moTa + ", Ma giang vien: " + (maGiangVien != null ? maGiangVien : "");
    }
}