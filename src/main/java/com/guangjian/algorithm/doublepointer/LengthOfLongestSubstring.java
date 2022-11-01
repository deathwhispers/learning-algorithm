package com.guangjian.algorithm.doublepointer;

import java.util.HashMap;

/**
 * <b>无重复字符的最长子串</b>
 * <p>
 * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串的长度。
 *
 * @author yanggj
 * @version 1.0.0
 * @date 2022/11/1 14:52
 */
public class LengthOfLongestSubstring {

    /**
     * 定义两个指针 start 和 end ，表示当前处理到的子串是 [start,end] 。
     * <p>
     * [start,end] 始终满足要求：无重复字符。
     * <p>
     * 从前往后进行扫描，同时维护一个哈希表记录 [start,end] 中每个字符出现的次数。
     * <p>
     * 遍历过程中， end 不断自增，将第 end 个字符在哈希表中出现的次数加一。
     * <p>
     * 令 right 为 下标 end 对应的字符，当满足 map.get(right) > 1 时，代表此前出现过第 end 位对应的字符。
     * <p>
     * 此时更新 start 的位置（使其右移），直到不满足 map.get(right) > 1 （代表
     * [start,end] 恢复满足无重复字符的条件）。同时使用 [start,end] 长度更新答案。
     */
    public int lengthOfLongestSubstring(String s) {
        int ans = 0;
        // 记录每个字符出现的次数
        HashMap<Character, Integer> map = new HashMap<>();
        // start、end表示最长字串的区间
        int start = 0, end = 0;
        for (; end < s.length(); end++) {
            char right = s.charAt(end);
            map.put(right, map.getOrDefault(right, 0) + 1);
            // 说明在左边出现了重复的字符,指针右移
            while (map.get(right) > 1) {
                char left = s.charAt(start);
                map.put(left, map.get(left) - 1);
                start++;
            }
            ans = Math.max(ans, end - start + 1);
        }
        return ans;
    }
}
