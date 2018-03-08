package com.pangpang.oj;
/** 
* @author  : lijingwei
* @version ：2018年3月7日 下午7:55:28 
*/
public class stringToNumber {
	public static void main(String[] args) {
		
		System.out.println(strToNum("huawei"));
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

}
