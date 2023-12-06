package com.cloud.test;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zl
 * @date 2023/10/24 18:58
 */
public class DemoTest {
    public static void main(String[] args) {
        int random = (int) (Math.random() * 5) + 1;
        System.out.println(random);

        long random1 = (int) (Math.random() * 20) + 3L;
        System.out.println(random1);
    }

    @Test
    public void numberTest() {
        String pricearea = null;
        Object priceValue = 0.0;
        if (priceValue instanceof Number) {
            double value = ((Number) priceValue).doubleValue();
            if (value > 0.05) {
                pricearea = priceValue + "元";
            } else {
                pricearea = "免费";
            }
        }
        System.out.println(pricearea);
    }

    @Test
    public void sortedListTest() {
//        List priceList = new ArrayList<>();
        List<Object> priceList = new ArrayList<>();
        priceList.add(1200.99);
        priceList.add(0);
        priceList.add(15.9d);
        priceList.add(0.0);
        priceList.add(1);
        priceList.add(12);
        priceList.add(32);
        priceList.add(1200);
        priceList.add(13.5d);
        priceList.add(1255.66);
        List<Double> priceSortList = priceList.stream().map(s -> Double.valueOf(s.toString())).sorted().collect(Collectors.toList());

        System.out.println(priceSortList);
        System.out.println(convertDoubleToString(priceSortList.get(0)));
    }

    /**
     * double去除末尾的“.0”
     */
    private String convertDoubleToString(double num) {
        BigDecimal bd = new BigDecimal(String.valueOf(num));
        return bd.stripTrailingZeros().toPlainString();
    }

}
