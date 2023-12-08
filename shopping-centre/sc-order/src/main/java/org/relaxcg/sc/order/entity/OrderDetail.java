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
 * 订单明细表
 * </p>
 *
 * @author relaxcg
 * @since 2023-12-08
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("sc_order_detail")
@ToString
public class OrderDetail extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 商品明细id
     */
    private Long id;

    /**
     * 订单id
     */
    private Long orderId;

    /**
     * 商品id
     */
    private Long goodsId;

    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 商品数量
     */
    private Integer goodsNum;

    /**
     * 商品价格
     */
    private BigDecimal goodsPrice;

}
