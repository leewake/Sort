package com.pangpang.sort;
/** 
* @author  : lijingwei
* @version ：2018年3月12日 上午9:52:33 
*/
public class MidInsertSort {
	
	public static void main(String[] args){
		/*Scanner scanner = new Scanner(System.in);  
        System.out.println("请输入一个数组：");  
        String s1 = scanner.nextLine();  
        String[] s2 = s1.split(",");  
        int[] str = new int[s2.length];  
        for (int i = 0; i < str.length; i++) {  
            str[i] = Integer.valueOf(s2[i]);  
        }  */
		
		int[] arr = new int[] {12, 45, 23, 78, 25, 80, 60, 48};
		System.out.print("初始数组:");
		print(arr);
		sort(arr);
		System.out.print("二分插入排序后的数组:");
		print(arr);
	}
	
	public static void sort(int[] arr){         
		for (int i = 1; i < arr.length; i++) {
			if (arr[i] < arr[i - 1]) {
				int tmp = arr[i];
				int left = 0;
				int right = i - 1;
				while (left <= right){
					int mid = (left + right) / 2;
					if (tmp > arr[mid]) {
						left = mid + 1;
					} else {
						right = mid - 1;
					}
				}
				for (int j = i; j > left; j--) {
					arr[j] = arr[j - 1];
				}
				arr[left] = tmp;
			}
		}
	}
	
	public static void print(int[] arr){
		for(int i = 0; i < arr.length; i++){
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}
	
	private static void test(){
		int low = 1;
		int high = 5;
		int tmp1 = (low + high) / 2;
		int tmp2 = low + (high - low) / 2;
		System.err.println(tmp1);
		System.err.println(tmp2);
	}
}
