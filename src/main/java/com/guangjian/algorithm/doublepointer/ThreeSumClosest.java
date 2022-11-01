package com.guangjian.algorithm.doublepointer;

import java.util.Arrays;

/**
 * <b>最接近的三数之和</b>
 * <p>
 * 给你一个长度为 n 的整数数组 nums 和 一个目标值 target。请你从 nums 中选出三个整数，使它们的和与 target 最接近。
 * <p>
 * 返回这三个数的和。
 * <p>
 * 假定每组输入只存在恰好一个解。
 *
 * @author yanggj
 * @version 1.0.0
 * @date 2022/11/1 16:13
 */
public class ThreeSumClosest {

    /**
     * <ol>
     * <li>对数组进行排序</li>
     * <li>取 i,j,k 三个指针，表示要找的三个数
     * j = i +1 ,k = n-1,分别向右、向左移动</li>
     *  <ul>
     *      <li>当 sum > target: k--;</li>
     *      <li>当 sum < target: j++;</li>
     *      <li>当 sum = target: return;</li>
     *  </ul>
     * </ol>
     */
    public int threeSumClosest(int[] nums, int target) {
        // 对数组进行排序
        Arrays.sort(nums);
        int n = nums.length;
        // 设置默认值
        int result = nums[0] + nums[1] + nums[2];
        for (int i = 0; i < n; i++) {
            int j = i + 1, k = n - 1;
            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                // 取 sum 和 result 中更接近 target 的值
                if (Math.abs(sum - target) < Math.abs(result - target)) result = sum;
                if (sum == target) return sum;
                if (sum > target) k--;
                if (sum < target) j++;
            }
        }
        return result;
    }
}