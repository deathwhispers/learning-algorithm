package com.guangjian.algorithm.linkedlist;

import java.util.Deque;
import java.util.LinkedList;

/**
 * <h1>删除链表的倒数第 N 个结点</h1>
 * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 *
 * @author yanggj
 * @version 1.0.0
 * @date 2022/11/15 17:58
 */
public class RemoveNthFromEnd {

    /**
     * <h2>计算链表长度</h2>
     * 先遍历链表,计算链表长度
     * <p>
     * 然后再找到倒数第 n 个节点
     */
    public ListNode removeNthFromEnd1(ListNode head, int n) {
        // 哨兵
        ListNode dummy = new ListNode(0, head);
        int length = 0;
        // 计算链表的长度
        while (head != null) {
            ++length;
            head = head.next;
        }
        ListNode cur = dummy;
        // 找到待删除节点(链表长度 length - n + 1)
        for (int i = 1; i < length - n + 1; ++i) {
            cur = cur.next;
        }
        // 删除节点(只需将next -> next.next)
        cur.next = cur.next.next;
        return dummy.next;
    }


    /**
     * <h2>栈</h2>
     */
    public ListNode removeNthFromEnd2(ListNode head, int n) {
        // 哨兵
        ListNode dummy = new ListNode(0, head);
        // 新建栈, 将链表元素全部入栈
        Deque<ListNode> stack = new LinkedList<>();
        ListNode cur = dummy;
        // 链表元素入栈
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }
        // 入栈后,元素顺序逆序了, 取出 n 个元素 即可找到 倒数第 n 个节点
        for (int i = 0; i < n; ++i) {
            stack.pop();
        }
        // 找到倒数第 n 个节点的前一个节点
        ListNode prev = stack.peek();
        // 删除节点
        prev.next = prev.next.next;
        return dummy.next;
    }

    /**
     * <h2>快慢指针</h2>
     *
     */
    public ListNode removeNthFromEnd3(ListNode head, int n) {
        // 哨兵节点
        ListNode dummy = new ListNode(0, head);
        // 快指针
        ListNode fast = head;
        // 满指针
        ListNode slow = dummy;
        // 让快指针先走 n 步
        for (int i = 0; i < n; ++i) {
            fast = fast.next;
        }
        // 快慢指针同时移动, 当快指针走到链表结尾时, 慢指针正好指向倒数第 n 个节点前
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        // 删除节点
        slow.next = slow.next.next;
        ListNode ans = dummy.next;
        return ans;
    }
}
