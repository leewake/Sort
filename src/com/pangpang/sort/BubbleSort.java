package com.pangpang.sort;

public class BubbleSort{
	
	public static void main(String[] args){
		int[] arr = new int[] {12, 45, 2, 4, 8, 3, 2, 90, 45, 24, 80};
		System.out.print("初始数组为:");
		print(arr);
		sort(arr);
		System.out.print("冒泡排序后的数组为:");
		print(arr);
	}
	
	public static void sort(int[] arr){
		int temp;
		for(int i = 0; i < arr.length; i++){
			for(int j = arr.length - 1; j > i; j--){
				if(arr[j] < arr[j - 1]){
					temp = arr[j - 1];
					arr[j - 1] = arr[j];
					arr[j] = temp;
				}
			}
		}
	}
	
	public static void print(int[] arr){
		for(int i = 0; i < arr.length; i++){
			System.out.print(arr[i] + " ");
			
		}
		System.out.println();
	}
}