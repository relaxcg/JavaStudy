package org.relaxcg.sc.stock.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.relaxcg.sc.common.utils.BeanUtils;
import org.relaxcg.sc.stock.dto.StockDecreResultRes;
import org.relaxcg.sc.stock.entity.Stock;
import org.relaxcg.sc.stock.mapper.StockMapper;
import org.relaxcg.sc.stock.service.IStockService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional(rollbackFor = Exception.class)
    public Long addStock(Stock stock) {
        save(stock);
        return stock.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public StockDecreResultRes decreStock(Long goodsId, int quantity) {
        Stock stock = getOne(lambdaQuery().eq(Stock::getGoodsId, goodsId));
        if (stock == null) {
            return BeanUtils.set(StockDecreResultRes::new, t -> {
                t.setSuccess(false);
                t.setMessage("库存不存在");
            });
        }

        if (stock.getInventory() >= quantity) {
            return BeanUtils.set(StockDecreResultRes::new, t -> {
                t.setSuccess(false);
                t.setMessage("库存不足");
            });
        }

        update(lambdaUpdate().set(Stock::getInventory, stock.getInventory() - quantity)
                .eq(Stock::getId, stock.getId()));
        return BeanUtils.set(StockDecreResultRes::new, t -> {
            t.setSuccess(true);
        });
    }
}
