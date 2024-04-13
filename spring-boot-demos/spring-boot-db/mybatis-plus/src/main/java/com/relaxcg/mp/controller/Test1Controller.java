package com.relaxcg.mp.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.relaxcg.common.web.Result;
import com.relaxcg.mp.entity.DishesEntity;
import com.relaxcg.mp.entity.Test1DO;
import com.relaxcg.mp.mapper.DishesMapper;
import com.relaxcg.mp.mapper.Test1Mapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * @author relaxcg
 * @date 2023/11/21 17:37
 */
@RestController
@RequestMapping("/test1")
public class Test1Controller {
    @Resource
    private Test1Mapper test1Mapper;

    @GetMapping("")
    public Result<Test1DO> get1() {
        Test1DO test1DO = test1Mapper.selectOne(Wrappers.query(Test1DO.class).last("limit 1"));
        return Result.ok(test1DO);
    }

    @Resource
    private DishesMapper dishesMapper;

    @GetMapping("/2")
    public Result<Void> test2() {
        DishesEntity dishesEntity = new DishesEntity();
        dishesEntity.setId(1L);
        dishesEntity.setInfo(new HashMap<String, Object>() {{
            put("name", "test");
        }});
        dishesMapper.insert(dishesEntity);

        DishesEntity dishesEntity1 = dishesMapper.selectById(1L);
        System.out.println(dishesEntity1.getInfo());
        dishesMapper.deleteById(1L);
        return Result.ok();
    }

}
