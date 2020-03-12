package com.pangpang.hw;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class ValidatePassword {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		while(scanner.hasNext()) {
			String str = scanner.next();
			if (str.length() <= 8) {
				System.out.println("NG");
				continue;
			}

			char[] chars = str.toCharArray();

			int numSign = 0;
			int lowerSign = 0;
			int upperSign = 0;
			int otherSign = 0;

			for (int i = 0; i < chars.length; i++) {
				if (numSign == 0 && (chars[i] >= '0' && chars[i] <= '9')){
					numSign = 1;
				} else if (lowerSign == 0 && (chars[i] >= 'a' && chars[i] <= 'z')){
					lowerSign = 1;
				} else if (upperSign == 0 && (chars[i] >= 'A' && chars[i] <= 'Z')){
					upperSign = 1;
				} else if (otherSign == 0){
					otherSign = 1;
				}

				if(numSign + lowerSign + upperSign + otherSign >= 3){
					break;
				}
			}
			if(numSign + lowerSign + upperSign + otherSign < 3){
				System.out.println("NG");
				continue;
			}

			int printSign = 0;
			Set<String> set = new HashSet<>();
			for (int j = 0; j < str.length()-2; j++){
				StringBuilder sb = new StringBuilder();
				String str1 = sb.append(chars[j]).append(chars[j+1]).append(chars[j+2]).toString();
				if(set.contains(str1)){
					System.out.println("NG");
					printSign = 1;
					break;
				}else{
					set.add(str1);
				}
			}

			if(printSign == 0){
				System.out.println("OK");
			}

		}
	}
}