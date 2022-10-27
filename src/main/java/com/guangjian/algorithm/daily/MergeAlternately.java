package com.guangjian.algorithm.daily;

/**
 * <b>交替合并字符串</b>
 * <p>
 * 给你两个字符串 word1 和 word2 。请你从 word1 开始，通过交替添加字母来合并字符串。如果一个字符串比另一个字符串长，就将多出来的字母追加到合并后字符串的末尾。
 * <p>
 * 返回 <b>合并后的字符串</b> 。
 *
 * @author yanggj
 * @version 1.0.0
 * @date 2022/10/27 9:57
 */
public class MergeAlternately {
    public String solution(String word1, String word2) {
        int n = word1.length(), m = word2.length(), i = 0, j = 0;
        StringBuilder sb = new StringBuilder();
        while (i < n || j < m) {
            if (i < n) sb.append(word1.charAt(i++));
            if (j < m) sb.append(word2.charAt(j++));
        }
        return sb.toString();
    }
}
