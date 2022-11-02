package com.guangjian.algorithm.doublepointer;

/**
 * <b>删除数组中重复元素</b>
 *
 * @author yanggj
 * @version 1.0.0
 * @date 2022/11/1 17:35
 */
public class RemoveDuplicates {

    /**
     * 使用双指针法
     * <p>
     * 指针 i 指向原数组, j 指向新的数组
     * <p>
     * 当 i,j 所指元素不一样时,将 i 赋值给 j
     */
    public int removeDuplicates(int[] nums) {
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == nums[j]) {
                // 值相同时，i指向下一个元素
            } else {
                // 值不同，则将 nums[i] 添加到 j 的下一个位置
                j++;
                nums[j] = nums[i];
            }
        }
        return j + 1;
    }
}
