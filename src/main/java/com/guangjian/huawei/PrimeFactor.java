package com.guangjian.huawei;

import java.util.Scanner;

/**
 * <h1>质数因子</h1>
 * 输入一个正整数，按照从小到大的顺序输出它的所有质因子（重复的也要列举）
 * （如180的质因子为2 2 3 3 5 ）
 *
 * @author yanggj
 * @version 1.0.0
 * @date 2023/3/29 15:26
 */
public class PrimeFactor {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextInt()) { // 注意 while 处理多个 case
            int num = in.nextInt();
            int max = (int) Math.sqrt(num);
            for (int i = 2; i <= max; i++) {
                while (num % i == 0) {
                    num /= i;
                    System.out.printf("%s ", i);
                }
            }
            if (num != 1) {
                System.out.printf("%s", num);
            }
        }
    }
}
