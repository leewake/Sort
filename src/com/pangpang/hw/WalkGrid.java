package com.pangpang.hw;

import java.util.Scanner;

public class WalkGrid {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		while (scanner.hasNext()) {
			int n = scanner.nextInt();//行数
			int m = scanner.nextInt();//列数
			int count = walk(n, m);

			System.out.println(count);
		}
	}

	public static int  walk(int n, int m) {
		if (n == 0 && m == 0) {
			return 0;
		}

		if (n == 0 || m == 0) {
			return 1;
		}

		return walk(n - 1, m) + walk(n, m - 1);
	}
}