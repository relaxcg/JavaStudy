package org.relaxcg.sc.order.dto;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * @author relaxcg
 * @date 2023/12/8 10:57
 */
@Data
public class OrderReq implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @NotEmpty
    @Valid
    private List<OrderDetailDto> detailList;

    private String comment;
}
