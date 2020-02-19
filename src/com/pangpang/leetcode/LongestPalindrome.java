package com.pangpang.leetcode;

import java.util.Arrays;

/**
 * @description: 最长公共回文串
 * @author: leewake
 * @create: 2019-12-19 19:34
 **/

public class LongestPalindrome {

    public static void main(String[] args) {

        String str = "ccc";
        String s = longestPalindrome(str);
        System.out.println(s);
        String dpResult = longestPalindromeOfDp(str);
        System.out.println("dp结果" + dpResult);
    }

    public static String longestPalindrome(String s) {
        int length = s.length();
        if (length < 2) {
            return s;
        }
        String result = "";
        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j <= length; j++) {
                String substring = s.substring(i, j);
                isLongestPalindrome(substring);
                if(isLongestPalindrome(substring) && substring.length() > result.length()) {
                    result = substring;
                }
            }
        }
        return result;
    }

    public static boolean isLongestPalindrome(String s) {
        for (int i = 0; i < s.length() / 2; i++) {
            boolean b = s.charAt(i) == s.charAt(s.length() - 1 - i);
            if (!b) {
                return false;
            }
        }
        return true;
    }

    public static String longestPalindromeOfDp(String str) {
        int length = str.length();
        if (length < 2) {
            return str;
        }

        //单个首字母也是回文串
        int maxLen = 1;
        String result = str.substring(0, 1);
        boolean[][] dp = new boolean[length + 1][length + 1];

        for (int right = 1; right < length; right++) {
            for (int left = 0; left < right; left++) {
                if (str.charAt(left) == str.charAt(right) && (right - left <= 2 || dp[left + 1][right - 1])) {
                    dp[left][right] = true;
                    int midLen = right - left + 1;
                    if (midLen > maxLen) {
                        maxLen = midLen;
                        result = str.substring(left, right + 1);
                    }
                }
            }
        }

//        System.out.println("dp:"+ Arrays.deepToString(dp));
        return result;
    }

}
