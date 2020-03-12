package com.pangpang.hw;

import java.util.Arrays;
import java.util.Scanner;

public class RandomAndNoRepate {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		while(scanner.hasNext()) {
			int num = scanner.nextInt();
			int[] arr = new int[num];
			for (int i = 0; i < num; i++) {
				arr[i] = scanner.nextInt();
			}
			Arrays.sort(arr);


			for (int i = 0; i < num; i++) {
				if (i == 0 || arr[i - 1] != arr[i]) {
					System.out.println(arr[i]);
				}
			}
		}
	}
}