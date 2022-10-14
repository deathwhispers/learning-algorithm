package com.guangjian.algorithm.daily;

/**
 * <b>不同的子序列II</b>
 * <p>
 * 给定一个字符串 s，计算 s 的 不同非空子序列 的个数。因为结果可能很大，所以返回答案需要对 10^9 + 7 取余 。
 * <p>
 * 字符串的 子序列 是经由原字符串删除一些（也可能不删除）字符但不改变剩余字符相对位置的一个新字符串。
 * <p>
 * 例如，"ace" 是 "abcde" 的一个子序列，但 "aec" 不是。
 * <pre>
 * 示例 1：
 * 输入：s = "abc"
 * 输出：7
 * 解释：7 个不同的子序列分别是 "a", "b", "c", "ab", "ac", "bc", 以及 "abc"。
 * </pre>
 * <pre>
 * 示例 2：
 * 输入：s = "aba"
 * 输出：6
 * 解释：6 个不同的子序列分别是 "a", "b", "ab", "ba", "aa" 以及 "aba"。
 * </pre>
 * <pre>
 * 示例 3：
 * 输入：s = "aaa"
 * 输出：3
 * 解释：3 个不同的子序列分别是 "a", "aa" 以及 "aaa"。
 * </pre>
 *
 * @author yanggj
 * @version 1.0.0
 * @date 2022/10/14 17:44
 */
public class DistinctSubseqII {

    public int distinctSubseqII(String s) {
        int mod = (int) 1e9 + 7;
        int n = s.length();
        //之前新增的个数
        int[] preCount = new int[26];
        int curAns = 1;
        char[] chs = s.toCharArray();
        for (int i = 0; i < n; i++) {
            //新增的个数
            int newCount = curAns;
            //当前序列的个数 = 之前的 + 新增的 - 重复的
            curAns = ((curAns + newCount) % mod - preCount[chs[i] - 'a'] % mod + mod) % mod;
            //记录当前字符的 新增值
            preCount[chs[i] - 'a'] = newCount;
        }
        //减去空串
        return curAns - 1;
    }


    /**
     *
     */
    int MOD = (int) 1e9 + 7;
    public int solution2(String s) {
        int n = s.length(), ans = 0;
        int[][] f = new int[n + 1][26];
        for (int i = 1; i <= n; i++) {
            int c = s.charAt(i - 1) - 'a';
            for (int j = 0; j < 26; j++) {
                if (c != j) {
                    f[i][j] = f[i - 1][j];
                } else {
                    int cur = 1;
                    for (int k = 0; k < 26; k++) cur = (cur + f[i - 1][k]) % MOD;
                    f[i][j] = cur;
                }
            }
        }
        for (int i = 0; i < 26; i++) ans = (ans + f[n][i]) % MOD;
        return ans;
    }

    /**
     * 根据转移的依赖关系，实现上，我们并不需要真正记录每一个 f[i][X]，而可以直接记录一个总的不同子序列方案数 ans。
     * <p>
     * 这可以避免每次计算新状态时，都累加前一个 f[i−1][X] 的值，有效减低时空复杂度。
     */
    public int solution3(String s) {
        int n = s.length(), ans = 0;
        int[] f = new int[26];
        for (int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'a', prev = f[c];
            f[c] = (ans + 1) % MOD;
            ans = (ans + f[c]) % MOD;
            ans = (ans - prev + MOD) % MOD;
        }
        return ans;
    }

}
