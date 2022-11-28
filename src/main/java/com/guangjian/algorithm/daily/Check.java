package com.guangjian.algorithm.daily;

/**
 * <h1>检查数组是否经排序和轮转得到</h1>
 * <p>
 * 给你一个数组 nums 。nums 的源数组中，所有元素与 nums 相同，但按非递减顺序排列。
 * <p>
 * 如果 nums 能够由源数组轮转若干位置（包括 0 个位置）得到，则返回 true ；否则，返回 false 。
 * <p>
 * 源数组中可能存在 重复项 。
 * <p>
 * 注意：我们称数组 A 在轮转 x 个位置后得到长度相同的数组 B ，当它们满足 A[i] == B[(i+x) % A.length] ，其中 % 为取余运算。
 *
 * @author yanggj
 * @version 1.0.0
 * @date 2022/11/28 10:44
 */
public class Check {

    public boolean check(int[] nums) {

        int n = nums.length, cnt = 0;
        for (int i = 0, cur = 110; i < n; ) {
            int j = i;
            while (j + 1 < n && nums[j] <= nums[j + 1]) {
                if (nums[j++] > cur) return false;
            }
            if (nums[j] > cur) return false;
            cur = Math.min(cur, nums[i]);
            i = j + 1;
            cnt++;
        }
        return cnt <= 2;
    }
}
