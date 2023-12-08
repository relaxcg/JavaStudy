package org.relaxcg.sc.payment.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.relaxcg.sc.common.entity.BaseEntity;

import java.io.Serial;
import java.math.BigDecimal;

/**
 * <p>
 * 商品表
 * </p>
 *
 * @author relaxcg
 * @since 2023-12-08
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("sc_payment_record")
@ToString
public class PaymentRecord extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 支付id
     */
    private Long id;

    /**
     * 订单id
     */
    private Long orderId;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 支付金额
     */
    private BigDecimal amount;

    /**
     * 支付方式
     */
    private Byte payType;

    /**
     * 支付状态
     */
    private Byte status;
}
