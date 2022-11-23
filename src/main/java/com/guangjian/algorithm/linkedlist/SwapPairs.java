package com.guangjian.algorithm.linkedlist;

/**
 * <h1>两两交换链表中的节点</h1>
 * 给你一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点。你必须在不修改节点内部的值的情况下完成本题（即，只能进行节点交换）。
 *
 * @author yanggj
 * @version 1.0.0
 * @date 2022/11/18 15:46
 */
public class SwapPairs {


    /**
     * <h2>递归解法</h2>
     * 我们可以设计一个递归函数，接受一个 ListNode 节点 root 作为参数，函数的作用是将 root
     * 后面的两个节点进行交换，交换完成后再将下一个节点传入 …
     * 交换的前提条件：节点 root 后面至少有两个节点。同时别忘了应用我们的「哨兵技巧」。
     */
    public ListNode swapPairs1(ListNode head) {

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        recursive(dummy);
        return dummy.next;
    }

    private void recursive(ListNode root) {
        // root 的下一个节点和下下个节点都不为空时,才进入递归
        if (root.next != null && root.next.next != null) {
            // 取出 root 后两个节点
            ListNode a = root.next;
            ListNode b = a.next;
            //
            root.next = b;
            // 保证不断链
            a.next = b.next;
            b.next = a;
            root = a;
            recursive(root);
        }
    }

    public ListNode swapPairs2(ListNode head) {
        // 哨兵
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        // 迭代节点
        ListNode cur = dummy;
        while (cur.next != null && cur.next.next != null) {
            // 需要注意不要断链
            ListNode a = cur.next;
            ListNode b = a.next;
            cur.next = b;
            // 以下两步顺序不能乱
            a.next = b.next;
            b.next = a;
            cur = a;
        }
        return dummy.next;
    }
}
