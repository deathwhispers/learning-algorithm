package com.guangjian.huawei;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * <h1>进制转换</h1>
 * 写出一个程序，接受一个十六进制的数，输出该数值的十进制表示。
 *
 * @author yanggj
 * @version 1.0.0
 * @date 2023/3/29 15:05
 */
public class BaseConversion {

    public static final int BASE = 16;
    public static Map<Character, Integer> map = new HashMap<>();

    static {
        map.put('0', 0);
        map.put('1', 1);
        map.put('2', 2);
        map.put('3', 3);
        map.put('4', 4);
        map.put('5', 5);
        map.put('6', 6);
        map.put('7', 7);
        map.put('8', 8);
        map.put('9', 9);
        map.put('A', 10);
        map.put('B', 11);
        map.put('C', 12);
        map.put('D', 13);
        map.put('E', 14);
        map.put('F', 15);
        map.put('a', 10);
        map.put('b', 11);
        map.put('c', 12);
        map.put('d', 13);
        map.put('e', 14);
        map.put('f', 15);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextLine()) { // 注意 while 处理多个 case
            String str = in.nextLine();
            int res = 0;
            String str2 = str.substring(2);
            for (int i = 0; i < str2.length(); i++) {
                res = res * BASE + map.get(str2.charAt(i));
            }
            System.out.println(res);
        }
    }
}