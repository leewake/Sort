public class MaxHeapSort{
	
	public static void main(String[] args){
		
		int[] arr = new int[] {12, 45, 23, 68, 89, 34, 67, 40};
		
		System.out.print("初始数组：");
		
		print(arr);
		
		System.out.print("堆排序后的数组：");
		
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
			//保存当前结点
			int k = i;
			
			while(2 * k + 1 <= lastIndex){
				//biggerIndex总是记录较大结点的值，先赋值为当前判断结点的左子结点
				int biggerIndex = 2 * k + 1;
				
				//表明当前结点的右结点存在，否则biggerIndex = lastIndex
				if(biggerIndex < lastIndex){
					//若当前结点的左结点大于右结点，则biggerIndex加1
					if(arr[biggerIndex] < arr[biggerIndex + 1]){
						biggerIndex++;
					}						
				}
				//若当前结点的值小于其最大子结点的值，则两者交换
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