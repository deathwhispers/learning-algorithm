package com.guangjian.huawei;

import java.util.Scanner;

/**
 * <h1>字符串逆序</h1>
 * 将一个字符串str的内容颠倒过来，并输出。
 * 数据范围：1≤len(str)≤10000
 * ### 输入描述：
 * 输入一个字符串，可以有空格
 * ### 输出描述：
 * 输出逆序的字符串
 *
 * @author yanggj
 * @version 1.0.0
 * @date 2023/3/29 11:13
 */
public class ReverseString {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextLine()) { // 注意 while 处理多个 case
            String str = in.nextLine();
            StringBuilder sb = new StringBuilder();
            int n = str.length();
            for (int i = n - 1; i >= 0; i--) {
                sb.append(str.charAt(i));
            }
            System.out.println(sb);
        }
    }

    public void swap(char a,char b){
        char temp = a;
        a = b;
        b = temp;
    }

}
