package org.relaxcg.sc.order.service;

import org.relaxcg.sc.order.dto.OrderDetailDto;
import org.relaxcg.sc.order.entity.OrderDetail;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 订单明细表 服务类
 * </p>
 *
 * @author relaxcg
 * @since 2023-12-08
 */
public interface IOrderDetailService extends IService<OrderDetail> {

    void saveDetails(Long orderId, List<OrderDetailDto> detailList);
}
