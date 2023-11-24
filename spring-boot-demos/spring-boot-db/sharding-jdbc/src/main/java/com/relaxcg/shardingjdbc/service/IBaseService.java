package com.relaxcg.shardingjdbc.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;

/**
 * @author relaxcg
 * @date 2023/11/24 16:01
 */
public interface IBaseService<E> {

    Long create(E req);

    boolean delete(Long id);

    E getOne(Long id);

    IPage<E> queryPage(long currentPage, long pageSize, E query);
}
