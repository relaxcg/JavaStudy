package org.relaxcg.sc.stock.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;

/**
 * @author relaxcg
 * @date 2023/12/8 10:22
 */
@Data
@NoArgsConstructor
public class StockDecreReq implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @NotNull
    private Long goodsId;
    @NotNull
    @Min(1)
    private Integer quantity;
}
