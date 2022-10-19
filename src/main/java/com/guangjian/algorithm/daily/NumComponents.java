package com.guangjian.algorithm.daily;

import java.util.HashSet;
import java.util.Set;

/**
 * <b>链表组件</b>
 * 给定链表头结点 head，该链表上的每个结点都有一个 唯一的整型值 。同时给定列表 nums，该列表是上述链表中整型值的一个子集。
 * <p>
 * 返回列表 nums 中组件的个数，这里对组件的定义为：链表中一段最长连续结点的值（该值必须在列表 nums 中）构成的集合。
 *
 * @author yanggj
 * @version 1.0.0
 * @date 2022/10/12 16:29
 */
public class NumComponents {

    /**
     * 此题需要计算组件的个数，只需在链表中计算有多少组件的起始位置即可。当一个节点满足以下条件之一时，它是组件的起始位置：
     * <li>节点的值在数组 nums 中且节点位于链表起始位置；</li>
     * <li>节点的值在数组 nums 中且节点的前一个点不在数组 nums 中。</li>
     * <p>
     * 遍历链表，计算出满足条件的点的个数即可。因为需要多次判断值是否位于数组 nums 中，用一个哈希集合保存数组 nums 中的点可以降低时间复杂度。
     */
    public int numComponents(ListNode head, int[] nums) {
        // 用 set 存放 nums
        Set<Integer> numsSet = new HashSet<>();
        for (int num : nums) {
            numsSet.add(num);
        }
        // 标记符
        boolean inSet = false;
        // 记录组件的个数
        int res = 0;
        while (head != null) {
            if (numsSet.contains(head.val)) {
                if (!inSet) {
                    inSet = true;
                    res++;
                }
            } else {
                inSet = false;
            }
            head = head.next;
        }
        return res;
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}