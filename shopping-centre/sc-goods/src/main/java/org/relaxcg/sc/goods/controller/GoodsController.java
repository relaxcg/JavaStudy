package org.relaxcg.sc.goods.controller;

import org.relaxcg.sc.common.web.Result;
import org.relaxcg.sc.goods.entity.Goods;
import org.relaxcg.sc.goods.service.IGoodsService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 商品表 前端控制器
 * </p>
 *
 * @author relaxcg
 * @since 2023-12-07
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Resource
    private IGoodsService goodsService;

    @PostMapping("")
    public Result<Long> addGoods(@Validated @RequestBody Goods goods) {
        return Result.ok(goodsService.addGoods(goods));
    }

}
