package org.relaxcg.sc.goods.service;

import org.relaxcg.sc.goods.entity.Goods;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 商品表 服务类
 * </p>
 *
 * @author relaxcg
 * @since 2023-12-07
 */
public interface IGoodsService extends IService<Goods> {

    Long addGoods(Goods goods);
}
