package com.pangpang.hw;

import java.util.Scanner;

public class SimilarInteger {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		float num = scanner.nextFloat();
		int integer = (int) num;

		if (num - integer >= 0.5) {
			integer += 1;
		}
		System.out.println(integer);
	}

}