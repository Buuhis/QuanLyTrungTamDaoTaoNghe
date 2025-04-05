package test;

import model.HocVien;
import model.KhoaHoc;
import model.GiangVien;
import controller.QuanLyHocVien;
import controller.QuanLyKhoaHoc;
import controller.QuanLyGiangVien;
import view.HocVienView;
import view.KhoaHocView;
import view.GiangVienView;
import util.FileManager;
import util.CustomException;
import java.util.Date;
import java.util.ArrayList;
import java.io.IOException;

public class Test {
    public static void main(String[] args) {
        System.out.println("=== BẮT ĐẦU KIỂM TRA DỰ ÁN ===");

        // Test QuanLyHocVien
        testQuanLyHocVien();

        // Test QuanLyKhoaHoc
        testQuanLyKhoaHoc();

        // Test QuanLyGiangVien
        testQuanLyGiangVien();

        System.out.println("=== KẾT THÚC KIỂM TRA ===");
    }

    private static void testQuanLyHocVien() {
        System.out.println("\n1. Kiểm tra QuanLyHocVien:");
        try {
            // Khởi tạo view và controller
            HocVienView hvView = new HocVienView();
            QuanLyHocVien qlhv = new QuanLyHocVien(hvView);

            // Test thêm học viên
            HocVien hv1 = new HocVien("HV001", "Nguyen Van A", new Date(), true, "0123456789", "nva@example.com", "Hanoi", "HV001", new Date(), "Dang hoc");
            qlhv.them(hv1);
            System.out.println(" - Thêm học viên HV001: Thành công");

            // Test thêm học viên trùng mã
            try {
                HocVien hv2 = new HocVien("HV001", "Nguyen Van B", new Date(), false, "0987654321", "nvb@example.com", "HCM", "HV001", new Date(), "Dang hoc");
                qlhv.them(hv2);
            } catch (CustomException e) {
                System.out.println(" - Thêm học viên trùng mã HV001: " + e.getMessage());
            }

            // Test tìm kiếm
            HocVien found = qlhv.timKiem("HV001");
            System.out.println(" - Tìm kiếm HV001: " + (found != null ? "Tìm thấy - " + found.getHoTen() : "Không tìm thấy"));

            // Test sửa học viên
            HocVien hv1Sua = new HocVien("HV001", "Nguyen Van A (Sửa)", new Date(), true, "0123456789", "nva@example.com", "Hanoi", "HV001", new Date(), "Hoan thanh");
            qlhv.sua(hv1Sua);
            System.out.println(" - Sửa học viên HV001: Thành công");

            // Test xóa học viên
            qlhv.xoa("HV001");
            System.out.println(" - Xóa học viên HV001: Thành công");

            // Kiểm tra file
            try {
                ArrayList<HocVien> danhSach = FileManager.readHocVienFromFile("hocvien.txt");
                System.out.println(" - Số học viên trong file: " + danhSach.size());
            } catch (IOException e) {
                System.out.println(" - Lỗi đọc file học viên: " + e.getMessage());
            }

        } catch (CustomException e) {
            System.out.println(" - Lỗi: " + e.getMessage());
        }
    }

    private static void testQuanLyKhoaHoc() {
        System.out.println("\n2. Kiểm tra QuanLyKhoaHoc:");
        try {
            // Khởi tạo view và controller
            KhoaHocView khView = new KhoaHocView();
            QuanLyKhoaHoc qlkh = new QuanLyKhoaHoc(khView);

            // Test thêm khóa học
            KhoaHoc kh1 = new KhoaHoc("KH001", "Java Cơ bản", 60, 5000000, "Khóa học lập trình Java");
            qlkh.them(kh1);
            System.out.println(" - Thêm khóa học KH001: Thành công");

            // Test thêm khóa học trùng mã
            try {
                KhoaHoc kh2 = new KhoaHoc("KH001", "Java Nâng cao", 80, 7000000, "Khóa học Java nâng cao");
                qlkh.them(kh2);
            } catch (CustomException e) {
                System.out.println(" - Thêm khóa học trùng mã KH001: " + e.getMessage());
            }

            // Test tìm kiếm
            KhoaHoc found = qlkh.timKiem("KH001");
            System.out.println(" - Tìm kiếm KH001: " + (found != null ? "Tìm thấy - " + found.getTenKhoa() : "Không tìm thấy"));

            // Test sửa khóa học
            KhoaHoc kh1Sua = new KhoaHoc("KH001", "Java Cơ bản (Sửa)", 70, 5500000, "Khóa học lập trình Java cơ bản");
            qlkh.sua(kh1Sua);
            System.out.println(" - Sửa khóa học KH001: Thành công");

            // Test xóa khóa học
            qlkh.xoa("KH001");
            System.out.println(" - Xóa khóa học KH001: Thành công");

            // Kiểm tra file
            try {
                ArrayList<KhoaHoc> danhSach = FileManager.readKhoaHocFromFile("khoahoc.txt");
                System.out.println(" - Số khóa học trong file: " + danhSach.size());
            } catch (IOException e) {
                System.out.println(" - Lỗi đọc file khóa học: " + e.getMessage());
            }

        } catch (CustomException e) {
            System.out.println(" - Lỗi: " + e.getMessage());
        }
    }

    private static void testQuanLyGiangVien() {
        System.out.println("\n3. Kiểm tra QuanLyGiangVien:");
        try {
            // Khởi tạo view và controller
            GiangVienView gvView = new GiangVienView();
            QuanLyGiangVien qlgv = new QuanLyGiangVien(gvView);

            // Test thêm giảng viên
            GiangVien gv1 = new GiangVien("GV001", "Tran Thi C", new Date(), false, "0912345678", "ttc@example.com", "Da Nang", "GV001", "Thạc sĩ", "CNTT", 5);
            qlgv.them(gv1);
            System.out.println(" - Thêm giảng viên GV001: Thành công");

            // Test thêm giảng viên trùng mã
            try {
                GiangVien gv2 = new GiangVien("GV001", "Le Van D", new Date(), true, "0981234567", "lvd@example.com", "Hue", "GV001", "Tiến sĩ", "CNTT", 10);
                qlgv.them(gv2);
            } catch (CustomException e) {
                System.out.println(" - Thêm giảng viên trùng mã GV001: " + e.getMessage());
            }

            // Test tìm kiếm
            GiangVien found = qlgv.timKiem("GV001");
            System.out.println(" - Tìm kiếm GV001: " + (found != null ? "Tìm thấy - " + found.getHoTen() : "Không tìm thấy"));

            // Test sửa giảng viên
            GiangVien gv1Sua = new GiangVien("GV001", "Tran Thi C (Sửa)", new Date(), false, "0912345678", "ttc@example.com", "Da Nang", "GV001", "Thạc sĩ", "CNTT", 6);
            qlgv.sua(gv1Sua);
            System.out.println(" - Sửa giảng viên GV001: Thành công");

            // Test xóa giảng viên
            qlgv.xoa("GV001");
            System.out.println(" - Xóa giảng viên GV001: Thành công");

            // Kiểm tra file
            try {
                ArrayList<GiangVien> danhSach = FileManager.readGiangVienFromFile("giangvien.txt");
                System.out.println(" - Số giảng viên trong file: " + danhSach.size());
            } catch (IOException e) {
                System.out.println(" - Lỗi đọc file giảng viên: " + e.getMessage());
            }

        } catch (CustomException e) {
            System.out.println(" - Lỗi: " + e.getMessage());
        }
    }
}