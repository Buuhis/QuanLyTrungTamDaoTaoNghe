package util;

import model.HocVien;
import model.KhoaHoc;
import model.GiangVien;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class FileManager {
    private static final String PATH = "data/";
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    // Ghi danh sách học viên vào file
    public static void writeHocVienToFile(ArrayList<HocVien> danhSach, String fileName) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PATH + fileName))) {
            for (HocVien hv : danhSach) {
                String line = String.join("|",
                    hv.getMaHocVien(),
                    hv.getHoTen(),
                    dateFormat.format(hv.getNgaySinh()),
                    String.valueOf(hv.isGioiTinh()),
                    hv.getSdt(),
                    hv.getEmail(),
                    hv.getDiaChi(),
                    dateFormat.format(hv.getNgayDangKy()),
                    hv.getTrangThai()
                );
                writer.write(line);
                writer.newLine();
            }
        }
    }

    // Đọc danh sách học viên từ file
    public static ArrayList<HocVien> readHocVienFromFile(String fileName) throws IOException {
        ArrayList<HocVien> danhSach = new ArrayList<>();
        File file = new File(PATH + fileName);
        if (!file.exists()) return danhSach;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 9) {
                    HocVien hv = new HocVien(
                        parts[0], // maHocVien
                        parts[1], // hoTen
                        dateFormat.parse(parts[2]), // ngaySinh
                        Boolean.parseBoolean(parts[3]), // gioiTinh
                        parts[4], // sdt
                        parts[5], // email
                        parts[6], // diaChi
                        parts[0], // maHocVien (trùng với ma)
                        dateFormat.parse(parts[7]), // ngayDangKy
                        parts[8] // trangThai
                    );
                    danhSach.add(hv);
                }
            }
        } catch (Exception e) {
            throw new IOException("Lỗi khi đọc file học viên: " + e.getMessage());
        }
        return danhSach;
    }

    // Ghi danh sách khóa học vào file
    public static void writeKhoaHocToFile(ArrayList<KhoaHoc> danhSach, String fileName) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PATH + fileName))) {
            for (KhoaHoc kh : danhSach) {
                String line = String.join("|",
                    kh.getMaKhoa(),
                    kh.getTenKhoa(),
                    String.valueOf(kh.getThoiLuong()),
                    String.valueOf(kh.getHocPhi()),
                    kh.getMoTa()
                );
                writer.write(line);
                writer.newLine();
            }
        }
    }

    // Đọc danh sách khóa học từ file
    public static ArrayList<KhoaHoc> readKhoaHocFromFile(String fileName) throws IOException {
        ArrayList<KhoaHoc> danhSach = new ArrayList<>();
        File file = new File(PATH + fileName);
        if (!file.exists()) return danhSach;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 5) {
                    KhoaHoc kh = new KhoaHoc(
                        parts[0], // maKhoa
                        parts[1], // tenKhoa
                        Integer.parseInt(parts[2]), // thoiLuong
                        Double.parseDouble(parts[3]), // hocPhi
                        parts[4] // moTa
                    );
                    danhSach.add(kh);
                }
            }
        } catch (Exception e) {
            throw new IOException("Lỗi khi đọc file khóa học: " + e.getMessage());
        }
        return danhSach;
    }

    // Ghi danh sách giảng viên vào file
    public static void writeGiangVienToFile(ArrayList<GiangVien> danhSach, String fileName) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PATH + fileName))) {
            for (GiangVien gv : danhSach) {
                String line = String.join("|",
                    gv.getMaGiangVien(),
                    gv.getHoTen(),
                    dateFormat.format(gv.getNgaySinh()),
                    String.valueOf(gv.isGioiTinh()),
                    gv.getSdt(),
                    gv.getEmail(),
                    gv.getDiaChi(),
                    gv.getTrinhDo(),
                    gv.getBangCap(),
                    String.valueOf(gv.getKinhNghiem())
                );
                writer.write(line);
                writer.newLine();
            }
        }
    }

    // Đọc danh sách giảng viên từ file
    public static ArrayList<GiangVien> readGiangVienFromFile(String fileName) throws IOException {
        ArrayList<GiangVien> danhSach = new ArrayList<>();
        File file = new File(PATH + fileName);
        if (!file.exists()) return danhSach;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 10) {
                    GiangVien gv = new GiangVien(
                        parts[0], // maGiangVien
                        parts[1], // hoTen
                        dateFormat.parse(parts[2]), // ngaySinh
                        Boolean.parseBoolean(parts[3]), // gioiTinh
                        parts[4], // sdt
                        parts[5], // email
                        parts[6], // diaChi
                        parts[0], // maGiangVien (trùng với ma)
                        parts[7], // trinhDo
                        parts[8], // bangCap
                        Integer.parseInt(parts[9]) // kinhNghiem
                    );
                    danhSach.add(gv);
                }
            }
        } catch (Exception e) {
            throw new IOException("Lỗi khi đọc file giảng viên: " + e.getMessage());
        }
        return danhSach;
    }
}