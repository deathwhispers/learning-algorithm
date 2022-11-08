package com.guangjian.algorithm.doublepointer;

/**
 * <h1>最大连续 1 的个数</h1>
 * 给定一个二进制数组 nums ， 计算其中最大连续 1 的个数。
 *
 * @author yanggj
 * @version 1.0.0
 * @date 2022/11/4 11:07
 */
public class FindMaxConsecutiveOnes {

    public int findMaxConsecutiveOnes(int[] nums) {
        int ans = 0;
        int n = nums.length;
        int i = 0, j = 0;
        while (i < n) {
            if (nums[i] == 1) {
                j = i + 1;
                while (j < n && nums[j] == 1) {
                    j++;
                }
                ans = Math.max(ans, j - i );
                i = j + 1;
            } else {
                i++;
            }
        }
        return ans;
    }

}
