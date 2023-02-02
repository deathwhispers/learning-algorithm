package com.guangjian.algorithm.linkedlist;

/**
 * <h1> K 个一组翻转链表</h1>
 * 给你链表的头节点 head ，每 k 个节点一组进行翻转，请你返回修改后的链表。
 * <p>
 * k 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 * <p>
 * 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
 *
 * @author yanggj
 * @version 1.0.0
 * @date 2022/11/23 9:34
 */
public class ReverseKGroup {

    public ListNode reverseKGroup(ListNode head, int k) {

        ListNode dummy = new ListNode(0);
        dummy.next = head;





        return dummy.next;
    }
}
