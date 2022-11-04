package com.guangjian.algorithm.doublepointer;

import java.util.Arrays;

/**
 * <b>最短无序连续子数组</b>
 * <p>
 * 给你一个整数数组 nums ，你需要找出一个 连续子数组 ，如果对这个子数组进行升序排序，那么整个数组都会变为升序排序。
 * <p>
 * 请你找出符合题意的 最短 子数组，并输出它的长度。
 *
 * @author yanggj
 * @version 1.0.0
 * @date 2022/11/4 14:05
 */
public class FindUnsortedSubarray {


    /**
     * <h2>双指针 + 排序</h2>
     * 创建一个新数组保存原数组升序排序的结果
     * 比较原数组和新数组，第一个不同的点即为 left、right
     */
    public int findUnsortedSubarray1(int[] nums) {
        int n = nums.length;
        int[] new_arr = nums.clone();
        Arrays.sort(new_arr);
        int left = 0, right = n - 1;
        while (left < right && nums[left] == new_arr[left]) {
            left++;
        }
        // 循环条件为 right >= left, 不能用 right >=0 作为条件
        while (right >= left && nums[right] == new_arr[right]) {
            right--;
        }
        return right - left + 1;
    }

    /**
     * <h2>双指针+线性扫描</h2>
     * <p>
     * 我们可以假设把这个数组分成三段，左段和右段是标准的升序数组，中段数组虽是无序的，但满足最小值大于左段的最大值，最大值小于右段的最小值。
     * <p>
     * 那么我们目标就很明确了，找中段的左右边界，我们分别定义为begin 和 end;
     * 分两头开始遍历:
     * <ul>
     *     <li>从左到右维护一个最大值max,在进入右段之前，那么遍历到的nums[i]都是小于max的，我们要求的end就是遍历中最后一个小于max元素的位置；</li>
     *     <li>同理，从右到左维护一个最小值min，在进入左段之前，那么遍历到的nums[i]也都是大于min的，要求的begin也就是最后一个大于min元素的位置。</li>
     * </ul>
     */
    public int findUnsortedSubarray2(int[] nums) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        int n = nums.length;
        int left = 0, right = 0;
        for (int i = 0; i < n; i++) {
            // 从左往右遍历，维护 max ，right 为最后一个小于 max 的元素的位置
            if (max > nums[i]) {
                right = i;
            } else {
                max = nums[i];
            }
            // 从右往左遍历，维护 min，left 为最后一个大于 min 元素的位置
            if (min < nums[n - i - 1]) {
                left = n - i - 1;
            } else {
                min = nums[n - i - 1];
            }
        }
        return right == 0 ? 0 : right - left + 1;
    }
}
