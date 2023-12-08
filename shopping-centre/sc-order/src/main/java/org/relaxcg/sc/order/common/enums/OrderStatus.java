package org.relaxcg.sc.order.common.enums;

import lombok.Getter;

/**
 * @author relaxcg
 * @date 2023/12/8 11:07
 */
public enum OrderStatus {
    // 待支付
    TO_BE_PAID(1),
    // 已支付
    PAID(3),
    // 待发货
    TO_BE_DELIVERED(4),
    // 已发货
    SHIPPED(5),
    // 运输中
    IN_TRANSIT(6),
    // 已完成
    FINISHED(7),
    // 退款中
    REFUNDING(8),
    // 已关闭
    CLOSED(9);
    @Getter
    private final Byte Status;

    OrderStatus(Integer status) {
        Status = status.byteValue();
    }
}
