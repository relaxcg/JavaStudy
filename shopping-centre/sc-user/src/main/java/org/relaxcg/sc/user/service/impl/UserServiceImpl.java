package org.relaxcg.sc.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.relaxcg.sc.user.entity.User;
import org.relaxcg.sc.user.mapper.UserMapper;
import org.relaxcg.sc.user.service.IUserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author relaxcg
 * @since 2023-11-30
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Override
    public void addUser(Long userId) {
        User user = new User();
        user.setId(userId);
        save(user);
    }
}
