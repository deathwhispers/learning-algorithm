package com.guangjian.algorithm.daily;

/**
 * <b>最大重复字串</b>
 *
 * @author yanggj
 * @version 1.0.0
 * @date 2022/11/3 10:25
 */
public class MaxRepeating {

    public int maxRepeating(String sequence, String word) {
        int res = 0;
        String temp = word;
        while (sequence.contains(word)) {
            word += temp;
            res++;
        }
        return res;
    }
}
