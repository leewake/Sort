package com.pangpang.hw;

import java.util.Scanner;

public class LevenshteinDistance {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String strOne = scanner.next();
            String strTwo = scanner.next();

            int lengthOne = strOne.length();
            int lengthTwo = strTwo.length();

            int[][] dp = new int[lengthOne + 1][lengthTwo + 1];
            dp[0][0] = 0;

            for (int i = 1; i < lengthOne + 1; i++) {
                dp[i][0] = i;
            }

            for (int i = 1; i < lengthTwo + 1; i++) {
                dp[0][i] = i;
            }

            int one, two, three = 0;
            for (int i = 1; i < lengthOne + 1; i++) {
                for (int j = 1; j < lengthTwo + 1; j++) {
                    one = dp[i - 1] [j] + 1;
                    two = dp[i][j - 1] + 1;
                    three = dp[i - 1][j - 1];
                    if (strOne.charAt(i - 1) == strTwo.charAt(j - 1)) {
                        dp[i][j] = three;
                    } else {
                        dp[i][j] = Math.min(Math.min(one, two), three + 1);
                    }
                }
            }
            System.out.println("1" + "/" + (dp[lengthOne][lengthTwo] + 1));
        }
    }
}