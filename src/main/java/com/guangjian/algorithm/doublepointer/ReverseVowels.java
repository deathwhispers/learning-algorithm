package com.guangjian.algorithm.doublepointer;

import java.util.HashSet;
import java.util.Set;

/**
 * <h1>反转元音字母</h1>
 * 给你一个字符串 s ，仅反转字符串中的所有元音字母，并返回结果字符串。
 * <p>
 * 元音字母包括 'a'、'e'、'i'、'o'、'u'，且可能以大小写两种形式出现。
 *
 * @author yanggj
 * @version 1.0.0
 * @date 2022/11/3 10:51
 */
public class ReverseVowels {
    // 保存原因字符
    static char[] vowels = new char[]{'a', 'e', 'i', 'o', 'u'};
    static Set<Character> set = new HashSet<>();

    static {
        for (char c : vowels) {
            set.add(c);
            set.add(Character.toUpperCase(c));
        }
    }

    /**
     * <h3>双指针法</h3>
     * 左右指针分别指向字符串的头尾，若两者均为元音字符，则交换位置并移动指针
     */
    public String reverseVowels(String s) {
        char[] chars = s.toCharArray();
        int i = 0, j = s.length() - 1;
        while (i < j) {
            // 非元音字符，直接移动指针
            if (!set.contains(chars[i])) i++;
            if (!set.contains(chars[j])) j--;
            if (set.contains(chars[i]) && set.contains(chars[j])) {
                // 左右指针均为原因字符，交换位置，并移动指针
                char temp = chars[i];
                chars[i] = chars[j];
                chars[j] = temp;
                i++;
                j--;
            }
        }
        return new String(chars);
    }
}
