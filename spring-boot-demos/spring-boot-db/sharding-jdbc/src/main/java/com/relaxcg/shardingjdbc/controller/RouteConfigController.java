package com.relaxcg.shardingjdbc.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.relaxcg.common.web.Result;
import com.relaxcg.shardingjdbc.entity.RouterConfigEntity;
import com.relaxcg.shardingjdbc.service.IRouterConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * @author relaxcg
 * @date 2023/11/24 16:22
 */
@RestController
@RequestMapping(value = "/api/sharding/jdbc/routerConfig")
public class RouteConfigController {

    @Autowired
    private IRouterConfigService routerConfigService;

    /**
     * 设备信息新增单条
     *
     * @param addParam
     * @return
     */
    @PostMapping(value = "/add", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Result<Long> add(@RequestBody RouterConfigEntity addParam) {
        return Result.ok(routerConfigService.create(addParam));
    }

    @DeleteMapping("/{id}")
    public Result<Boolean> delete(@PathVariable("id") Long id) {
        return Result.ok(routerConfigService.delete(id));
    }

    /**
     * 设备信息查询单条
     *
     * @param
     * @return
     */
    @GetMapping(value = "/query/one", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Result<RouterConfigEntity> queryOne(Long id) {
        return Result.ok(routerConfigService.getOne(id));
    }

    /**
     * 设备信息分页查询
     *
     * @param query
     * @return
     */
    @GetMapping(value = "/query/page", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Result<IPage<RouterConfigEntity>> queryPage(@RequestParam("currentPage") long currentPage,
                                                       @RequestParam("pageSize") long pageSize,
                                                       RouterConfigEntity query) {
        return Result.ok(routerConfigService.queryPage(currentPage, pageSize, query));
    }

}
