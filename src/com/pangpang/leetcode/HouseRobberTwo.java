package com.pangpang.leetcode;

/**
 * @description: leetcode 213入室抢劫2
 * @author: leewake
 * @create: 2019-04-03 16:26
 **/

public class HouseRobberTwo {

    public static void main(String[] args) {
        int nums[] = {1,2};
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

        /*if (length == 2) {
            return nums[0] > nums[1] ? nums[0] : nums[1];
        }*/

        return Math.max(internal(nums, 0, length - 2), internal(nums, 1, length - 1));

    }

    private static int internal(int[] nums, int start, int end) {
        int preTwo = 0, preOne = 0;
        for (int i = start; i <= end; i++) {
            int cur = Math.max(nums[i] + preTwo, preOne);
            preTwo = preOne;
            preOne = cur;
        }

        return preOne;
    }

}
