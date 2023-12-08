package org.relaxcg.sc.common.utils;

import java.math.BigDecimal;

/**
 * @author relaxcg
 * @date 2023/12/8 10:50
 */
public class BigDecimalUtil {

    public static BigDecimal add(BigDecimal a, BigDecimal b) {
        Assert.notNull(a, "入参不能为空");
        Assert.notNull(b, "入参不能为空");
        return a.add(b);
    }

    public static BigDecimal mul(BigDecimal a, int b) {
        Assert.notNull(a, "入参不能为空");
        return new BigDecimal(b).multiply(a);
    }
}
