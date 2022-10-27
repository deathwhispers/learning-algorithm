package com.guangjian.algorithm.daily;

/**
 * <b>数组元素积的符号</b>
 * <p>
 * 已知函数 signFunc(x) 将会根据 x 的正负返回特定值：
 * <li>如果 x 是正数，返回 1 。</li>
 * <li>如果 x 是负数，返回 -1 。</li>
 * <li>如果 x 是等于 0 ，返回 0 。</li>
 * 给你一个整数数组 nums 。令 product 为数组 nums 中所有元素值的乘积。
 * <p>
 * 返回 signFunc(product) 。
 *
 * @author yanggj
 * @version 1.0.0
 * @date 2022/10/27 9:17
 */
public class ArraySign {
    public int arraySign(int[] nums) {
        int sign = 1;
        for (int num : nums) {
            if (num == 0) return 0;
            if (num < 0) sign = -sign;
        }
        return sign;
    }
}