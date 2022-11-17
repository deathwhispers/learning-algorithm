package com.guangjian.algorithm.daily;

import java.util.ArrayList;
import java.util.List;

/**
 * <h1>匹配子序列的单词数</h1>
 * 给定字符串 s 和字符串数组 words, 返回  words[i] 中是s的子序列的单词个数 。
 * <p>
 * 字符串的 子序列 是从原始字符串中生成的新字符串，可以从中删去一些字符(可以是none)，而不改变其余字符的相对顺序。
 * <p>
 * 例如， “ace” 是 “abcde” 的子序列。
 *
 * @author yanggj
 * @version 1.0.0
 * @date 2022/11/17 9:09
 */
public class NumMatchingSubseq {
    public int numMatchingSubseq(String s, String[] words) {
        List<Integer>[] pos = new List[26];
        for (int i = 0; i < 26; ++i) {
            pos[i] = new ArrayList<>();
        }
        for (int i = 0; i < s.length(); ++i) {
            pos[s.charAt(i) - 'a'].add(i);
        }
        int ans = 0;
        for (String word : words) {
            if (isSubseq(s, word)) {
                ans++;
            }
        }
        return ans;
    }

    public boolean isSubseq(String str, String sub) {
        int n = str.length();
        int m = sub.length();
        int j = 0;
        for (int i = 0; i < n && j < m; i++) {
            if (str.charAt(i) == sub.charAt(j)) {
                j++;
            }
        }
        return j == m;
    }

    public int binarySearch(List<Integer> list, int target) {
        int left = 0, right = list.size() - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (list.get(mid) > target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return list.get(left);
    }

    public int numMatchingSubseq2(String s, String[] words) {
        List<Integer>[] pos = new List[26];
        for (int i = 0; i < 26; ++i) {
            pos[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < s.length(); ++i) {
            pos[s.charAt(i) - 'a'].add(i);
        }
        int res = words.length;
        for (String w : words) {
            if (w.length() > s.length()) {
                --res;
                continue;
            }
            int p = -1;
            for (int i = 0; i < w.length(); ++i) {
                char c = w.charAt(i);
                if (pos[c - 'a'].isEmpty() || pos[c - 'a'].get(pos[c - 'a'].size() - 1) <= p) {
                    --res;
                    break;
                }
                p = binarySearch(pos[c - 'a'], p);
            }
        }
        return res;
    }
}
