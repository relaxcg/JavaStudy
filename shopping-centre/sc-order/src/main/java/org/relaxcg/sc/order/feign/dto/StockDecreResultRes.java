package org.relaxcg.sc.order.feign.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author relaxcg
 * @date 2023/12/8 17:47
 */
@Data
@NoArgsConstructor
public class StockDecreResultRes implements Serializable {


    @Serial
    private static final long serialVersionUID = 1L;

    private Boolean success;
    private String message;
}
