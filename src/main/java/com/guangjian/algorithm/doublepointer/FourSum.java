package com.guangjian.algorithm.doublepointer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <b>四数之和</b>
 * <p>
 * 给你一个由 n 个整数组成的数组 nums ，和一个目标值 target 。
 * 请你找出并返回满足下述全部条件且不重复的四元组 [nums[a], nums[b], nums[c], nums[d]] （若两个四元组元素一一对应，则认为两个四元组重复）：
 * <p>
 * 0 <= a, b, c, d < n
 * a、b、c 和 d 互不相同
 * nums[a] + nums[b] + nums[c] + nums[d] == target
 * 你可以按 任意顺序 返回答案 。
 *
 * @author yanggj
 * @version 1.0.0
 * @date 2022/11/1 16:25
 */
public class FourSum {

    /**
     * 对数组进行排序，使用四个指针 i 、 j 、 k 和 p 分别代表要找的四个数。
     * <ol>
     *  <li>通过枚举 i 确定第一个数，枚举 j 确定第二个数，另外两个指针 k 和 p 分别
     *  从左边 j + 1 和右边 n - 1 往中间移动，找到满足
     *  nums[i] + nums[j] + nums[k] + nums[p] == t 的所有组合。</li>
     *  <li>k 和 p 指针的移动逻辑，分情况讨论</li>
     *   <ul>
     *    <li>sum > target: p 左移，使 sum 变小</li>
     *    <li>sum < target: k 右移，使 sum 变大</li>
     *    <li>sum = target: 将组合加入结果集， k 右移继续进行检查</li>
     *   </ul>
     * </ol>
     * 题目要求不能包含重复元素，所以我们要对 i 、 j 和 k 进行去重，去重逻辑是对于相同的
     * 数，只使用第一个。
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        // 对数组进行排序
        Arrays.sort(nums);
        int n = nums.length;
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            // 跳过重复数据
            while (i > 0 && i < n - 3 && nums[i] == nums[i - 1]) i++;
            for (int j = i + 1; j < n; j++) {
                // 跳过重复数据
                while (j > i + 1 && j < n - 2 && nums[j] == nums[j - 1]) j++;
                int k = j + 1, p = n - 1;
                while (k < p) {
                    // 跳过重复数据
                    while (k > j + 1 && k < n - 1 && nums[k] == nums[k - 1]) k++;
                    // 如果 k 跳过相同元素之后的位置超过了 p，本次循环结束
                    if (k >= p) break;
                    long sum = (long) nums[i] + nums[j] + nums[k] + nums[p];
                    if (sum == target) {
                        result.add(Arrays.asList(nums[i], nums[j], nums[k], nums[p]));
                        k++;
                    } else if (sum < target) {
                        k++;
                    } else if (sum > target) {
                        p--;
                    }
                }
            }
        }
        return result;
    }

    // 最后去重
    public List<List<Integer>> fourSum1(int[] nums, int target) {
        // 对数组进行排序
        Arrays.sort(nums);
        int n = nums.length;
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int k = j + 1, p = n - 1;
                while (k < p) {
                    // 数组越界
                    long sum = (long) nums[i] + nums[j] + nums[k] + nums[p];
                    if (sum == target) {
                        result.add(Arrays.asList(nums[i], nums[j], nums[k], nums[p]));
                        k++;
                    } else if (sum < target) {
                        k++;
                    } else if (sum > target) {
                        p--;
                    }
                }
            }
        }
        // 去除重复数据
        return result.stream().distinct().collect(Collectors.toList());
    }
}
