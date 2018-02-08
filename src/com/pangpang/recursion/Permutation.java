package com.pangpang.recursion;
/** 
* 字母的全排列
* @author  : lijingwei
* @version ：2018年1月29日 下午4:33:06 
*/
public class Permutation {
	
	private static Long total = 0L;
	
	public static void main(String[] args) {
		String[] strings = {"a", "b", "c"};
		permute(strings, 0, strings.length);
		System.out.println("全排列总数为:" + total);
	}
	
	private static <T> void swap(T[] arrays, int ori, int des){
		T tmp = arrays[ori];
		arrays[ori] = arrays[des];
		arrays[des] = tmp;
	}
	
	public static <T> void permute(T[] arrays, int start, int length){
		if (start == (length -1)) {
			for (T t : arrays) {
				System.out.print(t + " ");
			}
			System.out.println();
			total++;
		} else {
			for (int i = start; i < length; i++) {
				swap(arrays, start, i);
				permute(arrays, start + 1, length);
				swap(arrays, i, start);
			}
		}
	}

}
