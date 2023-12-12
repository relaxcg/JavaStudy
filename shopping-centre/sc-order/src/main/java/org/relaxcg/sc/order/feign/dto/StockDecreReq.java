package org.relaxcg.sc.order.feign.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;

/**
 * @author relaxcg
 * @date 2023/12/8 17:45
 */
@Data
public class StockDecreReq implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @NotNull
    private Long goodsId;
    @NotNull
    @Min(1)
    private Integer quantity;
}
