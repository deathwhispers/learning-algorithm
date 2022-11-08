package com.guangjian.algorithm.daily;

/**
 * <b>统计一致字符串的数目</b>
 * <p>
 * 给你一个由不同字符组成的字符串 allowed 和一个字符串数组 words 。如果一个字符串的每一个字符都在 allowed 中，就称这个字符串是 <b>一致字符串</b> 。
 * <p>
 * 请你返回 words 数组中 <b>一致字符串</b> 的数目
 *
 * @author yanggj
 * @version 1.0.0
 * @date 2022/11/8 9:08
 */
public class CountConsistentStrings {

    public int countConsistentStrings(String allowed, String[] words) {
        int res = 0;
        for (String word : words) {
            int i = 0;
            for (; i < word.length(); i++) {
                if (!allowed.contains(String.valueOf(word.charAt(i)))) {
                    break;
                }
            }
            if (i == word.length())
                res++;
        }
        return res;
    }

}
