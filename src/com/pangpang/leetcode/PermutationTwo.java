package com.pangpang.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * <B>Description:</B> leetcode 47 <br>
 * <B>Create on:</B> 2019/4/4 上午10:43 <br>
 *
 * @author leewake
 */
public class PermutationTwo {
	

	public static void main(String[] args) {
		int[] nums = {1, 1, 2};
		List<List<Integer>> arrays = permute(nums);
		for (List<Integer> array : arrays) {
			for (int num : array) {
				System.out.print(num + "---");
			}
			System.out.println();
		}
	}

	public static List<List<Integer>> permute(int[] nums) {
		List<List<Integer>> result = new ArrayList<>();
		if (nums == null || nums.length == 0)
			return result;
		Arrays.sort(nums);
		permuteInternal(nums, result, new ArrayList<>(), new boolean[nums.length]);
		return result;
	}

	private static void  permuteInternal (int[] nums, List<List<Integer>> result, List<Integer> curList, boolean[] used) {
		if (curList.size() == nums.length) {
			result.add(new ArrayList<>(curList));
			return;
		}else {
			int pre = nums[0] - 1;
			for (int i = 0; i < nums.length; i++) {
				if (used[i] == false && nums[i] != pre) {
					pre = nums[i];
					curList.add(nums[i]);
					int lastPos = curList.size() - 1;
					used[i] = true;
					permuteInternal(nums, result, curList, used);
					used[i] = false;
					curList.remove(lastPos);
				}
			}
		}
	}

}
