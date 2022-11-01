package com.guangjian.algorithm.doublepointer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <b>三数之和</b>
 * <p>
 * 给你一个整数数组 nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]] 满足 i != j、i != k 且 j != k ，同时还满足 nums[i] + nums[j] + nums[k] == 0 。请
 * <p>
 * 你返回所有和为 0 且不重复的三元组。
 * 注意：答案中不可以包含重复的三元组。
 *
 * @author yanggj
 * @version 1.0.0
 * @date 2022/11/1 15:51
 */
public class ThreeSum {

    /**
     * 对数组进行排序，使用三个指针 i 、 j 和 k 分别代表要找的三个数。
     * <ol>
     *  <li>通过枚举 i 确定第一个数，另外两个指针 j ， k 分别从左边 i + 1 和右边n - 1 往中间移动，找到满足 nums[i] + nums[j] + nums[k] == 0 的所有组合。</li>
     *  <li>j 和 k 指针的移动逻辑，分情况讨论 sum = nums[i] + nums[j] + nums[k] ：</li>
     *   <ul>
     *       <li>sum > 0： k 左移，使 sum 变小</li>
     *       <li>sum < 0： j 右移，使 sum 变大</li>
     *       <li>sum = 0：找到符合要求的答案，存起来
     *   </ul>
     * </ol>
     * 由于题目要求答案不能包含重复的三元组，所以在确定第一个数和第二个数的时候，要跳过数值
     * 一样的下标（在三数之和确定的情况下，确保第一个数和第二个数不会重复，即可保证三元组不
     * 重复）。
     */
    public List<List<Integer>> threeSum(int[] nums) {
        // 对数组进行排序
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            // 跳过重复的数
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            int j = i + 1, k = nums.length - 1;
            while (j < k) {
                // 跳过第二个重复的数
                while (j > i + 1 && j < nums.length && nums[j] == nums[j - 1]) j++;
                if (j >= k) break;
                int sum = nums[i] + nums[j] + nums[k];
                if (sum == 0) {
                    result.add(Arrays.asList(nums[i], nums[j], nums[k]));
                } else if (sum > 0) {
                    k--;
                } else if (sum < 0) {
                    j++;
                }
            }
        }
        return result;
    }
}
