package com.pangpang.sort;

public class MaxHeapSort{
	
	public static void main(String[] args){
		
		int[] arr = new int[] {12, 45, 23, 68, 89, 34, 67, 40};
		
		System.out.print("初始数组:");
		
		print(arr);
		
		System.out.print("最大堆排序后的数组:");
		
		heapSort(arr);
		
	}
	
	public static void heapSort(int[] arr){
		for(int i = 0; i < arr.length; i++){
			creatMaxHeap(arr, arr.length - 1 - i);
			swap(arr, 0, arr.length - 1 - i);
			print(arr);
		}
		
	}
	
	public static void creatMaxHeap(int[] arr, int lastIndex){
		for(int i = (lastIndex - 1) / 2; i >= 0; i--){
			//���浱ǰ���
			int k = i;
			
			while(2 * k + 1 <= lastIndex){
				//biggerIndex���Ǽ�¼�ϴ����ֵ���ȸ�ֵΪ��ǰ�жϽ������ӽ��
				int biggerIndex = 2 * k + 1;
				
				//������ǰ�����ҽ����ڣ�����biggerIndex = lastIndex
				if(biggerIndex < lastIndex){
					//����ǰ������������ҽ�㣬��biggerIndex��1
					if(arr[biggerIndex] < arr[biggerIndex + 1]){
						biggerIndex++;
					}						
				}
				//����ǰ����ֵС��������ӽ���ֵ�������߽���
				if(arr[k] < arr[biggerIndex]){
					swap(arr, k, biggerIndex);
					k = biggerIndex;
				}else{
					break;
				}
			}	
		}
	}
	
	public static void swap(int[] arr, int i, int j){
		
		if(i == j){
			return;
		}
		
		arr[i] = arr[i] + arr[j];
		arr[j] = arr[i] - arr[j];
		arr[i] = arr[i] - arr[j];
		
		// int temp = 0;
		// arr[temp] = arr[i];
		// arr[i] = arr[j];
		// arr[j] = arr[temp];
	}
	
	public static void print(int[] arr){
		
		for(int i = 0; i < arr.length; i++){
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}
	
}