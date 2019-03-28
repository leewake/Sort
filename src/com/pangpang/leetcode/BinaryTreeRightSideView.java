package com.pangpang.leetcode;


import com.pangpang.common.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @description: leetcode no 199 树的右视图
 * @author: leewake
 * @create: 2019-03-28 15:42
 **/

public class BinaryTreeRightSideView {
    public static void main(String[] args) {
        TreeNode root = initTree();
        List<Integer> result = rightSideView(root);
        printResult(result);
    }

    /**
     * <B>Description:</B> 初始化树 <br>
     * <B>Create on:</B> 2019/3/28 下午4:33 <br>
     *
     * @author leewake
     */
    public static TreeNode initTree() {
        TreeNode A = new TreeNode(1);
        TreeNode B = new TreeNode(2);
        TreeNode C = new TreeNode(3);
        TreeNode D = new TreeNode(4);
        TreeNode E = new TreeNode(5);
        A.setLeft(B);
        A.setRight(C);
        B.setLeft(null);
        B.setRight(E);
        C.setLeft(null);
        C.setRight(D);
        D.setLeft(null);
        D.setRight(null);
        E.setLeft(null);
        E.setRight(null);
        return A;
    }

    /**
     * <B>Description:</B> 根据每一层去遍历,每层最后一个即是最右结点 <br>
     * <B>Create on:</B> 2019/3/28 下午4:23 <br>
     *
     * @author leewake
     */
    public static List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList();

        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()) {
            int countPerFloor = queue.size();
            for (int i = 0; i < countPerFloor; i++) {
                TreeNode tmp = queue.poll();
                if (tmp.left != null) {
                    queue.add(tmp.left);
                }
                if (tmp.right != null) {
                    queue.add(tmp.right);
                }
                if (i == countPerFloor - 1) {
                    result.add(tmp.val);
                }
            }

        }

        return result;
    }

    /**
     * <B>Description:</B> 打印结果 <br>
     * <B>Create on:</B> 2019/3/28 下午4:37 <br>
     *
     * @author leewake
     */
    public static void printResult(List<Integer> result) {
        System.out.print("[");
        for (Integer val : result) {
            System.out.print(val + ",");
        }
        System.out.print("]");
    }

}
