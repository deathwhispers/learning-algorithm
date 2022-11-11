package com.guangjian.algorithm.doublepointer;

/**
 * <h1>通过连接另一个数组的子数组得到一个数组</h1>
 * 给你一个长度为 n 的二维整数数组 groups ，同时给你一个整数数组 nums 。
 * <p>
 * 你是否可以从 nums 中选出 n 个 <b>不相交</b> 的子数组，使得第 i 个子数组与 groups[i] （下标从 0 开始）完全相同，
 * 且如果 i > 0 ，那么第 (i-1) 个子数组在 nums 中出现的位置在第 i 个子数组前面。
 * （也就是说，这些子数组在 nums 中出现的顺序需要与 groups 顺序相同）
 * <p>
 * 如果你可以找出这样的 n 个子数组，请你返回 true ，否则返回 false 。
 * <p>
 * 如果不存在下标为 k 的元素 nums[k] 属于不止一个子数组，就称这些子数组是 不相交 的。子数组指的是原数组中连续元素组成的一个序列。
 *
 * @author yanggj
 * @version 1.0.0
 * @date 2022/11/10 14:31
 */
public class CanChoose {

    /**
     * 本质上是道模拟题。
     * <p>
     * 使用 i 指针代表 nums 当前枚举到的位置； j 代表 groups 中枚举到哪个数组。
     * count 代表匹配的数组个数。
     * <ul>
     * <li>当 i 开头的连续一段和 groups[j] 匹配： j 指针右移一位（代表匹配下一个数组），
     * i 指针右移 groups[j].length 长度。</li>
     * <li>当 i 开头的连续一段和 groups[j] 不匹配： i 右移一位。</li>
     * </ul>
     */
    public boolean canChoose(int[][] groups, int[] nums) {
        int n = nums.length, m = groups.length;
        int count = 0;
        // 遍历 nums ，判断 groups 是否和 nums 的 子数组匹配
        for (int i = 0, j = 0; i < n && j < m; ) {
            // groups 中的元素是按顺序与 nums 匹配的
            if (check(groups[j], nums, i)) {
                i += groups[j++].length;
                count++;
            } else {
                i++;
            }
        }
        return count == m;
    }

    /**
     * 判断 group 是否在 nums 的子数组
     */
    boolean check(int[] group, int[] nums, int i) {
        int j = 0;
        while (j < group.length && i < nums.length) {
            if (group[j++] != nums[i++]) {
                return false;
            }
        }
        return j == group.length;
    }
}
