package com.pangpang.hw;

import java.util.Scanner;

public class LastTermLength {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String sc = scanner.nextLine();
		String[] split = sc.split(" ");
		System.out.println(split[split.length - 1].length());
	}
}