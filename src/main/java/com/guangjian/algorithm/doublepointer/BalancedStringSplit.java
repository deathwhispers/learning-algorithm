package com.guangjian.algorithm.doublepointer;

/**
 * <h1>分割平衡字符串</h1>
 * 在一个 <b>平衡字符串</b> 中，'L' 和 'R' 字符的数量是相同的。
 * <p>
 * 给你一个平衡字符串 s，请你将它分割成尽可能多的平衡字符串。
 * <p>
 * 注意：分割得到的每个字符串都必须是平衡字符串，且分割得到的平衡字符串是原平衡字符串的连续子串
 * <p>
 * 返回可以通过分割得到的平衡字符串的 <b>最大数量</b>
 *
 * @author yanggj
 * @version 1.0.0
 * @date 2022/11/9 17:22
 */
public class BalancedStringSplit {

    /**
     * <h2>贪心</h2>
     * 题目确保了 s 为一个平衡字符串，即必然能分割成若干个 LR 子串。
     * <p>
     * 一个合法的 LR 子串满足 L 字符和 R 字符数量相等，常规检查一个字符串是否为合格的 LR
     * 子串可以使用 O(n) 的遍历方式，可以使用记录前缀信息的数据结构，而对于成对结构的元素
     * 统计，更好的方式是转换为数学判定，使用 1 来代指 L 得分，使用 -1 来代指 R 得分。
     * <p>
     * 那么一个子串为合格 LR 子串的充要条件为 整个 LR 子串的总得分为 0。
     */
    public int balancedStringSplit(String s) {
        int ans = 0;
        int n = s.length();
        int right = 0;
        int score = 0;
        while (right < n) {
            if (s.charAt(right) == 'L') {
                score++;
            } else {
                score--;
            }
            if (score == 0) {
                ans++;
            }
            right++;
        }
        return ans;
    }

    /**
     * 优雅版
     */
    public int balancedStringSplit2(String s) {
        char[] cs = s.toCharArray();
        int n = cs.length;
        int ans = 0;
        for (int i = 0; i < n; ) {
            int j = i + 1, score = cs[i] == 'L' ? 1 : -1;
            while (j < n && score != 0) score += cs[j++] == 'L' ? 1 : -1;
            i = j;
            ans++;
        }
        return ans;
    }
}
