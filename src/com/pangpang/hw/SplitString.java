package com.pangpang.hw;

import java.util.Scanner;

public class SplitString {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		while(scanner.hasNext()) {
			String next = scanner.next();
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append(next);
			int length = stringBuilder.length();
			int needFillLength = 8 - length % 8;
			if (needFillLength > 0 && needFillLength < 8) {
				while (needFillLength > 0) {
					stringBuilder.append("0");
					needFillLength--;
				}
			}

			String str = stringBuilder.toString();
			while (str.length() > 0) {
				System.out.println(str.substring(0, 8));
				str = str.substring(8);
			}
		}
	}
}