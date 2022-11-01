package com.guangjian.algorithm.daily;

/**
 * @author yanggj
 * @version 1.0.0
 * @date 2022/11/1 9:48
 */
public class ArrayStringsAreEqual {


    /**
     * 逐个字符进行比较
     */
    public boolean solution(String[] word1, String[] word2) {
        // p1 记录 word1 数组遍历指针，p2 记录 word2 数组的遍历指针
        int p1 = 0, p2 = 0;
        for (int i = 0, j = 0; p1 < word1.length && p2 < word2.length; ) {
            if (word1[p1].charAt(i) != word2[p2].charAt(j)) {
                // 当有一个字符不相等时，返回false
                return false;
            }
            // 指针后移
            i++;
            if (i == word1[p1].length()) {
                // 指针移到字符串最后一位, p1指针后移，将 i 重置到 0
                p1++;
                i = 0;
            }
            // 同 i，指针后移
            j++;
            if (j == word2[p2].length()) {
                p2++;
                j = 0;
            }
        }
        // 遍历结束时，需判断 p1、p2 是否所有的字符都扫描到了
        // 如果所有字符都扫描完，则 p1 == word1.length，p2 == word2.length
        return p1 == word1.length && p2 == word2.length;
    }

    /**
     * 字符串拼接后比较
     */
    public boolean solution1(String[] word1, String[] word2) {
        return join(word1).equals(join(word2));
    }

    // 简单的字符串拼接
    public String join(String[] arr) {
        StringBuilder builder = new StringBuilder();
        for (String s : arr) {
            builder.append(s);
        }
        return builder.toString();
    }
}
