package com.guangjian.algorithm.daily;

/**
 * <h1>全局倒置与局部倒置</h1>
 * 给你一个长度为 n 的整数数组 nums ，表示由范围 [0, n - 1] 内所有整数组成的一个排列。
 *
 * <b>全局倒置</b> 的数目等于满足下述条件不同下标对 (i, j) 的数目：
 * <p>
 * 0 <= i < j < n
 * nums[i] > nums[j]
 * <b>局部倒置</b> 的数目等于满足下述条件的下标 i 的数目：
 * <p>
 * 0 <= i < n - 1
 * nums[i] > nums[i + 1]
 * 当数组 nums 中 <b>全局倒置</b> 的数量等于 <b>局部倒置</b> 的数量时，返回 true ；否则，返回 false 。
 *
 * @author yanggj
 * @version 1.0.0
 * @date 2022/11/16 9:07
 */
public class IsIdealPermutation {

    /**
     * 一个局部倒置一定是一个全局倒置，因此要判断数组中局部倒置的数量是否与全局倒置的数量相等，只需要检查有没有非局部倒置就可以了。
     * 这里的非局部倒置指的是 nums[i]>nums[j]，其中 i<j−1。
     * <p>
     * 朴素的判断方法需要两层循环，设 n 是 nums 的长度，那么该方法的时间复杂度为 O(n^2)，无法通过。
     * <p>
     * 进一步的，对于每一个 nums[i] 判断是否存在一个 j(j>i+1) 使得 nums[i]>nums[j] 即可。
     * 这和检查 nums[i]>min(nums[i+2],…,nums[n−1]) 是否成立是一致的。
     * 因此我们维护一个变量 minSuffix=min(nums[i+2],…,nums[n−1])，倒序遍历 [0,n−3] 中的每个 i,
     * 如有一个 i 使得 nums[i]>minSuffix 成立，返回 false，若结束遍历都没有返回 false，则返回 true。
     * </p>
     */
    public boolean isIdealPermutation(int[] nums) {
        int n = nums.length, minSuff = nums[n - 1];
        // 找非局部倒置
        for (int i = n - 3; i >= 0; i--) {
            // 只要存在任意一个非局部倒置,就返回false
            if (nums[i] > minSuff) {
                return false;
            }
            minSuff = Math.min(minSuff, nums[i + 1]);
        }
        return true;
    }
}