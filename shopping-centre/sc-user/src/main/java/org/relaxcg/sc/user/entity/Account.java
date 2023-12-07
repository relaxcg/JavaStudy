package org.relaxcg.sc.user.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.relaxcg.sc.common.entity.BaseEntity;

import javax.validation.constraints.NotBlank;
import java.io.Serial;

/**
 * <p>
 * 账户表
 * </p>
 *
 * @author relaxcg
 * @since 2023-11-30
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("sc_account")
@ToString
public class Account extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    private Long id;

    /**
     * 密码
     */
    @NotBlank
    private String password;

    /**
     * 手机号
     */
    @NotBlank
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 状态
     */
    private Byte status;
}
