package com.guangjian.algorithm.daily;

import java.util.ArrayList;
import java.util.List;

/**
 * 1790. 仅执行一次字符串交换能否使两个字符串相等
 * 给你长度相等的两个字符串 s1 和 s2 。一次 <b>字符串交换</b> 操作的步骤如下：选出某个字符串中的两个下标（不必不同），并交换这两个下标所对应的字符。
 * <p>
 * 如果对 <b>其中一个字符串</b> 执行 <b>最多一次字符串交换</b> 就可以使两个字符串相等，返回 true；否则，返回 false
 * <p>
 * 示例 1：
 * <pre>
 * 输入：s1 = "bank", s2 = "kanb"
 * 输出：true
 * 解释：例如，交换 s2 中的第一个和最后一个字符可以得到 "bank"
 * </pre>
 * 示例 2：
 * <pre>
 * 输入：s1 = "attack", s2 = "defend"
 * 输出：false
 * 解释：一次字符串交换无法使两个字符串相等
 * </pre>
 * 示例 3：
 * <pre>
 * 输入：s1 = "kelb", s2 = "kelb"
 * 输出：true
 * 解释：两个字符串已经相等，所以不需要进行字符串交换
 * </pre>
 * 示例 4：
 * <pre>
 * 输入：s1 = "abcd", s2 = "dcba"
 * 输出：false
 * </pre>
 *
 * @author yanggj
 * @version 1.0.0
 * @date 2022/10/11 10:11
 */
public class AreAlmostEqual {

    /**
     * 题目要求其中一个字符串执行最多一次字符交换使得两个字符串相等,
     * 意味着两个字符串中最多只存在两个位置 i,j 处字符不相等,
     * 此时我们交换 i,j 处字符可使其相等。
     * <p>
     * 设两个字符串分别为 s1,s2
     * <li>
     * 如果两个字符串相等，则不需要进行交换即可满足
     * </li>
     * <li>
     * 如果两个字符串不等，则一定存在两个位置 i，j 的字符不相等，需要交换两处字符使其相等，
     * 时必定满足s1[i]==s2[j],s1[j]==s2[i]，否则无法一次交换使其相等
     * </li>
     */
    public boolean areAlmostEqual(String s1, String s2) {
        int n = s1.length();
        List<Integer> diff = new ArrayList<Integer>();
        for (int i = 0; i < n; ++i) {
            if (s1.charAt(i) == s2.charAt(i)) continue;
            if (diff.size() >= 2) {
                return false;
            }
            diff.add(i);
        }
        if (diff.isEmpty()) {
            return true;
        }
        if (diff.size() != 2) {
            return false;
        }
        return s1.charAt(diff.get(0)) == s2.charAt(diff.get(1)) && s1.charAt(diff.get(1)) == s2.charAt(diff.get(0));
    }

}
