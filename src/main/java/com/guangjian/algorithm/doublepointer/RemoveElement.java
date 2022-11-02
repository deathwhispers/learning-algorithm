package com.guangjian.algorithm.doublepointer;

/**
 * <b>移除元素</b>
 * 给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素，并返回移除后数组的新长度。
 * <p>
 * 不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 原地 修改输入数组。
 * <p>
 * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
 * <p>
 *
 * @author yanggj
 * @version 1.0.0
 * @date 2022/11/2 11:22
 */
public class RemoveElement {

    /**
     * <b>双指针解法</b>
     * 根据题意，我们可以将数组分成「前后」两段：
     * <ul>
     *  <li>前半段是有效部分，存储的是不等于 val 的元素。</li>
     *  <li>后半段是无效部分，存储的是等于 val 的元素。</li>
     * </ul>
     * 最终答案返回有效部分的结尾下标。
     */
    public int removeElement1(int[] nums, int val) {
        int j = nums.length - 1;
        for (int i = 0; i <= j; i++) {
            if (nums[i] == val) {
                swap(nums, i--, j--);
            }
        }
        return j + 1;
    }

    void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    /**
     * <b>通用解法</b>
     * <p>
     * 先设定变量 idx ，指向待插入位置。 idx 初始值为 0
     * <p>
     * 然后从题目的「要求/保留逻辑」出发，来决定当遍历到任意元素 x 时，应该做何种决策：
     * <ul>
     *
     * <li>• 如果当前元素 x 与移除元素 val 相同，那么跳过该元素。</li>
     * <li>• 如果当前元素 x 与移除元素 val 不同，那么我们将其放到下标 idx 的位置，并让 idx 自增右移</li>
     * </ul>
     * 最终得到的 idx 即是答案。
     *
     * <h2>总结</h2>
     * <b>对于诸如「相同元素最多保留 k 位元素」或者「移除特定元素」的问题，更好的做法是从题目
     * 本身性质出发，利用题目给定的要求提炼出具体的「保留逻辑」，将「保留逻辑」应用到我们的
     * 遍历到的每一个位置。</b>
     */
    public int removeElement(int[] nums, int val) {
        int idx = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[idx++] = nums[i];
            }
        }
        return idx;
    }
}
