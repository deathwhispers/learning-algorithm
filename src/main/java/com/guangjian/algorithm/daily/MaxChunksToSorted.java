package com.guangjian.algorithm.daily;

/**
 * 最多能完成排序的块
 * 给定一个长度为 n 的整数数组 arr ，它表示在 [0, n - 1] 范围内的整数的排列。
 * <p>
 * 我们将 arr 分割成若干 块 (即分区)，并对每个块单独排序。将它们连接起来后，使得连接的结果和按升序排序后的原数组相同。
 * <p>
 * 返回数组能分成的最多块数量。
 * <pre>
 * 示例 1:
 * 输入: arr = [4,3,2,1,0]
 * 输出: 1
 * 解释:
 * 将数组分成2块或者更多块，都无法得到所需的结果。
 * 例如，分成 [4, 3], [2, 1, 0] 的结果是 [3, 4, 0, 1, 2]，这不是有序的数组。
 * </pre>
 * <pre>
 * 示例 2:
 * 输入: arr = [1,0,2,3,4]
 * 输出: 4
 * 解释:
 * 我们可以把它分成两块，例如 [1, 0], [2, 3, 4]。
 * 然而，分成 [1, 0], [2], [3], [4] 可以得到最多的块数。
 * </pre>
 *
 * @author yanggj
 * @version 1.0.0
 * @date 2022/10/13 9:35
 */
public class MaxChunksToSorted {

    public int maxChunksToSorted(int[] arr) {
        int n = arr.length, ans = 0;
        // 用 left 表示当前划分块的左边界下标
        // 用 right 表示当前划分块的右边界下标
        // 用 min 表示当前划分块中元素最小值
        // 用 max 表示当前划分块中元素最大值
        int left = 0, right = 0;
        int min = n, max = -1;
        for (; right < n; right++) {
            // 元素最小值
            min = Math.min(min, arr[right]);
            // 元素最大值
            max = Math.max(max, arr[right]);
            if (left == min && right == max) {
                ans++;
                // 移到下一个划分块，left = right + 1
                left = right + 1;
                // 初始化 min 和 max 的值
                min = n;
                max = -1;
            }
        }
        return ans;
    }

}
