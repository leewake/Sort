package com.pangpang.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
* 字母的全排列
* @author  : lijingwei
* @version ：2018年1月29日 下午4:33:06 
*/
public class PermutationTest {

	private static List<List<Integer>> result = new ArrayList<>();

	public static void main(String[] args) {
		int[] strings = {1};
		permute(strings);
		System.out.println(result.toString());
	}

	public static List<List<Integer>> permute(int[] nums) {
		permute(nums, 0, nums.length);
		return result;
	}

	public static void swap(int[] arr, int start, int end) {
		int tmp = arr[start];
		arr[start] = arr[end];
		arr[end] = tmp;
	}

	public static void permute(int[] arr, int start, int length){
		if(start == length - 1) {
			List<Integer> tmpList = new ArrayList<>();
			for (int i = 0; i < length; i++) {
				tmpList.add(new Integer(arr[i]));
			}
			result.add(tmpList);
		} else {
			for(int i = start; i < length; i++){
				swap(arr, start, i);
				permute(arr, start + 1, length);
				swap(arr, i, start);
			}
		}
	}

}
