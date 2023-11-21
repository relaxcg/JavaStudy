package com.relaxcg.i18n.controller;

import com.relaxcg.common.exception.AppException;
import com.relaxcg.common.web.Result;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.io.Serial;
import java.io.Serializable;

/**
 * @author relaxcg
 * @date 2023/11/17 14:29
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<Void> create(@RequestBody @Validated UserReq req) {
        if ("123".equals(req.getUserId())) {
            AppException.throwEx("user.id.not.eq.123");
        }
        return Result.ok();
    }

    @Data
    public static class UserReq implements Serializable {

        @Serial
        private static final long serialVersionUID = 1L;
        @NotEmpty(message = "{user.msg.userId.notEmpty}")
        private String userId;

        @NotBlank(message = "{user.msg.username.notBlank}")
        private String username;

    }
}
