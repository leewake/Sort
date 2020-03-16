package com.pangpang.hw;

import java.util.Arrays;
import java.util.Scanner;

public class TrainPermute {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		while(scanner.hasNext()) {
			int num = Integer.parseInt(scanner.nextLine());
			String[] arr = scanner.nextLine().split(" ");
			Arrays.sort(arr);
			permute(arr, 0, num);
		}
	}

	public static void permute(String[] arr, int start, int length) {
		if (start == length - 1) {
			for (String t : arr) {
				System.out.print(t + " ");
			}
			System.out.println();
		} else {
			for (int i = start; i < length; i++) {
				swap(arr, start, i);
				permute(arr, start + 1, length);
				swap(arr, i, start);
			}
		}

	}

	private static void swap(String[] arrays, int start, int end){
		String tmp = arrays[start];
		arrays[start] = arrays[end];
		arrays[end] = tmp;
	}

}