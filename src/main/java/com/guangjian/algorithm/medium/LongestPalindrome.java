package com.guangjian.algorithm.medium;

/**
 * 最长回文字串
 *
 * @author yanggj
 * @version 1.0.0
 * @date 2022/10/10 9:54
 */
public class LongestPalindrome {

    /*
    中心扩散法
     */
    public String solution1(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        int strLen = s.length();
        int left = 0;
        int right = 0;
        int len = 1;
        int maxStart = 0;
        int maxLen = 0;

        for (int mid = 0; mid < strLen; mid++) {
            left = mid - 1;
            right = mid + 1;
            // 向左侧扩展，直到超过边界或遇到与中心字符不等跳出 while 循环
            while (left >= 0 && s.charAt(left) == s.charAt(mid)) {
                // 与 mid 字符一致，回文长度 +1
                len++;
                // left 字符与 mid 字符一致，继续左移
                left--;
            }

            // 向右侧扩展，直到超过边界或遇到与中心字符不等跳出 while 循环
            while (right < strLen && s.charAt(right) == s.charAt(mid)) {
                // 与 mid 字符一致，回文长度+1
                len++;
                // right 字符与 mid 字符一致，继续右移
                right++;
            }

            // 同时向左右两侧扩展
            while (left >= 0 && right < strLen && s.charAt(right) == s.charAt(left)) {
                // 注意此处，在最后一次循环中，即最长回文子串索引为：i~j，此时的 left=i-1，right=j+1
                len = len + 2;
                left--;
                right++;
            }
            if (len > maxLen) {
                maxLen = len;
                maxStart = left;
            }
            len = 1;
        }
        // 返回子串,从 maxStart 位开始，长度为 maxLen
        return s.substring(maxStart + 1, maxStart + maxLen + 1);
    }

    /*
    动态规划法
     */
    public String solution2(String s) {
        if (s == null || s.length() < 2) {
            return s;
        }
        int strLen = s.length();
        int maxStart = 0;  //最长回文串的起点
        int maxEnd = 0;    //最长回文串的终点
        int maxLen = 1;  //最长回文串的长度

        boolean[][] dp = new boolean[strLen][strLen];

        for (int r = 1; r < strLen; r++) {
            for (int l = 0; l < r; l++) {
                if (s.charAt(l) == s.charAt(r) && (r - l <= 2 || dp[l + 1][r - 1])) {
                    dp[l][r] = true;
                    if (r - l + 1 > maxLen) {
                        maxLen = r - l + 1;
                        maxStart = l;
                        maxEnd = r;
                    }
                }
            }
        }
        return s.substring(maxStart, maxEnd + 1);
    }

}
