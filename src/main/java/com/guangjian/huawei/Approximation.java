package com.guangjian.huawei;

import java.util.Scanner;

/**
 * <h1>取近似值</h1>
 * 写出一个程序，接受一个正浮点数值，输出该数值的近似整数值。
 * 如果小数点后数值大于等于 0.5 ,向上取整；小于 0.5 ，则向下取整。
 *
 * @author yanggj
 * @version 1.0.0
 * @date 2023/3/29 15:33
 */
public class Approximation {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        double number = in.nextDouble();
        System.out.println((int)(number + 0.5));
    }
}
