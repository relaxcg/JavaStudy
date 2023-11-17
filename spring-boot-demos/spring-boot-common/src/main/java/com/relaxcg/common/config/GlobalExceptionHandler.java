package com.relaxcg.common.config;

import com.relaxcg.common.web.Result;
import lombok.extern.slf4j.Slf4j;
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
        String errors = e.getBindingResult().getFieldErrors().stream().map(fe -> {
                    log.info("{}, {}", fe.getCode(), fe.getField());
                    return fe;
                }).map(fe -> fe.getDefaultMessage())
                .collect(Collectors.joining(";"));
        return Result.error(errors);
    }
}
