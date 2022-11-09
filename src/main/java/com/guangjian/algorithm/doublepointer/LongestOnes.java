package com.guangjian.algorithm.doublepointer;

/**
 * <h1>最大连续1的个数 III</h1>
 * 给定一个二进制数组 nums 和一个整数 k，如果可以翻转最多 k 个 0 ，则返回 数组中连续 1 的最大个数 。
 *
 * @author yanggj
 * @version 1.0.0
 * @date 2022/11/9 15:09
 */
public class LongestOnes {

    /**
     * <h2>双指针</h2>
     * 维护左右两个指针 left,right,右指针始终右移;当 right - left - (1的个数) > k 时, 左指针右移
     * <p>
     * 因此我们可以使用「滑动窗口」的思路，动态维护一个左右区间 [left, right] 和维护窗口内和 total。
     * <p>
     * 右端点一直右移，左端点在窗口不满足「len - tol <= k」的时候进行右移，即可做到线程扫描的复杂度。
     */
    public int longestOnes(int[] nums, int k) {
        int n = nums.length;
        // 定义区间 [left,right]
        int left = 0, right = 0;
        // 记录区间 [left,right] 中间 1 的个数
        int total = 0;
        int ans = 0;
        while (right < n) {
            total += nums[right];
            // 当 k 个数不满足区间时, 需要将左指针右移
            while (right - left - total > k) {
                total -= nums[left];
                left++;
            }
            ans = Math.max(ans, right - left + 1);
            right++;
        }
        return ans;
    }
}
