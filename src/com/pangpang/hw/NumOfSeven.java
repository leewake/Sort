package com.pangpang.hw;

import java.util.Scanner;

public class NumOfSeven {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		while (scanner.hasNext()) {
			int num = scanner.nextInt();
			int count = 0;
			for (int i = 7; i <= num; i++) {
				if (String.valueOf(i).contains("7") || (i % 7 == 0)) {
					count++;
				}
			}
			System.out.println(count);
		}
	}
}