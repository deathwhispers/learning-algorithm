package com.guangjian.algorithm.monotonicstack;

import java.util.ArrayDeque;

/**
 * 接雨水
 *
 * @author yanggj
 * @version 1.0.0
 * @date 2023/2/16 11:48
 */
public class TrappingRainWater {

    /**
     * 暴力解法
     * 遍历所有柱子，计算每个柱子可以接到的雨水
     */
    public int trap(int[] height) {
        int n = height.length;
        int sum = 0;
        for (int i = 1; i < n - 1; i++) {
            int cur = height[i];
            // 找左右两侧柱子的最大值
            int leftMax = 0;
            for (int j = 0; j < i - 1; j++) {
                leftMax = Math.max(leftMax, height[j]);
            }
            // 两侧柱子比当前柱子低，跳过
            if (leftMax <= cur) {
                continue;
            }
            int rightMax = 0;
            for (int j = i + 1; j < n; j++) {
                rightMax = Math.max(rightMax, height[j]);
            }
            if (rightMax <= cur) {
                continue;
            }
            sum += Math.min(leftMax, rightMax) - cur;
        }
        return sum;
    }


    /**
     * 预处理左右侧最值
     */
    public int trap2(int[] height) {
        int n = height.length;
        int sum = 0;
        // 预处理左右侧柱子最大值
        int[] lMax = new int[n];
        int[] rMax = new int[n];
        lMax[0] = 0;
        rMax[n - 1] = 0;
        for (int i = 1; i < n; i++) {
            lMax[i] = Math.max(lMax[i - 1], height[i]);
        }
        for (int i = n - 2; i > 0; i--) {
            rMax[i] = Math.max(rMax[i + 1], height[i]);
        }
        for (int i = 1; i < n - 1; i++) {
            int cur = height[i];
            // 找左右两侧柱子的最大值
            int leftMax = lMax[i];
            // 两侧柱子比当前柱子低，跳过
            if (leftMax <= cur) {
                continue;
            }

            int rightMax = rMax[i];
            if (rightMax <= cur) {
                continue;
            }
            sum += Math.min(leftMax, rightMax) - cur;
        }
        return sum;
    }

    /**
     * 使用单调栈
     * 给定一个数组：如何求得任意位置的左半边的最大值和右边最大值
     *
     * @param height /
     * @return /
     */
    public int trap3(int[] height) {
        int n = height.length;
        int sum = 0;
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {


            while (!deque.isEmpty() && deque.peekLast() < height[i]) {
                int cur = deque.pollLast();
                if (deque.isEmpty()) {
                    continue;
                }
                int l = deque.peekLast();
                int r = i;
                int w = r - l + 1 - 2;
                int h = Math.min(height[l], height[r]) - cur;
                sum += w * h;
            }
            deque.addLast(i);
        }
        return sum;
    }
}
