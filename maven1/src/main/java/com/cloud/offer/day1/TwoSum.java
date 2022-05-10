package com.cloud.offer.day1;

import java.util.HashMap;
import java.util.Map;

/**
 * 算法题：两数之和
 *
 * @author zl
 * @date 2022/5/10 22:36
 */
public class TwoSum {
    public static void main(String[] args) {
        int[] nums = new int[]{2, 7, 11, 15};
        int target = 26;
        int[] result = twoSum(nums, target);
        if (null != result) {
            for (int i : result) {
                System.out.println(i);
            }
        } else {
            System.out.println("无解");
        }

    }

    public static int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (target - nums[i] == nums[j]) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    public static int[] twoSum1(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>(16);
        for (int i = 0; i < nums.length; i++) {
            int temp = target - nums[i];
            if (map.containsKey(temp)) {
                return new int[]{map.get(temp), i};
            }
            map.put(nums[i], i);
        }
        return null;
    }
}
