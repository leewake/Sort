package com.pangpang.hw;

import java.util.Scanner;

public class NumOfOne {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int num = scanner.nextInt();
		int count = 0;
		while(num != 0) {
			num &= num - 1;
			count++;
		}
		System.out.println(count);
	}
}