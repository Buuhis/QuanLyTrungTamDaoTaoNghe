package model;

import util.CustomException;

public interface IQuanLy<T> {
    void them(T entity) throws CustomException;
    void sua(T entity) throws CustomException;
    void xoa(String ma) throws CustomException;
    T timKiem(String ma);
}