package controller;

import view.MainView;
import view.HocVienView;
import view.KhoaHocView;
import view.GiangVienView;
import view.LoginView;
import util.CustomException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class QuanLyTrungTamController {
    private MainView mainView;
    private QuanLyHocVien quanLyHocVien;
    private QuanLyKhoaHoc quanLyKhoaHoc;
    private QuanLyGiangVien quanLyGiangVien;

    public QuanLyTrungTamController() {
        mainView = new MainView();
        mainView.setVisible(true);

        // Khởi tạo tất cả các controller ngay từ đầu
        try {
            GiangVienView gvView = new GiangVienView();
            KhoaHocView khView = new KhoaHocView();
            HocVienView hvView = new HocVienView();

            // Khởi tạo QuanLyGiangVien và QuanLyKhoaHoc trước
            quanLyGiangVien = new QuanLyGiangVien(gvView, null); // Tạm thời truyền null
            quanLyKhoaHoc = new QuanLyKhoaHoc(khView, quanLyGiangVien);
            quanLyGiangVien = new QuanLyGiangVien(gvView, quanLyKhoaHoc); // Khởi tạo lại với quanLyKhoaHoc đầy đủ
            quanLyHocVien = new QuanLyHocVien(hvView, quanLyKhoaHoc);
        } catch (CustomException ex) {
            JOptionPane.showMessageDialog(mainView, ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }

        // Listener cho Quản Lý Học Viên
        mainView.addQuanLyHocVienListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (quanLyHocVien != null) {
                    HocVienView hvView = new HocVienView();
                    try {
                        quanLyHocVien = new QuanLyHocVien(hvView, quanLyKhoaHoc);
                        hvView.setVisible(true);
                    } catch (CustomException ex) {
                        JOptionPane.showMessageDialog(mainView, ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        // Listener cho Quản Lý Khóa Học
        mainView.addQuanLyKhoaHocListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (quanLyKhoaHoc != null) {
                    KhoaHocView khView = new KhoaHocView();
                    try {
                        quanLyKhoaHoc = new QuanLyKhoaHoc(khView, quanLyGiangVien);
                        khView.setVisible(true);
                    } catch (CustomException ex) {
                        JOptionPane.showMessageDialog(mainView, ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        // Listener cho Quản Lý Giảng Viên
        mainView.addQuanLyGiangVienListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (quanLyGiangVien != null) {
                    GiangVienView gvView = new GiangVienView();
                    try {
                        quanLyGiangVien = new QuanLyGiangVien(gvView, quanLyKhoaHoc);
                        gvView.setVisible(true);
                    } catch (CustomException ex) {
                        JOptionPane.showMessageDialog(mainView, ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        // Listener cho Thống Kê
        mainView.addThongKeListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                thongKe();
            }
        });
    }

    private void thongKe() {
        StringBuilder thongKe = new StringBuilder();
        thongKe.append("Tổng số học viên: ").append(quanLyHocVien != null ? quanLyHocVien.getDanhSachHocVien().size() : 0).append("\n");
        thongKe.append("Tổng số khóa học: ").append(quanLyKhoaHoc != null ? quanLyKhoaHoc.getDanhSachKhoaHoc().size() : 0).append("\n");
        thongKe.append("Tổng số giảng viên: ").append(quanLyGiangVien != null ? quanLyGiangVien.getDanhSachGiangVien().size() : 0).append("\n");
        JOptionPane.showMessageDialog(mainView, thongKe.toString(), "Thống kê", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        LoginView loginView = new LoginView();
        new LoginController(loginView);
        loginView.setVisible(true);
    }
}