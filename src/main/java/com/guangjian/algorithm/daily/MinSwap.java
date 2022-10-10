package com.guangjian.algorithm.daily;

/**
 * 使序列递增的最小交换次数
 *
 * @author yanggj
 * @version 1.0.0
 * @date 2022/10/10 9:41
 */
public class MinSwap {

    /*
    801. 使序列递增的最小交换次数
我们有两个长度相等且不为空的整型数组 nums1 和 nums2 。在一次操作中，我们可以交换 nums1[i] 和 nums2[i]的元素。

例如，如果 nums1 = [1,2,3,8] ， nums2 =[5,6,7,4] ，你可以交换 i = 3 处的元素，得到 nums1 =[1,2,3,4] 和 nums2 =[5,6,7,8] 。
返回 使 nums1 和 nums2 严格递增 所需操作的最小次数 。

数组 arr 严格递增 且  arr[0] < arr[1] < arr[2] < ... < arr[arr.length - 1] 。

注意：用例保证可以实现操作。

示例 1:
输入: nums1 = [1,3,5,4], nums2 = [1,2,3,7]
输出: 1
解释:
交换 A[3] 和 B[3] 后，两个数组如下:
A = [1, 3, 5, 7] ， B = [1, 2, 3, 4]
两个数组均为严格递增的。
示例 2:

输入: nums1 = [0,3,5,8,9], nums2 = [2,1,4,6,9]
输出: 1
     */

    public int minSwap(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int[][] dp = new int[n][2];
        dp[0][0] = 0;
        dp[0][1] = 1;
        for (int i = 1; i < n; i++) {
            int at = dp[i - 1][0], bt = dp[i - 1][1];
            dp[i][0] = dp[i][1] = n;
            if (nums1[i] > nums1[i - 1] && nums2[i] > nums2[i - 1]) {
                dp[i][0] = Math.min(dp[i][0], at);
                dp[i][1] = Math.min(dp[i][1], bt + 1);
            }
            if (nums1[i] > nums2[i - 1] && nums2[i] > nums1[i - 1]) {
                dp[i][0] = Math.min(dp[i][0], bt);
                dp[i][1] = Math.min(dp[i][1], at + 1);
            }
        }
        return Math.min(dp[n - 1][0], dp[n - 1][1]);
    }

    /*
    因为求解的每一个状态都只与前一个状态有关，因此可以用滚动数组的方法将dp[][]替代，进行空间优化
     */
    public int minSwap2(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int a = 0;
        int b = 1;
        for (int i = 1; i < n; i++) {
            int at = a, bt = b;
            a = b = n;
            if (nums1[i] > nums1[i - 1] && nums2[i] > nums2[i - 1]) {
                a = Math.min(a, at);
                b = Math.min(b, bt + 1);
            }
            if (nums1[i] > nums2[i - 1] && nums2[i] > nums1[i - 1]) {
                a = Math.min(a, bt);
                b = Math.min(b, at + 1);
            }
        }
        return Math.min(a, b);
    }
}
