package org.relaxcg.sc.common.exception;

import lombok.Getter;
import org.relaxcg.sc.common.web.ErrorCode;

import java.io.Serial;

/**
 * @author relaxcg
 * @date  2023/11/20 9:39
 */
public class AppException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    @Getter
    private final String code;

    private AppException(String message, String code) {
        super(message);
        this.code = code;
    }

    public static AppException exception(ErrorCode errorCode) {
        return new AppException(errorCode.getMessage(), errorCode.getCode());
    }

    public static AppException exception(String message) {
        return new AppException(message, ErrorCode.SYS_ERR.getCode());
    }

    public static void throwEx(ErrorCode errorCode) {
        throw AppException.exception(errorCode);
    }

    public static void throwEx(String message) {
        throw AppException.exception(message);
    }

    public static void throwEx(String message, ErrorCode errorCode) {
        throw new AppException(message, errorCode.getCode());
    }
}
