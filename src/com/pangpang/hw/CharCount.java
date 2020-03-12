package com.pangpang.hw;

import java.util.Scanner;

public class CharCount {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String str = scanner.nextLine().toLowerCase();
		String cha = scanner.nextLine().toLowerCase();
		char[] chars = str.toCharArray();
		int count = 0;
		for (int i = 0; i < chars.length; i++) {
			if (chars[i] == cha.charAt(0)) {
				count++;
			}
		}
		System.out.println(count);
	}
}