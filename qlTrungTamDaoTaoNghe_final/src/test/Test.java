package test;

import view.MainView;
import view.HocVienView;
import view.KhoaHocView;
import view.GiangVienView;
import view.LoginView;
import controller.QuanLyTrungTamController;
import controller.QuanLyHocVien;
import controller.QuanLyKhoaHoc;
import controller.QuanLyGiangVien;
import controller.LoginController;
import util.CustomException;
import javax.swing.SwingUtilities;

public class Test {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                LoginView loginView = new LoginView();
                new LoginController(loginView) {
                    @Override
                    public void onSuccessfulLogin() {
                        loginView.dispose();
                        try {
                            QuanLyTrungTamController controller = new QuanLyTrungTamController();
                        } catch (Exception e) {
                            System.err.println("Lỗi khởi tạo sau khi đăng nhập: " + e.getMessage());
                        }
                    }
                };
                loginView.setVisible(true);

            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}