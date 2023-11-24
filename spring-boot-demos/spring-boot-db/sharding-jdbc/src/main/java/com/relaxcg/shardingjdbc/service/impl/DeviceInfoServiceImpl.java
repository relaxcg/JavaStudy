package com.relaxcg.shardingjdbc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.relaxcg.common.utils.BeanUtils;
import com.relaxcg.shardingjdbc.entity.DeviceInfoEntity;
import com.relaxcg.shardingjdbc.mapper.DeviceInfoMapper;
import com.relaxcg.shardingjdbc.service.IDeviceInfoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

/**
 * @author relaxcg
 * @date 2023/11/24 16:05
 */
@Service
public class DeviceInfoServiceImpl extends ServiceImpl<DeviceInfoMapper, DeviceInfoEntity> implements IDeviceInfoService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long create(DeviceInfoEntity req) {
        DeviceInfoEntity entity = BeanUtils.copy(req, DeviceInfoEntity.class);
        save(entity);
        return entity.getId();
    }

    @Override
    public boolean delete(Long id) {
        return removeById(id);
    }

    @Override
    public DeviceInfoEntity getOne(Long id) {
        return getById(id);
    }

    @Override
    public IPage<DeviceInfoEntity> queryPage(long currentPage, long pageSize, DeviceInfoEntity query) {
        Page<DeviceInfoEntity> page = new Page<>(currentPage, pageSize);
        LambdaQueryWrapper<DeviceInfoEntity> queryWrapper = Wrappers.lambdaQuery(DeviceInfoEntity.class)
                .eq(Objects.nonNull(query.getType()), DeviceInfoEntity::getType, query.getType())
                .orderByAsc(DeviceInfoEntity::getType);
        return this.page(page, queryWrapper);
    }
}
