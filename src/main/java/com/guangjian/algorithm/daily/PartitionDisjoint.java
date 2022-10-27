package com.guangjian.algorithm.daily;

/**
 * <b>分割数组</b>
 * <p>
 * 给定一个数组 nums ，将其划分为两个连续子数组 left 和 right， 使得：
 *
 * <li>left 中的每个元素都小于或等于 right 中的每个元素。</li>
 * <li>left 和 right 都是非空的。</li>
 * <li>left 的长度要尽可能小。</li>
 * <p>
 * 在完成这样的分组后返回 left 的 长度 。
 *
 * @author yanggj
 * @version 1.0.0
 * @date 2022/10/27 9:46
 */
public class PartitionDisjoint {

    /**
     * <b>两次遍历</b>
     */
    public int solution1(int[] nums) {
        int n = nums.length;
        // minRight数组:记录右侧子数组的最小值;minRight[i]:nums[i]-nums[n-1]中的最小值
        int[] minRight = new int[n];
        minRight[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            minRight[i] = Math.min(nums[i], minRight[i + 1]);
        }

        // 左子数组中的最大值
        int maxLeft = 0;
        for (int i = 0; i < n - 1; i++) {
            maxLeft = Math.max(maxLeft, nums[i]);
            // 当左侧最大值比右侧最小值小时,结束
            if (maxLeft <= minRight[i + 1]) {
                return i + 1;
            }
            // 当左侧最大值比右侧最小值大时,则继续向右移动
        }
        return n - 1;
    }

    /**
     * <b>一次遍历</b>
     */
    public int solution2(int[] nums) {
        int n = nums.length;
        int leftMax = nums[0], leftPos = 0, curMax = nums[0];
        for (int i = 1; i < n - 1; i++) {
            curMax = Math.max(curMax, nums[i]);
            if (nums[i] < leftMax) {
                leftMax = curMax;
                leftPos = i;
            }
        }
        return leftPos + 1;
    }
}
