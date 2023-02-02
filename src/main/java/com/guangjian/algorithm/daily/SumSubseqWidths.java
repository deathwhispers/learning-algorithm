package com.guangjian.algorithm.daily;

import java.util.Arrays;

/**
 * <h1>子序列宽度之和</h1>
 * 一个序列的 <b>宽度</b> 定义为该序列中最大元素和最小元素的差值。
 * <p>
 * 给你一个整数数组 nums ，返回 nums 的所有非空 <b>子序列</b> 的 <b>宽度之和</b> 。
 * 由于答案可能非常大，请返回对 109 + 7 <b>取余</b> 后的结果。
 * <p>
 * <b>子序列</b> 定义为从一个数组里删除一些（或者不删除）元素，但不改变剩下元素的顺序得到的数组。
 * 例如，[3,6,2,7] 就是数组 [0,3,1,6,2,2,7] 的一个子序列。
 *
 * @author yanggj
 * @version 1.0.0
 * @date 2022/11/18 14:45
 */
public class SumSubseqWidths {
    static int N = 100010, MOD = (int) 1e9 + 7;
    static long[] p = new long[N];

    // 把每个位置的 2^k 计算出来
    static {
        p[0] = 1;
        for (int i = 1; i < N; i++) {
            p[i] = p[i - 1] * 2 % MOD;
        }
    }

    /**
     * <h2>数学</h2>
     * <h3>提示一：每个子序列对答案的贡献</h3>
     * 对于某个子序列而言，若其最大值为 a，最小值为 b，则该子序列对答案的贡献为 (a - b)。
     * <p>
     * 我们有若干个子序列，即有若干个 (a - b)，答案为所有 (a - b) 之和，我们称一个 (a - b) 为 item。
     * <p>
     * <h3>提示二：每个 nums[i] 参与了多少个 item 的组成，在最终展开式中又是如何</h3>
     * 对于每个 (a - b) 而言，a 和 b 均必然是具体的 nums[i]。
     * <p>
     * 同时易知若 nums[i] 作为了 k 个子序列的最小值，那么在最终表达式展开中，必然有 k 个 −nums[i]；
     * 同理若 nums[i] 作为了 k 个子序列的最大值，那么在最终表达式展开中，必然有 k 个 nums[i]。
     * <p>
     * <h3>提示三：统计每个 nums[i] 作为最值时，有多少个子序列</h3>
     * 先不考虑 nums[i] 的重复问题。
     * <p>
     * 若 nums[i] 作为子序列最小值时，首先 nums[i] 必选，小于 nums[i] 的必不选，
     * 而大于 nums[i] 的可选可不选，组合个数取决于大于 nums[i] 的数的个数，
     * 假设有 k 个，那么根据组合数原理，共有 2^k 个组合，即共有 2^k 个子序列。
     * 此时 nums[i] 对答案的贡献为 2^k * (-nums[i])
     * <p>
     * 同理，nums[i] 作为子序列最大值时，子序列个数取决于小于 nums[i] 的数的个数，假设有 k 个，此时 nums[i] 对答案的贡献为 2^k * nums[i]
     * <p>
     * <h3>提示四：如何快速得知比 nums[i] 大/小 的数的个数</h3>
     * 排序。
     * <p>
     * <h3>提示五：nums[i] 的重复问题</h3>
     * 无论是将 nums[i] 视作最大值还是最小值，我们的组合数均取决于某一侧的数的个数，因此不会答案正确性产生影响。
     * <p>
     * <h3>提示六：2^k 操作的重复计算问题</h3>
     * 将 nums[i] 视作最值，我们都需要统计两边数所产生的组合数个数，因此即使对于单个用例都会面临重复计算某个 2^k 的问题（对称性）。
     * <p>
     * 同时对于跨样例而言，我们仍会重复计算某些 2^k （尤其是较小的 k 值），
     * 为避免重复计算，我们可以通过打表预处理的方式算得所有可能要用到 2^k 结果，在使用的时候直接通过查表取得。
     */
    public int sumSubseqWidths(int[] nums) {
        // 对数组进行排序
        Arrays.sort(nums);
        long res = 0;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            // 计算 nums[i] 作为最大值的贡献
            long max_supply = (nums[i] * p[i]) % MOD;
            // 计算 nums[i] 作为最小值的贡献 nums[i] * 2 ^ (n -i -1)
            long min_supply = (nums[i] * p[n - i - 1]) % MOD;
            // 总体贡献
            res = (res + max_supply - min_supply) % MOD;
        }
        return (int) res;
    }


    public int sumSubseqWidths2(int[] nums) {
        final int MOD = 1000000007;
        Arrays.sort(nums);
        long res = 0;
        long x = nums[0], y = 2;
        for (int j = 1; j < nums.length; j++) {
            res = (res + nums[j] * (y - 1) - x) % MOD;
            x = (x * 2 + nums[j]) % MOD;
            y = y * 2 % MOD;
        }
        return (int) ((res + MOD) % MOD);
    }
}
