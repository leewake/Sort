package com.pangpang.hw;

import java.util.Scanner;

public class LongestIncreaseSubString {
	public static void main(String[] args) {
		System.out.println((int)'0');
		System.out.println((int)'9');
		System.out.println((int)'a');
		/*int[] arr = {2,1,5,3,6,4,8,9,7};
		solution(arr);*/
		Scanner in = new Scanner(System.in);
		while (in.hasNext()) {
			int num = in.nextInt();
			if (num <= 2) {
				System.out.println(0);
			}
			int[] members = new int[num];//存储每一个数据元素
			int[] left_queue = new int[num];//数据元素从左到右对应的最大递增子序列数
			int[] right_queue = new int[num];//数据元素从右到左对应的最大递增子序列数
			for (int i = 0; i < num; i++) {//初始化各个数组数据
				members[i] = in.nextInt();
				left_queue[i] = 1;
				right_queue[i] = 1;
			}

			for (int i = 0; i < num; i++) {
				for (int j = 0; j < i; j++) {
					if (members[i] > members[j] && left_queue[j] + 1 > left_queue[i])
						left_queue[i] = left_queue[j] + 1;
				}
			}
			for (int i = num - 1; i >= 0; i--) {
				for (int j = num - 1; j > i; j--) {
					if (members[i] > members[j] && right_queue[j] + 1 > right_queue[i])
						right_queue[i] = right_queue[j] + 1;
				}
			}
			int max = 0;
			for (int i = 0; i < num; i++) {
				if (left_queue[i] + right_queue[i] > max)
					max = left_queue[i] + right_queue[i];
			}
			System.out.println(num - max + 1);
		}
	}

	private static void solution(int[] arr) {
		int length = arr.length;
		int max = 0;
		int[] dp = new int[length];
		dp[0] = 1;
		for (int i = 1; i < length; i++) {
			for (int j = 0; j < i; j++) {
				if (arr[i] > arr[j] && dp[j] + 1 > dp[i]) {
					dp[i] = dp[j] + 1;
					if (dp[i] > max) {
						max = dp[i];
					}
				}
			}
		}
		System.out.println(max);
	}
}