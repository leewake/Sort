public class ChooseSort{
	
	public static void main(String[] args){
		int[] arr = new int[] {12, 45, 23, 78, 25, 80, 60, 48};
		System.out.print("初始数组：");
		print(arr);
		sort(arr);
		System.out.print("插入排序后的数组：");
		print(arr);
	}
	
	
	public static void sort(int[] arr){
		for(int i = 0; i < arr.length; i++){
			int lowIndex = i;
			for(int j = i + 1; j < arr.length; j++){
				if(arr[j] < arr[lowIndex]){
					lowIndex = j;
				}
			}
			int temp = arr[i];
			arr[i] = arr[lowIndex];
			arr[lowIndex] = temp;
		}
	}
	
	public static void print(int[] arr){
		for(int i = 0; i < arr.length; i++){
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}
}