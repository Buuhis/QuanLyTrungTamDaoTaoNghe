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
                    hv.getKetQuaDanhGia(),
                    hv.getMaKhoaHoc() != null ? hv.getMaKhoaHoc() : ""
                );
                writer.write(line);
                writer.newLine();
            }
        }
    }

    public static ArrayList<HocVien> readHocVienFromFile(String fileName) throws IOException {
        ArrayList<HocVien> danhSach = new ArrayList<>();
        File file = new File(PATH + fileName);
        if (!file.exists()) return danhSach;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            int lineNumber = 0;
            while ((line = reader.readLine()) != null) {
                lineNumber++;
                try {
                    String[] parts = line.split("\\|");
                    if (parts.length >= 9) {
                        String maKhoaHoc = parts.length > 8 ? parts[8] : "";
                        int soBuoiVang = parts.length > 9 ? Integer.parseInt(parts[9]) : 0;
                        // Kiểm tra định dạng ngày trước khi parse
                        String ngaySinhStr = parts[2];
                        if (!ngaySinhStr.matches("\\d{2}-\\d{2}-\\d{4}")) {
                            System.err.println("Dòng " + lineNumber + ": Định dạng ngày sinh không hợp lệ - " + ngaySinhStr);
                            continue; // Bỏ qua dòng này
                        }
                        HocVien hv = new HocVien(
                            parts[0],
                            parts[1],
                            dateFormat.parse(ngaySinhStr),
                            Boolean.parseBoolean(parts[3]),
                            parts[4],
                            parts[5],
                            parts[6],
                            parts[7],
                            maKhoaHoc,
                            soBuoiVang
                        );
                        danhSach.add(hv);
                    } else {
                        System.err.println("Dòng " + lineNumber + ": Dữ liệu không đủ trường - " + line);
                    }
                } catch (Exception e) {
                    System.err.println("Dòng " + lineNumber + ": Lỗi khi đọc dữ liệu - " + e.getMessage());
                    continue; // Bỏ qua dòng lỗi
                }
            }
        } catch (IOException e) {
            throw new IOException("Lỗi khi đọc file học viên: " + e.getMessage());
        }
        return danhSach;
    }

    public static void writeKhoaHocToFile(ArrayList<KhoaHoc> danhSach, String fileName) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PATH + fileName))) {
            for (KhoaHoc kh : danhSach) {
                String line = String.join("|",
                    kh.getMaKhoa(),
                    kh.getTenKhoa(),
                    String.valueOf(kh.getThoiLuong()),
                    String.valueOf(kh.getHocPhi()),
                    kh.getMoTa(),
                    kh.getMaGiangVien() != null ? kh.getMaGiangVien() : ""
                );
                writer.write(line);
                writer.newLine();
            }
        }
    }

    public static ArrayList<KhoaHoc> readKhoaHocFromFile(String fileName) throws IOException {
        ArrayList<KhoaHoc> danhSach = new ArrayList<>();
        File file = new File(PATH + fileName);
        if (!file.exists()) return danhSach;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length >= 5) {
                    String maGiangVien = parts.length > 5 ? parts[5] : "";
                    KhoaHoc kh = new KhoaHoc(
                        parts[0],
                        parts[1],
                        Integer.parseInt(parts[2]),
                        Double.parseDouble(parts[3]),
                        parts[4],
                        maGiangVien
                    );
                    danhSach.add(kh);
                }
            }
        } catch (Exception e) {
            throw new IOException("Lỗi khi đọc file khóa học: " + e.getMessage());
        }
        return danhSach;
    }

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
                        parts[0],
                        parts[1],
                        dateFormat.parse(parts[2]),
                        Boolean.parseBoolean(parts[3]),
                        parts[4],
                        parts[5],
                        parts[6],
                        parts[7],
                        parts[8],
                        Integer.parseInt(parts[9])
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