package com.relaxcg.shardingjdbc.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.relaxcg.common.web.Result;
import com.relaxcg.shardingjdbc.entity.DeviceInfoEntity;
import com.relaxcg.shardingjdbc.service.IDeviceInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * @author relaxcg
 * @date 2023/11/24 16:22
 */
@RestController
@RequestMapping(value = "/api/sharding/jdbc/device")
public class DeviceInfoController {

    @Autowired
    private IDeviceInfoService deviceInfoService;

    /**
     * 设备信息新增单条
     *
     * @param addParam
     * @return
     */
    @PostMapping(value = "/add", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Result<Long> add(@RequestBody DeviceInfoEntity addParam) {
        return Result.ok(deviceInfoService.create(addParam));
    }

    @DeleteMapping("/{id}")
    public Result<Boolean> delete(@PathVariable("id") Long id) {
        return Result.ok(deviceInfoService.delete(id));
    }

    /**
     * 设备信息查询单条
     *
     * @param
     * @return
     */
    @GetMapping(value = "/query/one", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Result<DeviceInfoEntity> queryOne(Long id) {
        return Result.ok(deviceInfoService.getOne(id));
    }

    /**
     * 设备信息分页查询
     *
     * @param query
     * @return
     */
    @GetMapping(value = "/query/page", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Result<IPage<DeviceInfoEntity>> queryPage(@RequestParam("currentPage") long currentPage,
                                                     @RequestParam("pageSize") long pageSize,
                                                     @RequestParam DeviceInfoEntity query) {
        return Result.ok(deviceInfoService.queryPage(currentPage, pageSize, query));
    }

}
