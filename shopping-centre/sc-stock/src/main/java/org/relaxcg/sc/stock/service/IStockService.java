package org.relaxcg.sc.stock.service;

import org.relaxcg.sc.stock.dto.StockDecreResultRes;
import org.relaxcg.sc.stock.entity.Stock;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 库存表 服务类
 * </p>
 *
 * @author relaxcg
 * @since 2023-12-07
 */
public interface IStockService extends IService<Stock> {

    Long addStock(Stock stock);

    StockDecreResultRes decreStock(Long goodsId, int quantity);
}
