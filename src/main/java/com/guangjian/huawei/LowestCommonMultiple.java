package com.guangjian.huawei;

import java.util.Scanner;

/**
 * <h1>最小公倍数</h1>
 *
 * @author yanggj
 * @version 1.0.0
 * @date 2023/3/29 11:22
 */
public class LowestCommonMultiple {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        int a = in.nextInt();
        int b = in.nextInt();
        System.out.println(a + b);
        System.out.println(a * b / gcd(a, b));
    }

    public static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }
}
