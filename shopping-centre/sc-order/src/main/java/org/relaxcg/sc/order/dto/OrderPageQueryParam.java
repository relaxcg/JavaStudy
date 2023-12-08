package org.relaxcg.sc.order.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.relaxcg.sc.common.web.PageQueryParam;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author relaxcg
 * @date 2023/12/8 11:22
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class OrderPageQueryParam extends PageQueryParam implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Byte orderStatus;
    private String goodsName;
    private LocalDateTime createTimeStart;
    private LocalDateTime createTimeEnd;

}
