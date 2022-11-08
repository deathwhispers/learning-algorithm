package com.guangjian.algorithm.doublepointer;

/**
 * <h1>删除有序数组中的重复项</h1>
 * 给你一个有序数组 nums ，请你 原地 删除重复出现的元素，使每个元素 只出现一次 ，返回删
 * 除后数组的新长度。
 * <p>
 * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完
 * 成。
 *
 * @author yanggj
 * @version 1.0.0
 * @date 2022/11/1 17:35
 */
public class RemoveDuplicates {

    /**
     * <h3>使用双指针法</h3>
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


    /**
     * <h3>扩展：通用解法</h3>
     * 为了让解法更具有一般性，我们将原问题的「最多保留 1 位」修改为「最多保留 k 位」。
     * <p>
     * 对于此类问题，我们应该进行如下考虑：
     * <ul>
     *  <li>由于是保留 k 个相同数字，对于前 k 个数字，我们可以直接保留。</li>
     *  <li>对于后面的任意数字，能够保留的前提是：与当前写入的位置前面的第 k 个元素进行比较，不相同则保留。
     *  举个🌰，我们令 k=1 ，假设有样例： [3,3,3,3,4,4,4,5,5,5]</li>
     *   <ol>
     *   <li>设定变量 idx ，指向待插入位置。 idx 初始值为 0 ，目标数组为 []</li>
     *   <li>首先我们先让第 1 位直接保留（性质 1）。 idx 变为 1 ，目标数组为 [3]</li>
     *   <li>继续往后遍历，能够保留的前提是与 idx 的前面 1 位元素不同（性质 2），因此我们会跳过剩余的 3，
     *   将第一个 4 追加进去。 idx 变为 2 ，目标数组为[3,4]</li>
     *   <li>继续这个过程，跳过剩余的 4 ，将第一个 5 追加进去。 idx 变为 3 ，目标数组为 [3,4,5]</li>
     *   <li>当整个数组被扫描完，最终我们得到了目标数组 [3,4,5] 和 答案 idx 为 3 。</li>
     *   </ol>
     * </ul>
     */
    public int removeDuplicatesExt(int[] nums) {
        return process(nums, 1);
    }

    int process(int[] nums, int k) {
        int idx = 0;
        for (int x : nums) {
            if (idx < k || nums[idx - k] != x) nums[idx++] = x;
        }
        return idx;
    }

    /**
     * <b>基于上述解法我们还能做一点小剪枝：利用目标数组的最后一个元素必然与原数组的最后一个元
     * 素相同进行剪枝，从而确保当数组有超过 k 个最大值时，数组不会被完整扫描。</b>
     * <p>
     * <b>但需要注意这种「剪枝」同时会让我们单次循环的常数变大，所以仅作为简单拓展。</b>
     */
    public int removeDuplicatesExt2(int[] nums) {
        int n = nums.length;
        if (n <= 1) return n;
        return process(nums, 1, nums[n - 1]);
    }

    int process(int[] nums, int k, int max) {
        int idx = 0;
        for (int x : nums) {
            if (idx < k || nums[idx - k] != x) nums[idx++] = x;
            if (idx - k >= 0 && nums[idx - k] == max) break;
        }
        return idx;
    }

}
