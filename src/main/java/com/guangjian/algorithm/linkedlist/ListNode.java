package com.guangjian.algorithm.linkedlist;

/**
 * Definition for singly-linked list.
 *
 * @author yanggj
 * @version 1.0.0
 * @date 2022/11/15 17:59
 */
public class ListNode {
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
