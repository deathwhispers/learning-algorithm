package com.guangjian.algorithm.doublepointer;

/**
 * <h1>平方数之和</h1>
 * 给定一个非负整数 c ，你要判断是否存在两个整数 a 和 b，使得 a^2 + b^2 = c 。
 *
 * @author yanggj
 * @version 1.0.0
 * @date 2022/11/8 11:40
 */
public class JudgeSquareSum {

    /**
     * <h2>枚举</h2>
     * 遍历 a 到 max，判断 a^2 + b^2 = c 是否成立
     */
    public boolean judgeSquareSum1(int c) {
        // 遍历的右边界
        int max = (int) Math.sqrt(c);
        // 注意遍历从 0 开始，不然会漏掉 c 为 0 的特殊情况，max处可以取等
        for (int a = 0; a <= max; a++) {
            // 求 b 的值
            int b = (int) Math.sqrt(c - a * a);
            // 判断 a b 是否满足
            if (a * a + b * b == c) {
                return true;
            }
        }
        return false;
    }

    /**
     * <h2>双指针</h2>
     * 由于 a,b 的取值都是 [0,max], 因此可以使用双指针指向区间的左、右端点
     * <ul>
     * <li>如果 a^2 + b^2 = c,返回 true</li>
     * <li>如果 a^2 + b^2 < c,a++</li>
     * <li>如果 a^2 + b^2 = c,b--</li>
     * </ul>
     */
    public boolean judgeSquareSum2(int c) {
        long a = 0, b = (long) Math.sqrt(c);
        while (a <= b) {
            long temp = a * a + b * b;
            if (temp == c) return true;
            if (temp < c) a++;
            if (temp > c) b--;
        }
        return false;
    }

    /**
     * <h2>费马平方和</h2>
     * <b>费马平方和 : 奇质数能表示为两个平方数之和的充分必要条件是该质数被 4 除余 1 。</b>
     * <p>
     * <b>翻译过来就是：当且仅当一个自然数的质因数分解中，满足 4k+3 形式的质数次方数均为偶数时，该自然数才能被表示为两个平方数之和。</b>
     */
    public boolean judgeSquareSum3(int c) {
        for (int i = 2; i * i <= c; i++) {
            int cnt = 0;
            while (c % i == 0) {
                cnt++;
                c /= i;
            }
            if (i % 4 == 3 && cnt % 2 != 0) return false;
        }
        return c % 4 != 3;
    }
}
