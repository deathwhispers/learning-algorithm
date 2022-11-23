package com.guangjian.algorithm.linkedlist;

/**
 * <h1>合并两个有序链表</h1>
 * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 *
 * @author yanggj
 * @version 1.0.0
 * @date 2022/11/16 17:22
 */
public class MergeTwoLists {

    /**
     * <h2>多路归并（哨兵技巧）</h2>
     * 做有关链表的题目，有个常用技巧：添加一个<b>虚拟头结点（哨兵）</b>，帮助简化边界情况的判断。
     * <p>
     * 由于两条链表本身就是有序的，只需要在遍历过程中进行比较即可：
     */
    public ListNode mergeTwoLists1(ListNode list1, ListNode list2) {
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
     * <h2>递归</h2>
     * 两个链表头部值较小的一个节点与剩下元素的 merge 操作结果合并
     * <p>
     * 如果 l1 或者 l2 一开始就是空链表 ，那么没有任何操作需要合并，所以我们只需要返回非空链表。
     * 否则，我们要判断 l1 和 l2 哪一个链表的头节点的值更小，然后递归地决定下一个添加到结果里的节点。如果两个链表有一个为空，递归结束。
     */
    public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        } else if (l1.val < l2.val) {
            l1.next = mergeTwoLists2(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists2(l1, l2.next);
            return l2;
        }
    }

}
