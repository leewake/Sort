package com.pangpang.oj;
/** 
 * 将字符串与数字相互转化
* @author  : lijingwei
* @version ：2018年3月7日 下午7:55:28 
*/
public class stringToNumber {
	public static void main(String[] args) {
		System.out.println(strToNum("huawei"));
		System.out.println(numToStr(104680767)); //对应的是"huawei"
		System.out.println();
		System.out.println(strToNum("zz"));
		System.out.println(numToStr(702)); //对应的是"zz"
		System.out.println();
		System.out.println(strToNum("zzzzzz"));
		System.out.println(numToStr(strToNum("zzzzzz")));
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
	
	/**
	 * 每次取余之后需要减26^i
	 * 而每次被除数应该除以26^(i+1),而不是26
	 * @param num
	 * @return
	 */
	private static String numToStr(int num) {
		String result = "";
		int mod, div = num;
		for (int i = 0; i < 6 && num > 0; i++) {
			mod = div % 26;
			if (mod == 0) {
				mod = 26;
			}
			result = (char) (mod + 96) + result;
			num -= mod * Math.pow(26, i);
			//debug错误的原因是div = num / 26;
			div =  num / (int)(Math.pow(26, i + 1));
		}
		return result;
	}

}
