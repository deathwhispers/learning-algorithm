package com.guangjian.algorithm.doublepointer;

import java.util.Arrays;

/**
 * <b>合并两个有序数组</b>
 * <p>
 * 给你两个按 <b>非递减顺序</b> 排列的整数数组 nums1 和 nums2，另有两个整数 m 和 n ，分别表示 nums1 和 nums2 中的元素数目。
 * <p>
 * 请你 合并 nums2 到 nums1 中，使合并后的数组同样按 <b>非递减顺序</b> 排列。
 * <p>
 * <b>注意：</b>最终，合并后数组不应由函数返回，而是存储在数组 nums1 中。
 * 为了应对这种情况，nums1 的初始长度为 m + n，其中前 m 个元素表示应合并的元素，后 n 个元素为 0 ，应忽略。
 * nums2 的长度为 n 。
 *
 * @author yanggj
 * @version 1.0.0
 * @date 2022/11/2 14:02
 */
public class MergeOrderedArray {

    /**
     * <b>借助额外空间+双指针</b>
     * 创建一个 m + n长度的新数组 arr,存放合并后的数据
     * 使用双指针,将 nums1 和 nums2 中的数据迁移到 arr 中
     * 最后将 arr 写回 nums1
     */
    public void merge1(int[] nums1, int m, int[] nums2, int n) {
        // 构建一个新的数组,用于存放合并后的结果
        int[] arr = new int[m + n];
        // 定义指针 i,j 分别指向 nums1,nums2
        int i = 0, j = 0;
        // 定义指针 idx 指向 arr
        int idx = 0;
        while (i < m && j < n) {
            // 将较小的值先放到 arr 中
            if (nums1[i] > nums2[j]) {
                arr[idx++] = nums2[j++];
            } else if (nums1[i] < nums2[j]) {
                arr[idx++] = nums1[i++];
            } else {
                // 相等时,将两个数组的值都放到 arr 中, 指针后移
                arr[idx++] = nums1[i++];
                arr[idx++] = nums2[j++];
            }
        }
        // 剩余未合并的元素
        while (i < m) {
            arr[idx++] = nums1[i++];
        }
        while (j < n) {
            arr[idx++] = nums2[j++];
        }
        // arr 复制到 nums1
        System.arraycopy(arr, 0, nums1, 0, arr.length);
    }

    /**
     * <b>先合并再排序</b>
     */
    public void merge2(int[] nums1, int m, int[] nums2, int n) {
        // 将 nums2 中的元素合并到 nums1(m ,m + n) 中
        System.arraycopy(nums2, 0, nums1, m, n);
        // 对 nums1 排序(升序)
        Arrays.sort(nums1);
    }

    /**
     * <b>原地合并(从前往后)</b>
     * 直接在 nums1 上进行合并操作
     * <ul>
     *  <li>nums1[i] > nums2[j]: 交换两数;
     *  <b>注意:交换前nums2是有序的,交换后nums1[i]可能并不是最小的,需要对nums2进行排序,</b>
     *  排序后再重复上述工作</li>
     *  <li>nums[i] <= nums2[j]: i++</li>
     * </ul>
     * 当 i > m 时, 将 nums2 中剩余的元素添充到 nums1
     */
    public void merge3(int[] nums1, int m, int[] nums2, int n) {
        int i = 0, j = 0;
        while (j < n) {
            if (i > m) {
                nums1[i++] = nums2[j++];
            } else {
                if (nums1[i] > nums2[j]) {
                    // 交换 nums1[i] nums2[j]
                    int temp = nums1[i];
                    nums1[i] = nums2[j];
                    nums2[j] = temp;
                    // 对 nums2 重新排序
                    Arrays.sort(nums2, j, n);
                } else {
                    i++;
                }
            }
        }
    }

    /**
     * <b>原地合并(从后到前)</b>
     */
    public void merge4(int[] nums1, int m, int[] nums2, int n) {
        int idx = m + n - 1;
        int i = m - 1, j = n - 1;
        while (i >= 0 && j >= 0) {
            if (nums1[i] > nums2[j]) {
                nums1[idx--] = nums1[i--];
            } else if (nums1[i] < nums2[j]) {
                nums1[idx--] = nums2[j--];
            } else {
                nums1[idx--] = nums1[i--];
                nums1[idx--] = nums2[j--];
            }
        }
        while (idx >= 0 && i >= 0) {
            nums1[idx--] = nums1[i--];
        }
        while (idx >= 0 && j >= 0) {
            nums1[idx--] = nums2[j--];
        }
    }
}
