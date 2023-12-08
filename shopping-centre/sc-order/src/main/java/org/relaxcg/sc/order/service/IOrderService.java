package org.relaxcg.sc.order.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.relaxcg.sc.order.dto.OrderPageQueryParam;
import org.relaxcg.sc.order.dto.OrderPageRes;
import org.relaxcg.sc.order.dto.OrderReq;
import org.relaxcg.sc.order.dto.OrderRes;
import org.relaxcg.sc.order.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 订单表 服务类
 * </p>
 *
 * @author relaxcg
 * @since 2023-12-08
 */
public interface IOrderService extends IService<Order> {

    Long addOrder(OrderReq req);

    OrderRes getOrder(Long orderId);

    Page<OrderPageRes> pageQuery(OrderPageQueryParam param);
}
