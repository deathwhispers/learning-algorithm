package com.guangjian.algorithm.monotonicstack;

import java.util.ArrayDeque;

/**
 * 503. 下一个更大元素 II
 * 给定一个循环数组nums（nums[nums.length - 1]的下一个元素是nums[0]），返回nums中每个元素的 下一个更大元素 。
 * <p>
 * 数字 x的 下一个更大的元素 是按数组遍历顺序，这个数字之后的第一个比它更大的数，这意味着你应该循环地搜索它的下一个更大的数。
 * 如果不存在，则输出 -1
 *
 * @author yanggj
 * @version 1.0.0
 * @date 2023/2/16 16:46
 */
public class NextGreaterElements {

    /**
     * 暴力解法
     */
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            ans[i] = -1;
        }
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < 2 * n; j++) {
                if (nums[i] < nums[j % n]) {
                    ans[i] = nums[j % n];
                    // 必须要break，以免被后来的元素覆盖了
                    break;
                }
            }
        }
        return ans;
    }

    /**
     * 单调栈
     */
    public int[] nextGreaterElements2(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            ans[i] = -1;
        }
        // 栈保存下标
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < 2 * n; i++) {
            int cur = nums[i % n];
            while (!deque.isEmpty() && cur > nums[deque.peekLast()]) {
                Integer integer = deque.pollLast();
                ans[integer] = nums[i % n];
            }
            deque.addLast(i % n);
        }
        return ans;
    }
}
