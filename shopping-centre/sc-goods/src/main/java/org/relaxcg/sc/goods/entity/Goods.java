package org.relaxcg.sc.goods.entity;

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
 * @since 2023-12-07
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("sc_goods")
@ToString
public class Goods extends BaseEntity {


    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 商品id
     */
    private Long id;

    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 描述
     */
    private String description;

}
