package org.relaxcg.sc.common.config;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.val;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author relaxcg
 * @date 2023/11/30 14:54
 */
@Component
public class SCMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        if (metaObject.hasSetter("createTime")) {
            val now = LocalDateTime.now();
            this.setFieldValByName("createTime", now, metaObject);
        }
        if (metaObject.hasSetter("createUser")) {
            this.setFieldValByName("createUser", getCreateUser(), metaObject);
        }
    }

    private Long getCreateUser() {
        try {
            return Long.valueOf(StpUtil.getExtra("userId").toString());
        } catch (Exception e) {
            return 1L;
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        if (metaObject.hasSetter("modifyTime")) {
            val now = LocalDateTime.now();
            this.setFieldValByName("modifyTime", now, metaObject);
        }
        if (metaObject.hasSetter("modifyUser") && StpUtil.getExtra("userId") != null) {
            this.setFieldValByName("modifyUser", StpUtil.getExtra("userId"), metaObject);
        }
    }
}
