package org.relaxcg.sc.user.service.impl;

import cn.dev33.satoken.secure.BCrypt;
import cn.dev33.satoken.stp.SaLoginConfig;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.relaxcg.sc.common.utils.Assert;
import org.relaxcg.sc.user.entity.Account;
import org.relaxcg.sc.user.mapper.AccountMapper;
import org.relaxcg.sc.user.service.IAccountService;
import org.relaxcg.sc.user.service.IUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * <p>
 * 账户表 服务实现类
 * </p>
 *
 * @author relaxcg
 * @since 2023-11-30
 */
@Service
@RequiredArgsConstructor
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements IAccountService {
    private final IUserService userService;

    @Override
    public SaTokenInfo login(String phone, String password) {
        Account account = getByPhone(phone);
        Assert.notNull(account, "账号密码错误");
        boolean checkResult = BCrypt.checkpw(password, account.getPassword());
        Assert.isTrue(checkResult, "账号密码错误");
        StpUtil.login(account.getPhone(), SaLoginConfig
                .setExtra("userId", account.getId()));
        return StpUtil.getTokenInfo();
    }

    private Account getByPhone(String phone) {
        return getOne(Wrappers.lambdaQuery(Account.class)
                .eq(Account::getPhone, phone).last("limit 1"));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long addAccount(Account account) {
        val phone = account.getPhone();
        val old = getOne(Wrappers.lambdaQuery(Account.class)
                .eq(Account::getPhone, phone).last("limit 1"));
        Assert.isNull(old, "账户已存在，请使用不同的手机号注册");
        account.setPassword(BCrypt.hashpw(account.getPassword()));
        save(account);
        userService.addUser(account.getId());
        return account.getId();
    }
}
