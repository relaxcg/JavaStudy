package com.relaxcg.beandefinition;

import lombok.Data;

/**
 * @author relaxcg
 * @date 2023/11/23 11:37
 */
@Data
public class TestClass1 {

    private String name;

    private String age;

    private String ext;

    public TestClass1() {

    }

    public TestClass1(String name) {
        this.name = name;
    }
}
