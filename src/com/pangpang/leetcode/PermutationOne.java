package com.pangpang.leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * <B>Description:</B> leetcode 46 <br>
 * <B>Create on:</B> 2019/4/4 上午10:43 <br>
 *
 * @author leewake
 */
public class PermutationOne {
	

	public static void main(String[] args) {
		int[] nums = {1, 2, 3};
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
		permuteInternal(nums, result, new ArrayList<>(), new HashSet<>());
		return result;
	}

	private static void  permuteInternal (int[] nums, List<List<Integer>> result, List<Integer> curList, HashSet<Integer> set) {
		if (curList.size() == nums.length) {
			result.add(new ArrayList<>(curList));
			return;
		}else {
			for (int i = 0; i < nums.length; i++) {
				if (!set.contains(nums[i])) {
					curList.add(nums[i]);
					int lastPos = curList.size() - 1;
					set.add(nums[i]);
					permuteInternal(nums, result, curList, set);
					set.remove(nums[i]);
					curList.remove(lastPos);
				}
			}
		}
	}

}
