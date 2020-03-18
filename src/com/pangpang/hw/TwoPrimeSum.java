package com.pangpang.hw;

import java.util.Scanner;

public class TwoPrimeSum {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int num = Integer.parseInt(scanner.nextLine());
            int left = num / 2;
            int right = num / 2;

            while (left > 0 && right < num) {
                if (isPrime(left) && isPrime(right)) {
                    System.out.println(left);
                    System.out.println(right);
                    break;
                } else {
                    left--;
                    right++;
                }
            }
        }
    }

    public static boolean isPrime(int number) {
        for (int i = 2; i * i <= number; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}