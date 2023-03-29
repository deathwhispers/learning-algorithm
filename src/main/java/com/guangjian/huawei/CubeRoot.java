package com.guangjian.huawei;

import java.util.Scanner;

/**
 * <h1>求立方根</h1>
 * 计算一个浮点数的立方根，不使用库函数。
 * 保留一位小数。
 *
 * @author yanggj
 * @version 1.0.0
 * @date 2023/3/29 11:11
 */
public class CubeRoot {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextDouble()) { // 注意 while 处理多个 case
            double num = in.nextDouble();
            double x = 1.0;
            while (Math.abs(Math.pow(x, 3) - num) > 1e-3) {
                x = x - ((Math.pow(x, 3) - num) / (3 * Math.pow(x, 2)));
            }
            String result = String.format("%.1f", x);
            System.out.println(result);
        }
    }
}
