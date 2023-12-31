package org.relaxcg.sc.user.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.relaxcg.sc.common.entity.BaseEntity;

import java.io.Serial;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author relaxcg
 * @since 2023-11-30
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("sc_user")
@ToString
public class User extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 地址
     */
    private String address;
}
