package com.pangpang.algorithm;

import java.util.Stack;

/**
 * @description: 判断字符串是否可以通过入栈出栈得到
 * @author: leewake
 * @create: 2018-12-20 16:22
 **/

public class StackPopPush {

    public static void main(String[] args) {
        int[] origin = {1,2,3,4,5};
        int[] target = {1,2,3,4,5};
//        int[] target = {4,3,5,1,2};
        boolean result = isPop(origin, target);
        System.out.println("是否可以得到" + result);
    }

    public static boolean isPop(int[] origin, int[] target){
        Stack<Integer> stack = new Stack();
        int len = origin.length;
        int popIndex = 0;
        for (int i = 0; i < len; i++) {
            stack.push(origin[i]);
            while (!stack.isEmpty() && (target[popIndex] == stack.peek())){
                stack.pop();
                popIndex++;
            }
        }
        return stack.empty();
    }
}
