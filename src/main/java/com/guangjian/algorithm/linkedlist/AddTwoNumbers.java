package com.guangjian.algorithm.linkedlist;

/**
 * <h1>两数相加</h1>
 * 给你两个 <b>非空</b> 的链表，表示两个非负的整数。它们每位数字都是按照 <b>逆序</b> 的方式存储的，并且每个节点只能存储 <b>一位</b> 数字。
 * <p>
 * 请你将两个数相加，并以相同形式返回一个表示和的链表。
 * <p>
 * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 *
 * @author yanggj
 * @version 1.0.0
 * @date 2022/11/11 16:04
 */
public class AddTwoNumbers {

    /**
     * <h2>朴素解法（哨兵技巧）</h2>
     * 从最低位至最高位，逐位相加，如果和大于等于 10，则保留个位数字，同时向前一位进 1
     * <p>
     * 如果最高位有进位，则需在最前面补 1。
     * <p>
     * 做有关链表的题目，有个常用技巧：添加一个虚拟头结点（哨兵），帮助简化边界情况的判断。
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // 添加哨兵节点
        ListNode dummy = new ListNode(0);
        // 当前指针
        ListNode cur = dummy;
        // 记录进位值
        int carry = 0;
        // 遍历两个链表,如果一个为空则补 0
        while (l1 != null || l2 != null) {
            // 如果当前链表为空(较短链表遍历完), 则将该位补 0
            int a = l1 != null ? l1.val : 0;
            int b = l2 != null ? l2.val : 0;
            // 两链表对应位 求和 再加上进位值
            int sum = a + b + carry;
            // sum % 10 当前节点的值(如: sum = 10, 则当前节点为 0 )
            cur.next = new ListNode(sum % 10);
            // 进位(如 sum = 10,则进位为 1)
            carry = sum / 10;
            cur = cur.next;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        // 最终进位如果大于0, 则添加到最后一个节点
        if (carry > 0) {
            cur.next = new ListNode(carry);
        }
        return dummy.next;
    }

}