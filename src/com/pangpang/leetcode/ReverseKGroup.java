package com.pangpang.leetcode;

import com.pangpang.common.ListNode;

/**
 * @description: leetcode 25. K 个一组翻转链表
 * @author: leewake
 * @create: 2019-11-14 17:07
 **/

public class ReverseKGroup {

    /**
     * <B>Description:</B> 头插法 <br>
     * <B>Create on:</B> 2019/11/15 上午10:00 <br>
     *
     * @author leewake
     */
    public static ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        head = dummy;
        ListNode p = head, q = p;
        ListNode cur = null;
        while(q != null){
            //让q先跑k步，判断区间内节点是否够k个
            for(int i = 0; i < k && q != null; i++){
                q = q.next;
            }
            //不够k个，直接跳出
            if(q == null) break;
            //否则反转子区间内的节点
            cur = p.next;
            for(int i = 1; i < k; i++){
                ListNode x = cur.next;//要反转节点的下一个节点
                cur.next = x.next;
                //TODO 这一步 如何理解 与这一段区间相连的尾结点
                x.next = p.next;
                p.next = x;
                System.out.println("cur: " + cur.val + ", p: " + p.val + ", x: " + x.val);
            }
            //开始下一个区间
            p = cur;
            q = p;
        }
        return head.next;
    }

    public static ListNode initNode() {
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        ListNode listNode4 = new ListNode(4);
        ListNode listNode5 = new ListNode(5);
        ListNode listNode6 = new ListNode(6);
        ListNode listNode7 = new ListNode(7);
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode5;
        listNode5.next = listNode6;
        listNode6.next = listNode7;
        return listNode1;
    }

    public static void printNode(ListNode head) {
        while(head != null) {
            System.out.print(head.val + "---");
            head = head.next;
        }
    }

    public static void main(String[] args) {
        ListNode head = initNode();
        System.out.println("反转前链表为:");
        printNode(head);
        System.out.println();

        int step = 3;
        ListNode newHead = reverseKGroup(head, step);
        System.out.println();
        System.out.println(step + "步反转后链表为:");
        printNode(newHead);
    }



}
