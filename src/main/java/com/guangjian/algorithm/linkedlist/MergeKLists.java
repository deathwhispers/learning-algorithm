package com.guangjian.algorithm.linkedlist;

import java.util.ArrayDeque;
import java.util.PriorityQueue;

/**
 * <h1>合并K个升序链表</h1>
 * 给你一个链表数组，每个链表都已经按升序排列。
 * <p>
 * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
 *
 * @author yanggj
 * @version 1.0.0
 * @date 2022/11/16 17:48
 */
public class MergeKLists {

    public ListNode mergeKLists(ListNode[] lists) {
        // 哨兵节点
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        PriorityQueue<ListNode> q = new PriorityQueue<>((a, b) -> b.val - a.val);
        // 将所有节点的头节点放到一个集合中
        for (ListNode list : lists) {
            if (list != null) {
                q.add(list);
            }
        }
        while (!q.isEmpty()) {
            ListNode poll = q.poll();
            cur.next = poll;
            cur = cur.next;
            // 将list中的下一个节点放到队列中
            if (poll.next != null) {
                q.add(poll.next);
            }
        }
        return dummy.next;
    }

    ListNode findMin(ArrayDeque<ListNode> q) {
        ListNode res = q.peekFirst();
        for (ListNode listNode : q) {
            if (res.val > listNode.val) {
                res = listNode;
            }
        }
        return res;
    }
}
