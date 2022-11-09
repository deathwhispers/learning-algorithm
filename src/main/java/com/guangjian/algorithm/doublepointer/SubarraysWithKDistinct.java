package com.guangjian.algorithm.doublepointer;

/**
 * <h1>K 个不同整数的子数组</h1>
 * 给定一个正整数数组 nums和一个整数 k ，返回 num 中 「好子数组」 的数目。
 * <p>
 * 如果 nums 的某个子数组中不同整数的个数恰好为 k，则称 nums 的这个连续、不一定不同的子数组为 「好子数组 」。
 * <p>
 * 例如，[1,2,3,1,2] 中有 3 个不同的整数：1，2，以及 3。
 * <b>子数组</b> 是数组的 <b>连续</b> 部分。
 *
 * @author yanggj
 * @version 1.0.0
 * @date 2022/11/8 17:05
 */
public class SubarraysWithKDistinct {

    public int subarraysWithKDistinct(int[] nums, int k) {
        int ans = 0;
        int n = nums.length;
        for (int left = 0; left < n; left++) {
            int count = 0;
            int[] arr = new int[k];
            int right = left;
            while (count < k) {
                // 不包含，则 count++，并添加到 arr 中
                if (!contains(arr, nums[right])) {
                    arr[count++] = nums[right];
                }
                right++;
            }
            ans++;
        }
        return ans;
    }

    public boolean contains(int[] arr, int num) {
        for (int i : arr) {
            if (i == num) {
                return true;
            }
        }
        return false;
    }
}
