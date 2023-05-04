package com.cloud.test;

import org.junit.Test;

import java.util.Currency;
import java.util.Locale;

/**
 * @author zl
 * @date 2023/5/4 15:45
 */
public class MoneyTest {

    @Test
    public void testMoneyUnit() {
        System.out.println(Currency.getInstance("CNY").getSymbol(Locale.CHINA));
        System.out.println(Currency.getInstance("USD").getSymbol(Locale.US));
    }

}
