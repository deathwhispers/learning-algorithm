package com.guangjian.algorithm;

import java.util.Arrays;

/**
 * @author yanggj
 * @version 1.0.0
 * @date 2022/11/1 14:36
 */
public class Convert {
    public String convert(String s, int numRows) {
        String[] temp = new String[numRows];
        Arrays.fill(temp, "");
        String res = "";
        if (s.isEmpty() || numRows < 1) {
            return res;
        }
        if (numRows == 1) {
            return s;
        }
        for (int i = 0; i < s.length(); i++) {
            int ans = i / (numRows - 1);
            int remain = i % (numRows - 1);
            if (ans % 2 == 0) {                           //结果为偶数或0
                temp[remain] += s.charAt(i);              //按余数正序保存
            }
            if (ans % 2 != 0) {                           //结果为奇数
                temp[numRows - remain - 1] += s.charAt(i);    //按余数倒序保存
            }
        }
        for (int i = 0; i < temp.length; i++) {
            if (temp[i] != null&& !temp[i].equals("null")) {
                res += temp[i];
            }
        }
        return res;
    }


}
