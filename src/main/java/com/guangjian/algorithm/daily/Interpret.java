package com.guangjian.algorithm.daily;

/**
 * <b>设计 Goal 解析器</b>
 * 请你设计一个可以解释字符串 command 的 Goal 解析器 。command 由 "G"、"()" 和/或 "(al)" 按某种顺序组成。Goal 解析器会将 "G" 解释为字符串 "G"、"()" 解释为字符串 "o" ，"(al)" 解释为字符串 "al" 。然后，按原顺序将经解释得到的字符串连接成一个字符串。
 * <p>
 * 给你字符串 command ，返回 Goal 解析器 对 command 的解释结果。
 *
 * @author yanggj
 * @version 1.0.0
 * @date 2022/11/7 13:59
 */
public class Interpret {
    public String interpret(String command) {
        StringBuilder res = new StringBuilder();
        // 遍历command
        for (int i = 0; i < command.length(); i++) {
            if (command.charAt(i) == 'G') {
                // 如果是G，则直接添加到返回结果中
                res.append("G");
            } else if (command.charAt(i) == '(') {
                if (command.charAt(i + 1) == ')') {
                    // 如果是(),则解析为o
                    res.append("o");
                } else {
                    // 否则为(al),解析为al,添加到结果中
                    res.append("al");
                }
            }
        }
        return res.toString();
    }
}
