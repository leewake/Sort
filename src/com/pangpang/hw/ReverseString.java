package com.pangpang.hw;

import java.util.Scanner;

public class ReverseString {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		while(scanner.hasNext()) {
			String[] split = scanner.nextLine().split(" ");
			int count = 0;
			for (String str : split) {
				count += Integer.parseInt(str);
			}

			if (count == 24) {
				System.out.println(true);
			} else {
				System.out.println(false);
			}
		}
	}
}