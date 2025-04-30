package model;

public class KetQuaDanhGia {
    private double diemSo;
    private String nhanXet;

    // Constructor
    public KetQuaDanhGia(double diemSo, String nhanXet) {
        this.diemSo = diemSo;
        this.nhanXet = nhanXet;
    }

    // Getter và Setter
    public double getDiemSo() { return diemSo; }
    public void setDiemSo(double diemSo) { this.diemSo = diemSo; }
    public String getNhanXet() { return nhanXet; }
    public void setNhanXet(String nhanXet) { this.nhanXet = nhanXet; }

    @Override
    public String toString() {
        return "Diem so: " + diemSo + ", Nhan xet: " + nhanXet;
    }
}