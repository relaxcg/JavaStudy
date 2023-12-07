package org.relaxcg.sc.stock.controller;

import org.relaxcg.sc.common.web.Result;
import org.relaxcg.sc.stock.entity.Stock;
import org.relaxcg.sc.stock.service.IStockService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 库存表 前端控制器
 * </p>
 *
 * @author relaxcg
 * @since 2023-12-07
 */
@RestController
@RequestMapping("/stock")
public class StockController {
    @Resource
    private IStockService stockService;

    @PostMapping("")
    public Result<Long> addStock(@RequestBody @Validated Stock stock) {
        return Result.ok(stockService.addStock(stock));
    }
}
