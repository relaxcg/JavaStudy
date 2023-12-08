package org.relaxcg.sc.common.web;

import lombok.Data;
import org.relaxcg.sc.common.exception.AppException;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author relaxcg
 * @date 2023/11/17 14:08
 */
@Data
public class Result<T> implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;


    private String code;
    private String message;
    private T data;

    private Result() {
        this.code = ErrorCode.SUCCESS.getCode();
        this.message = ErrorCode.SUCCESS.getMessage();
        this.data = null;
    }

    private Result(T data) {
        this.code = ErrorCode.SUCCESS.getCode();
        this.message = ErrorCode.SUCCESS.getMessage();
        this.data = data;
    }

    private Result(String message) {
        this.code = ErrorCode.SYS_ERR.getCode();
        this.message = message;
        this.data = null;
    }

    private Result(String code, String message) {
        this.code = code;
        this.message = message;
        this.data = null;
    }

    public static <T> Result<T> ok() {
        return new Result<>();
    }

    public static <T> Result<T> ok(T data) {
        return new Result<>(data);
    }

    public static <T> Result<T> error(String message) {
        return new Result<>(message);
    }

    public static <T> Result<T> error(String code, String message) {
        return new Result<>(code, message);
    }

    public T getResultData() {
        if (ErrorCode.SUCCESS.getCode().equals(this.code)) {
            AppException.throwEx(this.message, this.code);
        }
        return this.data;
    }
}
