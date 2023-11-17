package com.relaxcg.common.web;

import lombok.Getter;

/**
 * @author relaxcg
 * @date 2023/11/17 14:58
 */
@Getter
public enum ErrorCode {
    SUCCESS("200", "Success"),
    SYS_ERR("500", "System error"),
    PARAM_ERR("400", "Request parameter error"),
    ;

    private final String code;
    private final String message;

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
