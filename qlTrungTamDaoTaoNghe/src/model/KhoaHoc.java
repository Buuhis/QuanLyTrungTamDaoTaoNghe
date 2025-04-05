package model;

public class KhoaHoc {
    private String maKhoa;
    private String tenKhoa;
    private int thoiLuong; // Đơn vị: giờ
    private double hocPhi;
    private String moTa;

    // Constructor
    public KhoaHoc(String maKhoa, String tenKhoa, int thoiLuong, double hocPhi, String moTa) {
        this.maKhoa = maKhoa;
        this.tenKhoa = tenKhoa;
        this.thoiLuong = thoiLuong;
        this.hocPhi = hocPhi;
        this.moTa = moTa;
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

    @Override
    public String toString() {
        return "Ma khoa: " + maKhoa + ", Ten khoa: " + tenKhoa + ", Thoi luong: " + thoiLuong + 
               ", Hoc phi: " + hocPhi + ", Mo ta: " + moTa;
    }
}