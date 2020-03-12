package com.pangpang.leetcode;

/**
 * @description:
 * @author: leewake
 * @create: 2020-03-10 17:06
 **/

public class MoreThanHalfNum {

    public static void main(String[] args){
        int[] arr = new int[] {1,2,3,2,2,2,5,4,2};
        System.out.print("初始数组为:");
        print(arr);
        int result = solution(arr);
        System.out.print("出现最多的数为:" + result);
        print(arr);
    }

    public static int solution(int[] array) {
        if(array.length == 0){
            return 0;
        }
        if(array.length == 1){
            return array[0];
        }
        int count = 1;
        int num = array[0];

        for(int i = 1; i < array.length; i++){
            if(num != array[i]){
                count--;
                if(count == 0){
                    num = array[i];
                    count = 1;
                }
            }else{
                count++;
            }
        }

        count = 0;
        for(int i = 0; i < array.length; i++){
            if(array[i] == num){
                count++;
            }
        }
        return count > array.length/2 ? num : 0;
    }

    public static void print(int[] arr){
        for(int i = 0; i < arr.length; i++){
            System.out.print(arr[i] + " ");

        }
        System.out.println();
    }

}
