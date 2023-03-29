package com.guangjian.huawei;

import java.util.Scanner;

/**
 * <h1>字符串分隔</h1>
 * •输入一个字符串，请按长度为8拆分每个输入字符串并进行输出；
 * <p>
 * •长度不是8整数倍的字符串请在后面补数字0，空字符串不处理。
 *
 * @author yanggj
 * @version 1.0.0
 * @date 2023/3/29 14:56
 */
public class StringSplit {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextLine()) { // 注意 while 处理多个 case
            String str = in.nextLine();
            if (str.isEmpty()) {
                return;
            }
            if (str.length() < 8) {
                System.out.println(fill(str));
            } else {
                while (str.length() > 8) {
                    System.out.println(str.substring(0, 8));
                    str = str.substring(8);
                }
                System.out.println(fill(str));
            }
        }
    }

    public static String fill(String str) {
        while (str.length() < 8) {
            str = str + "0";
        }
        return str;
    }

}
