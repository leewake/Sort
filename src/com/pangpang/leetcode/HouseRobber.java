package com.pangpang.leetcode;

/**
 * @description: leetcode 198入室抢劫
 * @author: leewake
 * @create: 2019-04-03 16:26
 **/

public class HouseRobber {

    public static void main(String[] args) {
        int nums[] = {1,2,3,1};
        int rob = rob(nums);
        System.out.println(rob);
    }

    public static int rob(int[] nums) {
        int length = nums.length;
        if (nums == null || length == 0) {
            return 0;
        }
        if (length == 1) {
            return nums[0];
        }

        if (length == 2) {
            return nums[0] > nums[1] ? nums[0] : nums[1];
        }

        int[] sum = new int[length];
        sum[0] = nums[0];
        sum[1] = nums[0] > nums[1] ? nums[0] : nums[1];

        for (int i = 2; i < length; i++) {
            sum[i] = Math.max((sum[i - 2] + nums[i]), sum[i - 1]);
        }

        return sum[length - 1];

    }

}
