package com.pangpang.sort;

public class QuickSort{
	public static void main(String[] args){
		int[] arr = new int[] {12, 43, 16, 97, 34, 78, 34, 73};
		printResult(arr);
		quickSort(arr, 0, arr.length - 1);
		printResult(arr);
	}
	
	
	public static void quickSort(int[] arr, int left, int right){
		
		if(left < right){
			
			int division = partition(arr, left, right);
			quickSort(arr, left, division - 1);
			quickSort(arr, division + 1, right);
		}
		
	}
	
	
	public static int partition(int[] arr, int left, int right){
		
		int pivot = arr[left];
		
		while(left < right){
			while(left < right && pivot <= arr[right])
				right--;
			
			if(left < right){
				arr[left++] = arr[right];
			}
			
			while(left < right && pivot >= arr[left])
				left++;
			
			if(left < right){
				arr[right--] = arr[left];
			}
		}
		
		arr[left] = pivot;
		return left;
	}
	
	public static void printResult(int[] arr){
		for(int i = 0; i < arr.length; i++){
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}
}