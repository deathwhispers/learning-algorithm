package com.guangjian.algorithm.doublepointer;

import java.util.Arrays;
import java.util.List;

/**
 * <b>通过删除字母匹配到字典里最长单词</b>
 * <p>
 * 给你一个字符串 s 和一个字符串数组 dictionary ，找出并返回 dictionary 中最长的字符串，该字符串可以通过删除 s 中的某些字符得到。
 * <p>
 * 如果答案不止一个，返回长度最长且字母序最小的字符串。如果答案不存在，则返回空字符串。
 *
 * @author yanggj
 * @version 1.0.0
 * @date 2022/11/4 11:31
 */
public class FindLongestWord {

    /**
     * <h2>双指针 不排序</h2>
     * 根据题意，我们需要解决两个问题：
     * <ol>
     * <li>如何判断 dictionary 中的字符串 t 是否可以通过删除 s 中的某些字符得到；</li>
     * <li>如何找到长度最长且字典序最小的字符串。</li>
     * </ol>
     * 第 1 个问题实际上就是判断 t 是否是 s 的子序列
     * <p>
     * 第 2 个问题可以通过遍历 dictionary 中的字符串，并维护当前长度最长且字典序最小的字符串来找到。
     */
    public String findLongestWord1(String s, List<String> dictionary) {
        String ans = "";
        int max = 0;
        for (String str : dictionary) {
            // 指向 s
            int i = 0;
            // 指向 dict 中元素的下标
            int j = 0;
            while (i < s.length() && j < str.length()) {
                if (s.charAt(i) == str.charAt(j)) j++;
                i++;
            }
            if (j == str.length()) {
                // 符合条件
                if (j > max || (j == max && str.compareTo(ans) < 0)) {
                    max = j;
                    ans = str;
                }
            }
        }
        return ans;
    }

    /**
     * <h2>排序 + 双指针 + 贪心</h2>
     * 我们可以先对 dictionary 根据题意进行自定义排序：
     * <ol>
     * <li>长度不同的字符串，按照字符串长度排倒序；</li>
     * <li>长度相同的，则按照字典序排升序。</li>
     * </ol>
     * 然后我们只需要对 dictionary 进行顺序查找，找到的第一个符合条件的字符串即是答案。
     * <p>
     * 具体的，我们可以使用「贪心」思想的「双指针」实现来进行检查：
     * <ol>
     * <li>使用两个指针 i 和 j 分别代表检查到 s 和 dictionary[x] 中的哪位字符；</li>
     * <li>当 s[i] != dictionary[x][j] ，我们使 i 指针右移，直到找到 s 中第一位与dictionary[x][j] 对得上的位置，然后当 i 和 j 同时右移，匹配下一个字符；</li>
     * <li>重复步骤 2，直到整个 dictionary[x] 被匹配完。</li>
     * </ol>
     */
    public String findLongestWord2(String s, List<String> dictionary) {
        // 预处理：排序
        dictionary.sort((a, b) -> {
            if (a.length() != b.length()) return b.length() - a.length();
            return a.compareTo(b);
        });
        String ans = "";
        int max = 0;
        for (String str : dictionary) {
            // 指向 s
            int i = 0;
            // 指向 dict 中元素的下标
            int j = 0;
            while (i < s.length() && j < str.length()) {
                if (s.charAt(i) == str.charAt(j)) j++;
                i++;
            }
            if (j == str.length()) {
                // 符合条件
                if (j > max) {
                    max = j;
                    ans = str;
                }
            }
        }
        return ans;
    }

    /**
     * <h2>动态规划</h2>
     */
    public String findLongestWord3(String s, List<String> dictionary) {
        int m = s.length();
        int[][] f = new int[m + 1][26];
        Arrays.fill(f[m], m);

        for (int i = m - 1; i >= 0; --i) {
            for (int j = 0; j < 26; ++j) {
                if (s.charAt(i) == (char) ('a' + j)) {
                    f[i][j] = i;
                } else {
                    f[i][j] = f[i + 1][j];
                }
            }
        }
        String res = "";
        for (String t : dictionary) {
            boolean match = true;
            int j = 0;
            for (int i = 0; i < t.length(); ++i) {
                if (f[j][t.charAt(i) - 'a'] == m) {
                    match = false;
                    break;
                }
                j = f[j][t.charAt(i) - 'a'] + 1;
            }
            if (match) {
                if (t.length() > res.length() || (t.length() == res.length() && t.compareTo(res) < 0)) {
                    res = t;
                }
            }
        }
        return res;
    }

}
