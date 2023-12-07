package org.relaxcg.sc.stock.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.relaxcg.sc.common.entity.BaseEntity;

import java.io.Serial;

/**
 * <p>
 * 库存表
 * </p>
 *
 * @author relaxcg
 * @since 2023-12-07
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("sc_stock")
@ToString
public class Stock extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 库存id
     */
    private Long id;

    /**
     * 商品id
     */
    private Long goodsId;

    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 库存量
     */
    private Integer inventory;

    /**
     * 状态
     */
    private Byte status;

}
