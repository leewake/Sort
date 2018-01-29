package com.pangpang.sort;

public class MergeSort{
	public static void main(String[] args){
		
		int[] arr = new int[] {5, 37, 7, 4, 90, 39};
		
		System.out.print("初始数组:");
		printResult(arr);
		
		sort(arr, 0, arr.length - 1);
		
		System.out.print("归并排序后的数组:");
		printResult(arr);
	}
	
	public static void sort(int[] arr, int i, int j){
		if(i < j){
			int middle = (i + j) / 2;
			
			sort(arr, i, middle);
			sort(arr, middle + 1, j);
			merge(arr, i, middle, j);
		}
	}
	
	public static void merge(int[] arr, int i, int middle, int j){
		
		int[] temp = new int[arr.length];
		
		//���������������������β�±꣬middleָʾ�м�
		int m = i, n = middle + 1, k = i;
		
		while(m <= middle && n <= j){
			if(arr[m] < arr[n])
				temp[k++] = arr[m++];
			else
				temp[k++] = arr[n++];
		}
		
		while(m <= middle){
			temp[k++] = arr[m++];
		}
		
		while(n <= j){
			temp[k++] = arr[n++];
		}
		
		while(i <= j){
			arr[i] = temp[i++];
		}
		
	}
	
	public static void printResult(int[] arr){
		for(int i = 0; i < arr.length; i++){
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}
	
}