package util;

public class CustomException extends Exception {
    private int errorCode;
    private String message;

    public CustomException(int errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
        this.message = message;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "CustomException [errorCode=" + errorCode + ", message=" + message + "]";
    }

    // Một số mã lỗi mẫu
    public static final int DUPLICATE_ID = 1;
    public static final int INVALID_DATA = 2;
    public static final int FILE_ERROR = 3;
}