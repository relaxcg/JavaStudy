package org.relaxcg.sc.user.service.impl;

import cn.dev33.satoken.secure.BCrypt;
import cn.dev33.satoken.stp.SaLoginConfig;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.val;
import org.relaxcg.sc.common.utils.Assert;
import org.relaxcg.sc.user.entity.Account;
import org.relaxcg.sc.user.mapper.AccountMapper;
import org.relaxcg.sc.user.service.IAccountService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 账户表 服务实现类
 * </p>
 *
 * @author relaxcg
 * @since 2023-11-30
 */
@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements IAccountService {
    @Override
    public SaTokenInfo login(Account account) {
        account = getByPhone(account.getPhone());
        Assert.notNull(account, "账号密码错误");
        StpUtil.login(account.getPhone(), SaLoginConfig
                .setExtra("userId", account.getId()));
        return StpUtil.getTokenInfo();
    }

    private Account getByPhone(String phone) {
        return getOne(Wrappers.lambdaQuery(Account.class)
                .eq(Account::getPhone, phone).last("limit 1"));
    }

    @Override
    public Long addUser(Account account) {
        val phone = account.getPhone();
        val old = getOne(Wrappers.lambdaQuery(Account.class)
                .eq(Account::getPhone, phone).last("limit 1"));
        Assert.isNull(old, "账户已存在，请使用不同的手机号注册");
        account.setPassword(BCrypt.hashpw(account.getPassword()));
        save(account);
        return account.getId();
    }
}

