package com.pangpang.leetcode;

/**
 * @description: leetcode 518 硬币找零2
 * @author: leewake
 * @create: 2019-04-08 20:04
 **/

public class CoinChangeTwo {
    public static void main(String[] args) {
        int[] coins = {1, 2, 5};
        System.out.println(coinChange(coins, 5));
    }

    public static int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for(int coin : coins){
            for(int i = coin; i <= amount; i++) {
                dp[i] += dp[i - coin];
            }
        }

        return dp[amount];
    }
}
