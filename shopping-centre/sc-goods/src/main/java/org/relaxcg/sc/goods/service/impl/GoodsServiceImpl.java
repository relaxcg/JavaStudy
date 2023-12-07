package org.relaxcg.sc.goods.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.relaxcg.sc.goods.entity.Goods;
import org.relaxcg.sc.goods.mapper.GoodsMapper;
import org.relaxcg.sc.goods.service.IGoodsService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品表 服务实现类
 * </p>
 *
 * @author relaxcg
 * @since 2023-12-07
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements IGoodsService {

    @Override
    public Long addGoods(Goods goods) {
        save(goods);
        return goods.getId();
    }
}
