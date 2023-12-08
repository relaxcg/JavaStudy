package org.relaxcg.sc.order.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.relaxcg.sc.common.web.Result;
import org.relaxcg.sc.order.dto.OrderPageQueryParam;
import org.relaxcg.sc.order.dto.OrderPageRes;
import org.relaxcg.sc.order.dto.OrderReq;
import org.relaxcg.sc.order.dto.OrderRes;
import org.relaxcg.sc.order.service.IOrderService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 订单表 前端控制器
 * </p>
 *
 * @author relaxcg
 * @since 2023-12-08
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Resource
    private IOrderService orderService;

    @PostMapping
    public Result<Long> addOrder(@RequestBody @Validated OrderReq req) {
        return Result.ok(orderService.addOrder(req));
    }

    @GetMapping("")
    public Result<OrderRes> getOrder(@RequestParam("orderId") Long orderId) {
        return Result.ok(orderService.getOrder(orderId));
    }

    @GetMapping("/page")
    public Result<Page<OrderPageRes>> pageQuery(OrderPageQueryParam param) {
        return Result.ok(orderService.pageQuery(param));
    }

}
