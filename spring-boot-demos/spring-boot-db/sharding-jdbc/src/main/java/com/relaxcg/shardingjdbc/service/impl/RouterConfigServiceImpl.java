package com.relaxcg.shardingjdbc.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.relaxcg.common.utils.BeanUtils;
import com.relaxcg.shardingjdbc.entity.RouterConfigEntity;
import com.relaxcg.shardingjdbc.mapper.RouteConfigMapper;
import com.relaxcg.shardingjdbc.service.IRouterConfigService;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author relaxcg
 * @date 2023/11/24 16:06
 */
@Service
public class RouterConfigServiceImpl extends ServiceImpl<RouteConfigMapper, RouterConfigEntity> implements IRouterConfigService {

    @Override
    public Long create(RouterConfigEntity req) {
        RouterConfigEntity entity = BeanUtils.copy(req, RouterConfigEntity.class);
        save(entity);
        return entity.getId();
    }

    @Override
    public boolean delete(Long id) {
        return removeById(id);
    }

    @Override
    public RouterConfigEntity getOne(Long id) {
        return getById(id);
    }

    @Override
    public IPage<RouterConfigEntity> queryPage(long currentPage, long pageSize, RouterConfigEntity query) {
        return page(new Page<>(currentPage, pageSize), Wrappers.lambdaQuery(RouterConfigEntity.class)
                .eq(Objects.nonNull(query.getDeviceId()), RouterConfigEntity::getDeviceId, query.getDeviceId())
                .orderByAsc(RouterConfigEntity::getId));
    }
}
