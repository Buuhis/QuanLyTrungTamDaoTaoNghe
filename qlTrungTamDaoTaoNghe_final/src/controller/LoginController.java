package controller;

import view.LoginView;
import util.CustomException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class LoginController {
    protected LoginView loginView;
    private HashMap<String, String> userAccounts; // Lưu trữ tài khoản: ID -> Password

    public LoginController(LoginView loginView) {
        this.loginView = loginView;

        // Khởi tạo danh sách tài khoản
        userAccounts = new HashMap<>();
        userAccounts.put("admin", "1"); // Tài khoản 1
        userAccounts.put("Congvi", "123456"); // Tài khoản 2
        userAccounts.put("Luan", "1"); // Tài khoản 3
        userAccounts.put("Buu", "2"); // Tài khoản 4

        initController();
    }

    private void initController() {
        loginView.addLoginListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    login();
                } catch (CustomException ex) {
                    loginView.showMessage(ex.getMessage());
                }
            }
        });
    }

    private void login() throws CustomException {
        String id = loginView.getId();
        String password = loginView.getPassword();

        // Kiểm tra thông tin đăng nhập
        if (id == null || id.trim().isEmpty() || password == null || password.trim().isEmpty()) {
            throw new CustomException(CustomException.INVALID_DATA, "Vui lòng nhập đầy đủ ID và Password!");
        }

        // Kiểm tra xem ID có tồn tại không
        if (!userAccounts.containsKey(id)) {
            throw new CustomException(CustomException.INVALID_DATA, "ID không tồn tại!");
        }

        // Kiểm tra password
        if (!userAccounts.get(id).equals(password)) {
            throw new CustomException(CustomException.INVALID_DATA, "Password không đúng!");
        }

        // Đăng nhập thành công
        loginView.showMessage("Đăng nhập thành công!");
        onSuccessfulLogin();
    }

    // Phương thức để override trong subclass, được gọi khi đăng nhập thành công
    public void onSuccessfulLogin() {
        // Mặc định không làm gì, sẽ được override trong Test class
    }
}