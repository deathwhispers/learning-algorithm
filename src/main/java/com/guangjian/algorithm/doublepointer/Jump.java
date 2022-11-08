package com.guangjian.algorithm.doublepointer;

/**
 * <h1>跳跃游戏 II</h1>
 * 给你一个非负整数数组 nums ，你最初位于数组的第一个位置。
 * <p>
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * <p>
 * 你的目标是使用最少的跳跃次数到达数组的最后一个位置。
 * <p>
 * 假设你总是可以到达数组的最后一个位置。
 *
 * @author yanggj
 * @version 1.0.0
 * @date 2022/11/2 11:42
 */
public class Jump {

    /**
     * <b>双指针 + 贪心 + 动态规划</b>
     *
     */
    public int jump(int[] nums) {
        int n = nums.length;
        int[] f = new int[n];
        for (int i = 1, j = 0; i < n; i++) {
            while (j + nums[j] < i) j++;
            f[i] = f[j] + 1;
        }
        return f[n - 1];
    }
}
