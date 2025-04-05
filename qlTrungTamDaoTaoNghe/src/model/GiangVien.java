package model;

import java.util.Date;

public class GiangVien extends Nguoi {
    private String maGiangVien;
    private String trinhDo;
    private String bangCap;
    private int kinhNghiem;

    // Constructor
    public GiangVien(String ma, String hoTen, Date ngaySinh, boolean gioiTinh, String sdt, String email, String diaChi, 
                     String maGiangVien, String trinhDo, String bangCap, int kinhNghiem) {
        super(ma, hoTen, ngaySinh, gioiTinh, sdt, email, diaChi);
        this.maGiangVien = maGiangVien;
        this.trinhDo = trinhDo;
        this.bangCap = bangCap;
        this.kinhNghiem = kinhNghiem;
    }

    // Getter và Setter
    public String getMaGiangVien() { return maGiangVien; }
    public void setMaGiangVien(String maGiangVien) { this.maGiangVien = maGiangVien; }
    public String getTrinhDo() { return trinhDo; }
    public void setTrinhDo(String trinhDo) { this.trinhDo = trinhDo; }
    public String getBangCap() { return bangCap; }
    public void setBangCap(String bangCap) { this.bangCap = bangCap; }
    public int getKinhNghiem() { return kinhNghiem; }
    public void setKinhNghiem(int kinhNghiem) { this.kinhNghiem = kinhNghiem; }

    @Override
    public String toString() {
        return super.toString() + ", Ma giang vien: " + maGiangVien + ", Trinh do: " + trinhDo + 
               ", Bang cap: " + bangCap + ", Kinh nghiem: " + kinhNghiem;
    }
}