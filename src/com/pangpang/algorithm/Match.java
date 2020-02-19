package com.pangpang.algorithm;

import java.util.Arrays;

/**
 * @description:
 * @author: leewake
 * @create: 2019-12-17 20:28
 **/

public class Match {

    public static void main(String[] args) {

        String str = "abc";
        String pattern = "*c";
        boolean result = isMatch(str, pattern);
        System.out.println("匹配结果为:" + result);
    }

    /**
     * <B>Description:</B> ? ---> 表示一个字母 <br>
     * <B>Description:</B> * ---> 表示0或多个字母 <br>
     * <B>Create on:</B> 2019/12/17 下午8:30 <br>
     *
     * 状态方程 f[i][j] = f[i-1][j-1] && str[i]==p[j]
     *
     * @author leewake
     */
    /*public static boolean isMatch(String str, String p) {
        String tmp = "";

        //多个连续*,可以按一个*处理
        for (int i = 0; i < p.length(); i++) {
            if (p.charAt(i) == '*') {
                tmp += '*';
                while (i < p.length() && p.charAt(i) == '*') i++;
            }
            if (i < p.length()) {
                tmp += p.charAt(i);
            }
        }
        p = tmp;

        boolean[][] f = new boolean[str.length() + 1][p.length() + 1];
        //初始化
        f[0][0] = true;

        if (p.length() > 0 && p.charAt(0) == '*') {
            f[0][1] = true;
        }

        for (int i = 1; i <= str.length(); i++) {
            for (int j = 1; j <= p.length(); j++) {
                if (p.charAt(j - 1) == '*') {
                    f[i][j] = f[i - 1][j - 1] || f[i - 1][j] || f[i][j - 1];
                } else {
                    f[i][j] = f[i - 1][j - 1] && (str.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '?');
                }
            }
        }

        return f[str.length()][p.length()];
    }*/

    public static boolean isMatch(String str, String pattern) {
        int slen = str.length();
        int plen = pattern.length();

        boolean[][] dp = new boolean[slen + 1][plen + 1];

        dp[0][0] = true;

        //初始化空字串
        for(int j = 1; j <= plen; j++){
            if(pattern.charAt(j - 1) == '*'){
                dp[0][j] = dp[0][j-1];
            }
        }
        //dp[1][0]未初始化

        for(int i = 1;i <= slen; i++){
            for(int j = 1; j <= plen; j++){
                if(str.charAt(i-1) == pattern.charAt(j-1) || pattern.charAt(j-1) == '?'){
                    dp[i][j] = dp[i-1][j-1];
                } else if(pattern.charAt(j-1) == '*'){
                    //dp[i][j-1],表示*代表是空字符,例如ab,ab*
                    //​ dp[i-1][j],表示*代表非空任何字符,例如abcd,ab*
                    dp[i][j] = dp[i][j - 1] || dp[i - 1][j];
                }
            }
        }

        System.out.println("dp:"+ Arrays.deepToString(dp));
        return dp[slen][plen];
    }

}
