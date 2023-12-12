package org.relaxcg.sc.order.service.impl;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.relaxcg.sc.common.utils.BeanUtils;
import org.relaxcg.sc.common.utils.BigDecimalUtil;
import org.relaxcg.sc.common.web.Result;
import org.relaxcg.sc.order.dto.*;
import org.relaxcg.sc.order.entity.Order;
import org.relaxcg.sc.order.entity.OrderDetail;
import org.relaxcg.sc.order.feign.StockApi;
import org.relaxcg.sc.order.feign.dto.StockDecreReq;
import org.relaxcg.sc.order.feign.dto.StockDecreResultRes;
import org.relaxcg.sc.order.mapper.OrderMapper;
import org.relaxcg.sc.order.service.IOrderDetailService;
import org.relaxcg.sc.order.service.IOrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 订单表 服务实现类
 * </p>
 *
 * @author relaxcg
 * @since 2023-12-08
 */
@Slf4j
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {
    @Resource
    private IOrderDetailService orderDetailService;
    @Resource
    private StockApi stockApi;

    private static void strategy(OrderDetailDto s, StockDecreReq t) {
        t.setQuantity(s.getGoodsNum());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long addOrder(OrderReq req) {
        // TODO: 2023/12/8 校验商品库存
        Result<List<StockDecreResultRes>> decreResult = stockApi.decreStocks(BeanUtils.copyList(req.getDetailList(),
                StockDecreReq::new, OrderServiceImpl::strategy));
        List<StockDecreResultRes> resultData = decreResult.dealResult();
        log.info("stock decre result:{}", JSON.toJSONString(resultData));
        Order order = new Order();
        BigDecimal amount = req.getDetailList().stream()
                .map(a -> BigDecimalUtil.mul(a.getGoodsPrice(), a.getGoodsNum()))
                .reduce(BigDecimalUtil::add).orElse(BigDecimal.ZERO);
        order.setOrderAmount(amount);
        order.setComment(req.getComment());
        save(order);

        orderDetailService.saveDetails(order.getId(), req.getDetailList());
        return order.getId();
    }

    @Override
    public OrderRes getOrder(Long orderId) {
        Order order = getById(orderId);
        if (order == null) {
            return null;
        }
        List<OrderDetail> details = orderDetailService.list(Wrappers.lambdaQuery(OrderDetail.class)
                .eq(OrderDetail::getOrderId, orderId));
        return BeanUtils.copy(order, OrderRes::new,
                t -> t.setOrderDetails(BeanUtils.copyList(details, OrderDetailDto::new)));
    }

    @Override
    public Page<OrderPageRes> pageQuery(OrderPageQueryParam param) {
        if (!StringUtils.hasText(param.getGoodsName())) {
            Page<Order> page = page(new Page<>(param.getCurrentPage(), param.getPageSize()),
                    lambdaQuery().eq(Objects.nonNull(param.getOrderStatus()), Order::getOrderStatus, param.getOrderStatus())
                            .le(Objects.nonNull(param.getCreateTimeEnd()), Order::getCreateTime, param.getCreateTimeStart())
                            .ge(Objects.nonNull(param.getCreateTimeStart()), Order::getCreateTime, param.getCreateTimeStart())
                            .getWrapper());
            return BeanUtils.copy(page, Page::new,
                    (s, t) -> t.setRecords(BeanUtils.copyList(s.getRecords(), OrderPageRes::new)));
        }
        Page<OrderPageRes> resPage = baseMapper.queryPage(new Page<>(param.getCurrentPage(), param.getPageSize()), param);
        log.info("page:{}", JSON.toJSONString(resPage));
        return resPage;
    }
}
