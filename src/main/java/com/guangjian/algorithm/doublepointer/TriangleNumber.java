package com.guangjian.algorithm.doublepointer;

import java.util.Arrays;

/**
 * <h1>有效三角形的个数</h1>
 * 给定一个包含非负整数的数组 nums ，返回其中可以组成三角形三条边的三元组个数。
 *
 * @author yanggj
 * @version 1.0.0
 * @date 2022/11/8 10:16
 */
public class TriangleNumber {

    /**
     * <h3>排序+暴力循环</h3>
     */
    public int triangleNumber1(int[] nums) {
        int n = nums.length;
        int res = 0;
        Arrays.sort(nums);
        for (int i = 0; i < n; i++) {
            for (int j = i - 1; j >= 0; j--) {
                for (int k = j - 1; k >= 0; k--) {
                    if (nums[j] + nums[k] > nums[i]) {
                        res++;
                    }
                }
            }
        }
        return res;
    }

    /**
     * <h3>排序+双指针</h3>
     * 更进一步我们发现，当我们在枚举较大数下标 i，并在 [0,i) 范围内逐步减小下标（由于数组有
     * 序，也就是逐步减少值）找次大值下标 j 时，符合条件的 k
     * ′ 必然是从 0 逐步递增（这是由三角不等式 nums[k] + nums[j] > nums[i] 所决定的）。
     * <p>
     * 因此，我们可以枚举较大数下标 i 时，在 [0,i) 范围内通过双指针，以逐步减少下标的方式枚举
     * j，并在遇到不满足条件的 k 时，增大 k 下标。从而找到所有符合条件三元组的个数。
     *
     */
    public int triangleNumber2(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        int res = 0;
        for (int i = 0; i < n; i++) {
            int k = 0;
            for (int j = i - 1; k < j; j--) {
                while (k < j && nums[k] + nums[j] <= nums[i]) {
                    k++;
                }
                res += j - k;
            }
        }
        return res;
    }
}
