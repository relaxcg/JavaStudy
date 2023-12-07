package org.relaxcg.sc.user.service;

import cn.dev33.satoken.stp.SaTokenInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import org.relaxcg.sc.user.entity.Account;

/**
 * <p>
 * 账户表 服务类
 * </p>
 *
 * @author relaxcg
 * @since 2023-11-30
 */
public interface IAccountService extends IService<Account> {

    SaTokenInfo login(String phone, String password);

    Long addAccount(Account account);
}
