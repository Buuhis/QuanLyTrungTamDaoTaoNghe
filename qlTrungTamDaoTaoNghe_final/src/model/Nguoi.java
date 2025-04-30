package model;

import java.util.Date;

public abstract class Nguoi {
    protected String hoTen;
    protected Date ngaySinh;
    protected boolean gioiTinh; // true: Nam, false: Nữ
    protected String sdt;
    protected String email;
    protected String diaChi;

    // Constructor - removed ma parameter
    public Nguoi(String hoTen, Date ngaySinh, boolean gioiTinh, String sdt, String email, String diaChi) {
        this.hoTen = hoTen;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.sdt = sdt;
        this.email = email;
        this.diaChi = diaChi;
    }

    // Removed getMa() and setMa()
    public String getHoTen() { return hoTen; }
    public void setHoTen(String hoTen) { this.hoTen = hoTen; }
    public Date getNgaySinh() { return ngaySinh; }
    public void setNgaySinh(Date ngaySinh) { this.ngaySinh = ngaySinh; }
    public boolean isGioiTinh() { return gioiTinh; }
    public void setGioiTinh(boolean gioiTinh) { this.gioiTinh = gioiTinh; }
    public String getSdt() { return sdt; }
    public void setSdt(String sdt) { this.sdt = sdt; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getDiaChi() { return diaChi; }
    public void setDiaChi(String diaChi) { this.diaChi = diaChi; }

    @Override
    public String toString() {
        return "Ho ten: " + hoTen + ", Ngay sinh: " + ngaySinh + 
               ", Gioi tinh: " + (gioiTinh ? "Nam" : "Nu") + ", SDT: " + sdt + 
               ", Email: " + email + ", Dia chi: " + diaChi;
    }
}