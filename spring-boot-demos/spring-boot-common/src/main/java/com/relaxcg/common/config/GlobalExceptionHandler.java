package com.relaxcg.common.config;

import com.relaxcg.common.exception.AppException;
import com.relaxcg.common.utils.MessageLocalTransfer;
import com.relaxcg.common.web.ErrorCode;
import com.relaxcg.common.web.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

/**
 * @author relaxcg
 * @date 2023/11/17 14:52
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<Void> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        String errors = e.getBindingResult().getFieldErrors().stream()
                .peek(fe -> log.info("{}, {}", fe.getCode(), fe.getField()))
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining(";"));
        return Result.error(ErrorCode.PARAM_ERR.getCode(), errors);
    }

    @ExceptionHandler(AppException.class)
    public Result<Void> handleAppException(AppException e) {
        String message = MessageLocalTransfer.transfer(e.getMessage());
        log.error("error code:{}, error message:{}", e.getCode(), e.getMessage());
        return Result.error(e.getCode(), message);
    }

}
