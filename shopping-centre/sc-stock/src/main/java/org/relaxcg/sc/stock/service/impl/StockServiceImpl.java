package org.relaxcg.sc.stock.service.impl;

import org.relaxcg.sc.stock.entity.Stock;
import org.relaxcg.sc.stock.mapper.StockMapper;
import org.relaxcg.sc.stock.service.IStockService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 库存表 服务实现类
 * </p>
 *
 * @author relaxcg
 * @since 2023-12-07
 */
@Service
public class StockServiceImpl extends ServiceImpl<StockMapper, Stock> implements IStockService {

    @Override
    public Long addStock(Stock stock) {
        return null;
    }
}
