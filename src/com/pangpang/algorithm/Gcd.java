package com.pangpang.algorithm;
/** 
* @author  : lijingwei
* @version ：2018年3月13日 下午2:23:58 
*/
public class Gcd {
	
	public static void main(String[] args) {
		System.out.println("最大公约数为: " + findGcd(319, 319));
		System.out.println("最小公倍数为: " + findGln(319, 377));
	}

	private static int findGcd(int a, int b) {
		int mod;
		//使得a > b 
		if(a < b) {
			int tmp = a;
			a = b;
			b = tmp;
		}
		
		while (b != 0) {
			mod = a % b;
			a = b;
			b = mod;
			
		}
		return a;
	}
	
	private static int  findGln(int a, int b) {
		return a * b / findGcd(a, b);
	}
}
