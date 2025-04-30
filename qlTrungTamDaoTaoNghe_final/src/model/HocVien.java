package model;

import java.util.Date;

public class HocVien extends Nguoi {
    private String maHocVien;
    private String ketQuaDanhGia;
    private String maKhoaHoc;
    private int soBuoiVang; // Thêm thuộc tính số buổi vắng

    public HocVien(String maHocVien, String hoTen, Date ngaySinh, boolean gioiTinh, String sdt, String email, String diaChi, 
                   String ketQuaDanhGia, String maKhoaHoc, int soBuoiVang) {
        super(hoTen, ngaySinh, gioiTinh, sdt, email, diaChi);
        this.maHocVien = maHocVien;
        this.ketQuaDanhGia = ketQuaDanhGia;
        this.maKhoaHoc = maKhoaHoc;
        this.soBuoiVang = soBuoiVang;
    }

    public String getMaHocVien() { return maHocVien; }
    public void setMaHocVien(String maHocVien) { this.maHocVien = maHocVien; }
    public String getKetQuaDanhGia() { return ketQuaDanhGia; }
    public void setKetQuaDanhGia(String ketQuaDanhGia) { this.ketQuaDanhGia = ketQuaDanhGia; }
    public String getMaKhoaHoc() { return maKhoaHoc; }
    public void setMaKhoaHoc(String maKhoaHoc) { this.maKhoaHoc = maKhoaHoc; }
    public int getSoBuoiVang() { return soBuoiVang; }
    public void setSoBuoiVang(int soBuoiVang) { this.soBuoiVang = soBuoiVang; }

    @Override
    public String toString() {
        return super.toString() + ", Mã học viên: " + maHocVien + 
               ", Kết quả đánh giá: " + ketQuaDanhGia + ", Mã khóa học: " + maKhoaHoc + 
               ", Số buổi vắng: " + soBuoiVang;
    }
}