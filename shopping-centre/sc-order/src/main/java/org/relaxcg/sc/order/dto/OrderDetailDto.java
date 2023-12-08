package org.relaxcg.sc.order.dto;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author relaxcg
 * @date 2023/12/8 10:37
 */
@Data
public class OrderDetailDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long goodsId;
    private String goodsName;
    private Integer goodsNum;
    private BigDecimal goodsPrice;
}
