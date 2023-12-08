package org.relaxcg.sc.order.dto;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author relaxcg
 * @date 2023/12/8 11:14
 */
@Data
public class OrderRes implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private BigDecimal amount;
    private LocalDateTime createTime;
    private String comment;

    private List<OrderDetailDto> orderDetails;
}
