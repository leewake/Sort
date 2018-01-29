package com.pangpang.sort;

public class InsertSort{
	
	public static void main(String[] args){
		int[] arr = new int[] {12, 45, 23, 78, 25, 80, 60, 48};
		System.out.print("初始数组:");
		print(arr);
		sort(arr);
		System.out.print("插入排序后的数组:");
		print(arr);
	}
	
	public static void sort(int[] arr){
		for(int i = 1; i < arr.length; i++){
			int smallIndex = arr[i];
			int j = i - 1;
			while(j >= 0 && smallIndex < arr[j]){
				arr[j + 1] = arr[j];
				j--;
			}
			arr[j + 1] = smallIndex;
		}
	}
	
	
	public static void print(int[] arr){
		for(int i = 0; i < arr.length; i++){
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}
}