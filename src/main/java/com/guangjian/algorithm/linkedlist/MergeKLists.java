package com.guangjian.algorithm.linkedlist;

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

    /**
     * <h2>多路归并 + 优先队列</h2>
     * 由于所有链表本身满足「升序」，一个直观的做法是，我们比较每条链表的头结点，选取值最小的节点，添加到结果中，
     * 然后更新该链表的的头结点为该节点的 next 指针。循环比较，直到所有的节点都被加入结果中。
     * <p>
     * 对应到代码的话，相当于我们需要准备一个「集合」，将所有链表的头结点放入「集合」，
     * 然后每次都从「集合」中挑出最小值，并将最小值的下一个节点添加进「集合」（如果有的话），
     * 循环这个过程，直到「集合」为空（说明所有节点都处理完，进过集合又从集合中出来）。
     * <p>
     * 优先队列（堆）则是满足这样要求的数据结构，而整个过程其实就是「多路归并」过程。
     */
    public ListNode mergeKLists1(ListNode[] lists) {
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

    /**
     * <h2>顺序合并</h2>
     * 我们可以想到一种最朴素的方法：用一个变量 ans 来维护以及合并的链表，第 i 次循环把第 i 个链表和 ans 合并，答案保存到 ans 中
     */
    public ListNode mergeKLists2(ListNode[] lists) {
        ListNode ans = null;
        for (ListNode list : lists) {
            ans = mergeTwoLists(ans, list);
        }
        return ans;
    }

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        // 边界条件，其中一个 list 为空时，直接返回另一个 list 就行
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }
        // 哨兵节点
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        // 将两个链表中的元素按序依次添加到当前链表中
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                cur.next = list1;
                cur = cur.next;
                list1 = list1.next;
            } else {
                cur.next = list2;
                cur = cur.next;
                list2 = list2.next;
            }
        }
        // 剩余链表中还未添加的元素，添加至链尾
        if (list1 != null) {
            cur.next = list1;
        }
        if (list2 != null) {
            cur.next = list2;
        }
        return dummy.next;
    }

    /**
     * <h2>分治合并</h2>
     * 考虑优化方法一，用分治的方法进行合并。
     * <p>
     * 将 k 个链表配对并将同一对中的链表合并；
     * 第一轮合并以后， k 个链表被合并成了 k/2 个链表，平均长度为 2n/k，然后是 k/4 个链表，k/8 个链表等等；
     * 重复这一过程，直到我们得到了最终的有序链表。
     */
    public ListNode mergeKLists3(ListNode[] lists) {
        return merge(lists, 0, lists.length - 1);
    }

    public ListNode merge(ListNode[] lists, int l, int r) {
        if (l == r) {
            return lists[l];
        }
        if (l > r) {
            return null;
        }
        int mid = (l + r) >> 1;
        return mergeTwoLists(merge(lists, l, mid), merge(lists, mid + 1, r));
    }

}
