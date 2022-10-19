package com.guangjian.algorithm.daily;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * <b>括号中的分数</b>
 * <p>
 * 给定一个平衡括号字符串 S，按下述规则计算该字符串的分数：
 * <li>() 得 1 分。</li>
 * <li>AB 得 A + B 分，其中 A 和 B 是平衡括号字符串。</li>
 * <li>(A) 得 2 * A 分，其中 A 是平衡括号字符串。</li>
 *
 * @author yanggj
 * @version 1.0.0
 * @date 2022/10/9 15:39
 */
public class ScoreOfParentheses {

    /**
     * <b>栈</b>
     * 初始化将答案 0 放入栈中，从前往后处理整个 s，当遇到 ( 则存入一个占位数值 0，遇到 ) 取出栈顶元素 cur，根据栈顶元素数值值分情况讨论：
     * <li>栈顶元素 cur = 0，即当前的 ) 的前一元素即是 ( ，根据 () 得一分的规则可知，我们本次操作得到的分值为 1；</li>
     * <li>栈顶元素 cur != 0，即当前 ) 与其匹配的 ( 中间相隔了其他字符，根据 (A) 的得分规则，此时可知得分为 cur×2；</li>
     * 将两者结合可统一为 max(cur×2,1)。
     * <p>
     * 由于我们每次遇到 ) 时，都将最近一次操作计算出来。而再前面无论是 ) 还是 ( 我们都可以归结到 X() 的相邻项累加规则，将其新得分累加到栈顶元素上，
     * 其中 ( 仍采用累加规则，则利用我们将 ( 定义为 00 的设定。
     */
    public int scoreOfParentheses(String s) {
        Deque<Integer> d = new ArrayDeque<>();
        d.addLast(0);
        for (char c : s.toCharArray()) {
            if (c == '(') {
                d.addLast(0);
            } else {
                int cur = d.pollLast();
                d.addLast(d.pollLast() + Math.max(cur * 2, 1));
            }
        }
        return d.peekLast();
    }

}
