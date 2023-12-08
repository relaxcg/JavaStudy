package org.relaxcg.sc.order.ext.feign;

import org.relaxcg.sc.common.web.Result;
import org.relaxcg.sc.order.ext.feign.dto.StockDecreReq;
import org.relaxcg.sc.order.ext.feign.dto.StockDecreResultRes;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author relaxcg
 * @date 2023/12/8 17:30
 */
@FeignClient(value = "sc-stock", url = "stock")
public interface StockApi {
    @PostMapping("/stock/decreStock")
    Result<StockDecreResultRes> decreStock(@RequestBody StockDecreReq req);
}
