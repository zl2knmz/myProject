package com.cloud.algorithm;

/**
 * 字符串反转
 *
 * @author zl
 * @date 2022/5/12 13:48
 */
public class Solution2 {

    public static void main(String[] args) {
        String[] nums = new String[]{"h", "e", "l", "l", "o", "y"};
        int index = nums.length - 1;
        int count = nums.length / 2;
        String temp = null;
        for (int i = 0; i < count; i++) {
            temp = nums[i];
            nums[i] = nums[index - i];
            nums[index - i] = temp;
        }

        for (String num : nums) {
            System.out.println(num);
        }
    }

}
