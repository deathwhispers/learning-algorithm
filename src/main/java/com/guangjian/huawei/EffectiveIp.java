package com.guangjian.huawei;

import java.util.Scanner;

/**
 * @author yanggj
 * @version 1.0.0
 * @date 2023/3/29 15:38
 */
public class EffectiveIp {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int a = 0;
        int b = 0;
        int c = 0;
        int d = 0;
        int e = 0;
        int error = 0;// 错误的
        int pri = 0;// 私有的

        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextLine()) {
            String str = in.nextLine();
            String[] split = str.split("~");
            String ip = split[0];
            String mask = split[1];
            if (ignore(ip)) {
                continue;
            }
            if (!isValidIp(ip) || !isValidMask(mask)) {
                error++;
                continue;
            }
            if (isA(ip)) a++;
            if (isB(ip)) b++;
            if (isC(ip)) c++;
            if (isD(ip)) d++;
            if (isE(ip)) e++;
            if (isPri(ip)) pri++;
        }
        System.out.printf("%s %s %s %s %s %s %s", a, b, c, d, e, error, pri);
    }

    public static boolean ignore(String ip) {
        String[] split = ip.split("\\.");
        int a = Integer.parseInt(split[0]);
        return a == 0 || a == 127;
    }

    public static boolean isValidMask(String mask) {
        String[] maskArr = mask.split("\\.");
        if (maskArr.length != 4) {
            return false;
        } else {
            String maskStr = "";
            for (String s : maskArr) {
                String s1 = Integer.toBinaryString(Integer.parseInt(s));
                while (s1.length() < 8) {
                    s1 = "0" + s1;
                }
                maskStr += s1;
            }
            return maskStr.lastIndexOf("1") < maskStr.indexOf("0");
        }
    }

    public static boolean isValidIp(String ip) {
        String[] ipArr = ip.split("\\.");
        if (ipArr.length != 4) {
            return false;
        } else {
            for (String s : ipArr) {
                int i = Integer.parseInt(s);
                if (i < 0 || i > 255) {
                    return false;
                }
            }
        }
        return true;
    }


    public static boolean isA(String ip) {
        String[] split = ip.split("\\.");
        int a = Integer.parseInt(split[0]);
        return a <= 126 && a >= 0;
    }

    public static boolean isB(String ip) {
        String[] split = ip.split("\\.");
        int a = Integer.parseInt(split[0]);
        return a <= 191 && a >= 128;
    }

    public static boolean isC(String ip) {
        String[] split = ip.split("\\.");
        int a = Integer.parseInt(split[0]);
        return a <= 223 && a >= 192;
    }

    public static boolean isD(String ip) {
        String[] split = ip.split("\\.");
        int a = Integer.parseInt(split[0]);
        return a <= 239 && a >= 224;
    }

    public static boolean isE(String ip) {
        String[] split = ip.split("\\.");
        int a = Integer.parseInt(split[0]);
        return a <= 255 && a >= 240;
    }

    public static boolean isPri(String ip) {
        String[] split = ip.split("\\.");
        int a = Integer.parseInt(split[0]);
        int b = Integer.parseInt(split[1]);
        return a == 10 || (a == 172 && (b >= 16 && b <= 31)) || (a == 192 && b == 168);
    }
}
