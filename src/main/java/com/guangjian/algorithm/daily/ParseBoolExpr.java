package com.guangjian.algorithm.daily;

import java.util.ArrayDeque;

/**
 * <b>解析布尔表达式</b>
 * <p>
 * 给你一个以字符串形式表述的布尔表达式（boolean） <code>expression</code>，返回该式的运算结果。
 * <p>
 * 有效的表达式需遵循以下约定：
 * <ul>
 * <li>"t"，运算结果为 True</li>
 * <li>"f"，运算结果为 False</li>
 * <li>"!(expr)"，运算过程为对内部表达式 expr 进行逻辑 <b>非的运算</b>（NOT）</li>
 * <li>"&(expr1,expr2,...)"，运算过程为对 2 个或以上内部表达式 expr1, expr2, ... 进行逻辑 <b>与的运算</b>（AND）</li>
 * <li>"|(expr1,expr2,...)"，运算过程为对 2 个或以上内部表达式 expr1, expr2, ... 进行逻辑 <b>或的运算</b>（OR）</li>
 * </ul>
 *
 * @author yanggj
 * @version 1.0.0
 * @date 2022/11/7 14:01
 */
public class ParseBoolExpr {
    /**
     * 单栈
     */
    public boolean parseBoolExpr(String expression) {
        ArrayDeque<Character> stack = new ArrayDeque<>();
        int n = expression.length();
        for (int i = 0; i < n; i++) {
            char c = expression.charAt(i);
            if (c == ',') {
                continue;
            } else if (c != ')') {
                // 将元素依次入栈，逗号直接跳过
                stack.push(c);
            } else {
                int t = 0, f = 0;
                // 依次取出栈顶元素，直到栈顶元素为 ( 后
                while (stack.peek() != '(') {
                    char pop = stack.pop();
                    // 对取出的元素做bool运算
                    if (pop == 't') {
                        t++;
                    } else {
                        f++;
                    }
                }
                // 取出 (
                stack.pop();
                // 取出操作符
                char op = stack.pop();
                if (op == '!') {
                    stack.push(f == 1 ? 't' : 'f');
                } else if (op == '&') {
                    stack.push(f == 0 ? 't' : 'f');
                } else if (op == '|') {
                    stack.push(t > 0 ? 't' : 'f');
                }
            }
        }
        return stack.pop() == 't';
    }

    // 双栈
}