package org.relaxcg.sc.common.exception;

import com.google.common.base.Throwables;
import lombok.extern.slf4j.Slf4j;
import org.relaxcg.sc.common.utils.MessageLocalTransfer;
import org.relaxcg.sc.common.web.ErrorCode;
import org.relaxcg.sc.common.web.Result;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author relaxcg
 * @date 2023/11/16 9:07
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Result<Void> exception(Exception e) {
        log.error("{}", Throwables.getStackTraceAsString(e));
        return Result.error(e.getMessage());
    }

    @ExceptionHandler(IllegalAccessException.class)
    public Result<Void> handleIllegalAccessException(IllegalAccessException e) {
        log.error("{}", Throwables.getStackTraceAsString(e));
        return Result.error(ErrorCode.PARAM_ERR.getCode(), e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<Void> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("{}", Throwables.getStackTraceAsString(e));
        return Result.error(ErrorCode.PARAM_ERR.getCode(), e.getMessage());
    }

    @ExceptionHandler(AppException.class)
    public Result<Void> handleAppException(AppException e) {
        String message = MessageLocalTransfer.transfer(e.getMessage());
        log.error("error code:{}, error message:{}", e.getCode(), e.getMessage());
        return Result.error(e.getCode(), message);
    }
}
