package controller;

import view.MainView;
import view.HocVienView;
import view.KhoaHocView;
import view.GiangVienView;
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

        mainView.addQuanLyHocVienListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    HocVienView hvView = new HocVienView();
                    quanLyHocVien = new QuanLyHocVien(hvView);
                    hvView.setVisible(true);
                } catch (CustomException ex) {
                    JOptionPane.showMessageDialog(mainView, ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        mainView.addQuanLyKhoaHocListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    KhoaHocView khView = new KhoaHocView();
                    quanLyKhoaHoc = new QuanLyKhoaHoc(khView);
                    khView.setVisible(true);
                } catch (CustomException ex) {
                    JOptionPane.showMessageDialog(mainView, ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        mainView.addQuanLyGiangVienListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    GiangVienView gvView = new GiangVienView();
                    quanLyGiangVien = new QuanLyGiangVien(gvView);
                    gvView.setVisible(true);
                } catch (CustomException ex) {
                    JOptionPane.showMessageDialog(mainView, ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

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
        new QuanLyTrungTamController();
    }
}
