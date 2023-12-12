package org.relaxcg.sc.stock.controller;

import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import org.relaxcg.sc.common.web.Result;
import org.relaxcg.sc.stock.dto.StockDecreReq;
import org.relaxcg.sc.stock.dto.StockDecreResultRes;
import org.relaxcg.sc.stock.entity.Stock;
import org.relaxcg.sc.stock.service.IStockService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;
import java.util.Random;

/**
 * <p>
 * 库存表 前端控制器
 * </p>
 *
 * @author relaxcg
 * @since 2023-12-07
 */
@Slf4j
@RestController
@RequestMapping("/stock")
public class StockController {
    @Resource
    private IStockService stockService;

    @PostMapping("")
    public Result<Long> addStock(@RequestBody @Validated Stock stock) {
        return Result.ok(stockService.addStock(stock));
    }

    @PostMapping("/decreStock")
    public Result<StockDecreResultRes> decreStock(@RequestBody @Validated StockDecreReq req) {
        return Result.ok(stockService.decreStock(req.getGoodsId(), req.getQuantity()));
    }

    @PostMapping("/decreStocks")
    public Result<List<StockDecreResultRes>> decreStocks(@RequestBody @Valid List<StockDecreReq> reqList) {
        List<StockDecreResultRes> resList = stockService.decreStocks(reqList);
        log.info("decre stocks:{}", JSON.toJSONString(resList));
        return Result.ok(resList);
    }

    @GetMapping("/get")
    public String get() {
        return String.valueOf(new Random().nextInt());
    }


}
