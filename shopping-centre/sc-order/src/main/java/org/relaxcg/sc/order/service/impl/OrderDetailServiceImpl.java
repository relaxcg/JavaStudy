package org.relaxcg.sc.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.relaxcg.sc.common.utils.BeanUtils;
import org.relaxcg.sc.order.dto.OrderDetailDto;
import org.relaxcg.sc.order.entity.OrderDetail;
import org.relaxcg.sc.order.mapper.OrderDetailMapper;
import org.relaxcg.sc.order.service.IOrderDetailService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 订单明细表 服务实现类
 * </p>
 *
 * @author relaxcg
 * @since 2023-12-08
 */
@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements IOrderDetailService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveDetails(Long orderId, List<OrderDetailDto> detailList) {
        List<OrderDetail> orderDetails = BeanUtils.copyList(detailList, OrderDetail.class, t -> t.setOrderId(orderId));
        saveBatch(orderDetails);
    }
}
