package com.pangpang.leetcode;

/**
 * @description: leetcode 198入室抢劫
 * @author: leewake
 * @create: 2019-04-03 16:26
 **/

public class HouseRobber {

    public static int rob(int[] nums) {
        int length = nums.length;
        int[] sum = new int[length];
        sum[0] = nums[0];
        boolean include = true;
        for (int i = 1; i < length; i++) {
            if (include) {
                if (sum[i - 1] > nums[i]) {
                    sum[i] = sum[i - 1];
                    include = false;
                } else {
                    sum[i] = nums[i];
                }
            } else {
                sum[i] = sum[i -1] + nums[i];
                include = true;
            }
        }

        return sum[length - 1];

    }

}
