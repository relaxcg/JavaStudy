package org.relaxcg.sc.user.controller;

// import cn.dev33.satoken.stp.SaTokenInfo;

import cn.dev33.satoken.stp.SaTokenInfo;
import lombok.val;
import org.relaxcg.sc.common.web.Result;
import org.relaxcg.sc.user.entity.Account;
import org.relaxcg.sc.user.service.IAccountService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 账户表 前端控制器
 * </p>
 *
 * @author relaxcg
 * @since 2023-11-30
 */
@RestController
@RequestMapping("/account")
public class AccountController {
    @Resource
    private IAccountService accountService;

    @PostMapping("login")
    public Result<SaTokenInfo> login(@RequestBody Account account) {
        val info = accountService.login(account.getPhone(), account.getPassword());
        return Result.ok(info);
    }


    @PostMapping()
    public Result<Long> addAccount(@Validated @RequestBody Account account) {
        Long id = accountService.addAccount(account);
        return Result.ok(id);
    }

}
