package com.pangpang.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * @description: leetcode 403 青蛙跳河
 * @author: leewake
 * @create: 2019-03-29 11:00
 **/

public class FrogJump {
    public static void main(String[] args) {
        int[] stones = {0,1,3,5,6,8,12,17};
        System.out.println(canCross(stones));

//        int[] anotherStones = {0,1,2,3,4,8,9,11};
//        System.out.println(canCross(anotherStones));
    }

    /**
     * <B>Description:</B> map存放在每个石头时可以向前跳的步数 <br>
     * <B>Create on:</B> 2019/3/29 下午2:41 <br>
     *
     * @author leewake
     */
    public static boolean canCross(int[] stones) {
        int len = stones.length;
        Map<Integer, HashSet<Integer>> map = new HashMap<>();
        for (int i = 0; i < len; i ++) {
            map.put(stones[i], new HashSet<>());
        }
        map.get(0).add(1);
        for (int i = 0; i < len - 1; i ++) {
            for (int step : map.get(stones[i])) {
                int to = step + stones[i];
                if (to == stones[len - 1]) {
                    return true;
                }
                HashSet<Integer> tmp = map.get(to);
                if (tmp != null) {
                    tmp.add(step);
                    if (step > 1) {
                        tmp.add(step - 1);
                    }
                    tmp.add(step + 1);
                }
            }
        }
        return false;
    }

}
