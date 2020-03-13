package com.pangpang.hw;

import java.util.Scanner;

public class StringPattern {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		while (scanner.hasNext()) {
			String pattern = scanner.next();//行数
			String str = scanner.next();//行数
			isMatch(str, pattern);
		}
	}

	public static void isMatch(String str, String pattern) {
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

		System.out.println(dp[slen][plen]);
	}
}