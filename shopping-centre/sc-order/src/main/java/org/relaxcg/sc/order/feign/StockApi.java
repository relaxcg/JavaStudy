package org.relaxcg.sc.order.feign;

import org.relaxcg.sc.common.web.Result;
import org.relaxcg.sc.order.feign.dto.StockDecreReq;
import org.relaxcg.sc.order.feign.dto.StockDecreResultRes;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author relaxcg
 * @date 2023/12/8 17:30
 */
@FeignClient(value = "sc-stock", path = "/stock")
public interface StockApi {
    @PostMapping("/stock/decreStock")
    Result<StockDecreResultRes> decreStock(@RequestBody StockDecreReq req);

    @PostMapping("/stock/decreStocks")
    Result<List<StockDecreResultRes>> decreStocks(@RequestBody List<StockDecreReq> reqList);

    @GetMapping("/stock/get")
    String get();
}
