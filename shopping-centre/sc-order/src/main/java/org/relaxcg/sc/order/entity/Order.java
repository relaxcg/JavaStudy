package org.relaxcg.sc.order.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.relaxcg.sc.common.entity.BaseEntity;

import java.io.Serial;
import java.math.BigDecimal;

/**
 * <p>
 * 订单表
 * </p>
 *
 * @author relaxcg
 * @since 2023-12-08
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("sc_order")
@ToString
public class Order extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 订单id
     */
    private Long id;

    /**
     * 订单金额
     */
    private BigDecimal orderAmount;

    /**
     * 订单状态
     */
    private Byte orderStatus;

    /**
     * 描述
     */
    private String comment;

}
