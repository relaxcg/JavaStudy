package org.relaxcg.sc.stock.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.relaxcg.sc.common.utils.BeanUtils;
import org.relaxcg.sc.stock.dto.StockDecreReq;
import org.relaxcg.sc.stock.dto.StockDecreResultRes;
import org.relaxcg.sc.stock.entity.Stock;
import org.relaxcg.sc.stock.mapper.StockMapper;
import org.relaxcg.sc.stock.service.IStockService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
                t.setGoodsId(goodsId);
                t.setSuccess(false);
                t.setMessage("库存不存在");
            });
        }

        if (stock.getInventory() >= quantity) {
            return BeanUtils.set(StockDecreResultRes::new, t -> {
                t.setGoodsId(goodsId);
                t.setSuccess(false);
                t.setMessage("库存不足");
            });
        }

        update(lambdaUpdate().set(Stock::getInventory, stock.getInventory() - quantity)
                .eq(Stock::getId, stock.getId()));
        return BeanUtils.set(StockDecreResultRes::new, t -> {
            t.setGoodsId(goodsId);
            t.setSuccess(true);
        });
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<StockDecreResultRes> decreStocks(List<StockDecreReq> reqList) {
        Map<Long, Integer> goodsQuantityMap = reqList.stream().collect(Collectors.toMap(StockDecreReq::getGoodsId, StockDecreReq::getQuantity));
        List<Stock> stocks = list(Wrappers.lambdaQuery(Stock.class).in(Stock::getGoodsId, goodsQuantityMap.keySet()));
        if (stocks.isEmpty()) {
            return goodsQuantityMap.keySet().stream().map(goodId -> BeanUtils.set(StockDecreResultRes::new, t -> {
                t.setGoodsId(goodId);
                t.setSuccess(false);
                t.setMessage("库存不足");
            })).collect(Collectors.toList());
        }

        List<StockDecreResultRes> resList = stocks.stream().filter(stock -> stock.getInventory() < goodsQuantityMap.get(stock.getGoodsId()))
                .map(stock -> BeanUtils.set(StockDecreResultRes::new, s -> {
                    s.setGoodsId(stock.getGoodsId());
                    s.setSuccess(false);
                    s.setMessage("库存不足");
                })).toList();
        if (!resList.isEmpty()) {
            return resList;
        }

        List<Stock> updatedList = stocks.stream().map(stock -> BeanUtils.set(Stock::new, s -> {
            s.setId(stock.getId());
            s.setInventory(stock.getInventory() - goodsQuantityMap.get(s.getGoodsId()));
        })).toList();
        updateBatchById(updatedList);
        return BeanUtils.copyList(reqList, StockDecreResultRes::new, t -> t.setSuccess(true));
    }
}
