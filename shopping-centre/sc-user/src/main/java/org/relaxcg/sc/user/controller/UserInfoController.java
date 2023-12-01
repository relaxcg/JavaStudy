package org.relaxcg.sc.user.controller;

import org.relaxcg.sc.common.utils.Assert;
import org.relaxcg.sc.common.web.Result;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author relaxcg
 * @since 2023-11-30
 */
@RestController
@RequestMapping("/userInfo")
public class UserInfoController {


    // @PostMapping("/add")
    // public Result<Long> addUser()

    @GetMapping("/get")
    public Result<String> get(@RequestParam(value = "a", required = false) String a) {
        Assert.notNull(a, "a is null");
        return Result.ok("a");
    }
}
