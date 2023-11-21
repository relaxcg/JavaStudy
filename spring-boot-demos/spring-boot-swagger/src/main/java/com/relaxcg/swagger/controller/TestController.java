package com.relaxcg.swagger.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.relaxcg.common.web.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author relaxcg
 * @date 2023/11/20 14:24
 */
@Tag(name = "test")
@ApiSupport(author = "relaxcg@xx.com")
@RestController
@RequestMapping("/test")
public class TestController {

    @ApiOperationSupport(author = "relaxcg@xx.com")
    @Operation(summary = "获取字符串")
    @GetMapping(value = "/getStr", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<String> getStr() {
        return Result.ok("hello");
    }
}
