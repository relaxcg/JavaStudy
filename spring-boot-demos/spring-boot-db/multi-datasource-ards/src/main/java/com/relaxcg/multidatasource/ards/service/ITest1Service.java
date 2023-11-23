package com.relaxcg.multidatasource.ards.service;

import com.relaxcg.multidatasource.ards.entity.Test1DO;

/**
 * @author relaxcg
 * @date 2023/11/22 15:35
 */
public interface ITest1Service {

    void save();

    Test1DO getFromDs1();
    Test1DO getFromDs2();
}
