package com.pangpang.algorithm;

/**
 * @description: 两两反转链表
 * 链接: https://itimetraveler.github.io/2017/01/31/%E3%80%90%E7%AE%97%E6%B3%95%E3%80%91%E5%8D%95%E9%93%BE%E8%A1%A8%E5%8F%8D%E8%BD%AC%EF%BC%8C%E4%B8%A4%E4%B8%A4%E5%8F%8D%E8%BD%AC/
 * @author: leewake
 * @create: 2019-01-23 11:45
 **/

public class ReverseLinkedListByPair {

    public static void main(String[] args) {

        Node a = new Node("a");
        Node b = new Node("b");
        a.setNext(b);
        Node c = new Node("c");
        b.setNext(c);
        Node d = new Node("d");
        c.setNext(d);
        Node e = new Node("e");
        d.setNext(e);
        Node f = new Node("f");
        e.setNext(f);

        printLinkedList(a);
        System.out.println();

//        Node result = revertList(a);
        Node result = reverseByPair(a);

        printLinkedList(result);
    }

    public static void printLinkedList(Node head) {
        while (head != null) {
            System.out.print(head.name + "->");
            head = head.getNext();
        }
        System.out.print("null");
    }

    public static Node reverseByPair(Node head) {

        if (head == null) {
            return head;
        }

        Node current = head;
        Node next = current.next;
        Node nextNext;
        Node previous = null;

        //只有一个节点的链表
        Node newHead = (next == null ? current : next);

        while(next != null) {
            nextNext = next.next;
            next.next = current;
            current.next = nextNext;

            if (previous != null) {
                previous.next = next;
            }

            if (nextNext == null) {
                break;
            }

            previous = current;
            current = nextNext;
            next = nextNext.next;
        }

        return newHead;
    }

    public static class Node {
        public final String name;
        private Node next;

        public Node(String name) {
            this.name = name;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public Node getNext() {
            return this.next;
        }

        @Override
        public String toString() {
            return this.name;
        }
    }

    public static Node revertList(Node node) {
        if (node == null) {
            return null;
        }
        // 存储结果的头节点
        Node result = node;
        // 当前节点的上一个节点
        Node lastNode = null;
        // 当前节点的上上一个节点
        Node oldNode = null;
        int i = 1;
        while (node != null) {
            if (i % 2 != 0) {
                // 奇数位，只需平移指针
                oldNode = lastNode;
                lastNode = node;
                node = node.getNext();
            } else {
                // 偶数位考虑元素反转
                if (oldNode == null) {
                    // 前二个元素反转，无需考虑oldNode，但要注意result赋值
                    lastNode.setNext(node.getNext());
                    node.setNext(lastNode);
                    result = node;
                } else {
                    // 3，4... 反转
                    oldNode.setNext(node);
                    lastNode.setNext(node.getNext());
                    node.setNext(lastNode);
                }
                // 这里lastNode已经和Node反转了，所以只需更新oldNode和Node
                oldNode = node;
                node = lastNode.getNext();
            }
            i++;
        }
        return result;
    }

}
