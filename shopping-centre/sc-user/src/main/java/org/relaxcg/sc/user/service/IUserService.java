package org.relaxcg.sc.user.service;

import org.relaxcg.sc.user.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author relaxcg
 * @since 2023-11-30
 */
public interface IUserService extends IService<User> {

    void addUser(Long userId);

}
