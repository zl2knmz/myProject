package com.cloud.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zl
 * @date 2022/5/6 17:15
 */
public class ListHelper {
    /**
     * 将list集合元素拆成独立集合
     * [a, b, c] -> [[a], [b], [c]]
     */
    public static <T> List<List<T>> chopped(List<T> list, final int L) {
        List<List<T>> parts = new ArrayList<>();
        final int N = list.size();
        for (int i = 0; i < N; i += L) {
            parts.add(new ArrayList<T>(list.subList(i, Math.min(N, i + L))));
        }
        return parts;
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        System.out.println(chopped(list,1));
    }
}
