package org.relaxcg.sc.order.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.relaxcg.sc.order.dto.OrderPageQueryParam;
import org.relaxcg.sc.order.dto.OrderPageRes;
import org.relaxcg.sc.order.entity.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 订单表 Mapper 接口
 * </p>
 *
 * @author relaxcg
 * @since 2023-12-08
 */
public interface OrderMapper extends BaseMapper<Order> {

    Page<OrderPageRes> queryPage(Page<OrderPageRes> page, @Param("param") OrderPageQueryParam param);
}
