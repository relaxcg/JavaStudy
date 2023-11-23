package com.relaxcg.multidatasource.ards.controller;

import com.relaxcg.common.web.Result;
import com.relaxcg.multidatasource.ards.entity.Test1DO;
import com.relaxcg.multidatasource.ards.service.ITest1Service;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author relaxcg
 * @date 2023/11/21 17:37
 */
@RestController
@RequestMapping("/test1")
public class Test1Controller {
    @Resource
    private ITest1Service test1Service;

    @GetMapping("/ds1")
    public Result<Test1DO> get1() {
        return Result.ok();
    }

    @GetMapping("/ds2")
    public Result<Integer> get2() {
        return Result.ok();
    }

    @GetMapping()
    public Result<Void> test() {
        var ds1 = test1Service.getFromDs1();
        var ds2 = test1Service.getFromDs2();
        System.out.println(ds1);
        System.out.println(ds2);
        return Result.ok();
    }
}
