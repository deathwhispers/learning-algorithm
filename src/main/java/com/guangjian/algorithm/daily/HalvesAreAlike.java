package com.guangjian.algorithm.daily;

import java.util.HashSet;

/**
 * <h1>判断字符串的两半是否相似</h1>
 * 给你一个偶数长度的字符串 s 。将其拆分成长度相同的两半，前一半为 a ，后一半为 b 。
 * <p>
 * 两个字符串 <b>相似</b> 的前提是它们都含有相同数目的元音（'a'，'e'，'i'，'o'，'u'，'A'，'E'，'I'，'O'，'U'）。注意，s 可能同时含有大写和小写字母。
 * <p>
 * 如果 a 和 b 相似，返回 true ；否则，返回 false 。
 *
 * @author yanggj
 * @version 1.0.0
 * @date 2022/11/11 9:19
 */
public class HalvesAreAlike {
    static HashSet<Character> set = new HashSet<>();

    static {
        set.add('a');
        set.add('e');
        set.add('i');
        set.add('o');
        set.add('u');
        set.add('A');
        set.add('E');
        set.add('I');
        set.add('O');
        set.add('U');
    }

    public boolean halvesAreAlike(String s) {
        int n = s.length();
        int m = n / 2;
        int ans = 0;
        char[] chars = s.toCharArray();
        for (int i = 0, j = m; i < m; i++, j++) {
            if (set.contains(s.charAt(i))) {
                ans++;
            }
            if (set.contains(s.charAt(j))) {
                ans--;
            }
        }
        return ans == 0;
    }

    public boolean halvesAreAlike2(String s) {
        String a = s.substring(0, s.length() / 2);
        String b = s.substring(s.length() / 2);
        String h = "aeiouAEIOU";
        int ans = 0;
        for (int i = 0; i < a.length(); i++) {
            if (h.indexOf(a.charAt(i)) >= 0) {
                ans++;
            }
        }
        for (int i = 0; i < b.length(); i++) {
            if (h.indexOf(b.charAt(i)) >= 0) {
                ans--;
            }
        }
        return ans == 0;
    }

}
