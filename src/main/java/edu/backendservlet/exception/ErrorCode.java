package edu.backendservlet.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    EX_NOT_FOUND(9999,"Lỗi  "),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND.value(),"User khong ton tai"),
    SUCCESS(HttpStatus.OK.value(), "THanh cong"),
    USER_EXISTED(HttpStatus.CONFLICT.value(),"User Da ton tai"),
    USER_AUTH(1001,"Sai mat khau hoac pass word"),
    CATEGORT_NOT_FOUND(2001,"Không tìm thấy thể lọai"),
    CATEGORY_NOT_NAME(2002,"Không tìm thấy tên thể loại"),
    PRODUCT_NOT_FOUNF(3001,"không tìm thấy sản phẩm");
    private int code;
    private String message;
}
