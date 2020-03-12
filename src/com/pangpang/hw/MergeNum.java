package com.pangpang.hw;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class MergeNum {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Map<Integer, Integer> resultMap = new TreeMap<>();
		int total = Integer.parseInt(scanner.nextLine());
		for (int i = 0; i < total;i++){
			String[] mid = scanner.nextLine().split("\\s+");
			int index = Integer.parseInt(mid[0]);
			int val = Integer.parseInt(mid[1]);
			if (resultMap.containsKey(index)) {
				int newVal = resultMap.get(index) + val;
				resultMap.put(index, newVal);
			} else {
				resultMap.put(index, val);
			}
		}

		for (Map.Entry<Integer, Integer> entry: resultMap.entrySet()) {
			System.out.println(entry.getKey() + " " + entry.getValue());
		}
	}
}