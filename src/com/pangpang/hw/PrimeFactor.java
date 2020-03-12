package com.pangpang.hw;

import java.util.Scanner;

public class PrimeFactor {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		long num = Long.parseLong(scanner.next());
		getPrimer(num);
	}

	public static void getPrimer(long num){
		for (int i= 2; i <= num; i++){
			if (num % i == 0){
				System.out.print(i + " ");
				getPrimer(num / i);
				break;
			}
			if (i == num){
				System.out.print( i + "");
			}
		}
	}

}