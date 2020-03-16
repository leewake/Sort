package com.pangpang.hw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CommonSubStr {

	public static void main(String[] args) throws IOException {
		BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));
		String str1;
		while((str1 = bReader.readLine()) != null) {
			String str2 = bReader.readLine();
			int str1Len = str1.length();
			int str2Len = str2.length();

			if (str1Len <= str2Len) {
				getCommonSubStr(str1, str2, str1Len, str2Len);
			} else {
				getCommonSubStr(str2, str1, str2Len, str1Len);
			}
		}
	}

	private static void getCommonSubStr(String str1, String str2, int str1Len, int str2Len) {
		int startIndex = 0;//最大子字符串开始位置
		int max = 0;//最大长度

		int[][] dp = new int[str1Len + 1][str2Len + 1];

		for (int i = 1; i < str1Len +   1; i++) {
			for (int j = 1; j < str2Len + 1; j++) {
				if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
					dp[i][j] = dp[i - 1][j - 1] + 1;
					if (dp[i][j] > max) {
						max = dp[i][j];
						startIndex = i - max;
					}
				}
			}
		}

		System.out.println(str1.substring(startIndex, startIndex + max));
	}

}