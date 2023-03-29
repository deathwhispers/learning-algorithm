package com.guangjian.huawei;

/**
 * 最大公约数
 *
 * @author yanggj
 * @version 1.0.0
 * @date 2023/3/29 14:15
 */
public class GCD {

    public static void main(String[] args) {
    }

    public int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }
}
