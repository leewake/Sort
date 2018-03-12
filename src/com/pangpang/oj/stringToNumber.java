package com.pangpang.oj;
/** 
 * 将字符串与数字相互转化
* @author  : lijingwei
* @version ：2018年3月7日 下午7:55:28 
*/
public class stringToNumber {
	public static void main(String[] args) {
		System.out.println(strToNum("huawei"));
		System.out.println(numToStr(104680767));
	}
	
	private static int strToNum(String str){
		if (str == null) {
			return 0;
		}
		int result = 0;
		int len = str.length() - 1;
		byte[] bytes = str.getBytes();
		for (int i = 0; i <= len; i++) {
			result += (bytes[i] - 96) * Math.pow(26, (len - i));
		}
		return result;
	}
	
	private static String numToStr(int num){
		String result = "";
		int mod, div = num;
		for (int i = 0; i < 6; i++) {
			mod = div % 26;
			result = (char) (mod + 96) + result;
			num -= mod * Math.pow(26, i);
			div = div / 26;
			System.err.println("mod = " + mod + ", div = " + div + ", result = " + result);
		}
		return result;
	}

}
