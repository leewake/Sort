package com.pangpang.leetcode;

import java.util.Arrays;

/**
 * @description: leetcode 322 硬币找零
 * @author: leewake
 * @create: 2019-04-08 20:04
 **/

public class CoinChange {
    public static void main(String[] args) {
        int[] coins = {2};
        System.out.println(coinChange(coins, 3));
    }

    public static int coinChange(int[] coins, int amount) {
        if (coins == null || coins.length == 0 || amount < 0)
            return -1;

        int[] sum = new int[amount + 1];
        Arrays.fill(sum, amount + 1);
        Arrays.sort(coins);
        sum[0] = 0;

        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (coin > i)
                    break;
                sum[i] = Math.min(sum[i], sum[i - coin] + 1);
            }
        }
        return sum[amount] != amount + 1 ? sum[amount] : -1;
    }
}
