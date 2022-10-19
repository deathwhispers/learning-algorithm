package com.guangjian.algorithm.daily;

import java.util.Arrays;

/**
 * <b>无法吃午餐的学生数量</b>
 *
 * @author yanggj
 * @version 1.0.0
 * @date 2022/10/19 9:33
 */
public class CountStudents {

    /**
     * 假设喜欢吃圆形三明治的学生数量为 s0，喜欢吃方形三明治的学生数量为 s1。
     * <p>
     * 根据题意，我们可以知道栈顶的三明治能否被拿走取决于队列剩余的学生中是否有喜欢它的，
     * 因此学生在队列的相对位置不影响整个过程，我们只需要记录队列剩余的学生中 s0 和 s1 的值。
     * <li>我们对整个过程进行模拟，如果栈顶的元素为 0 并且 s0 > 0，我们将 s0 减 1；</li>
     * <li>如果栈顶的元素为 1 并且 s1 > 0，我们将 s1 减 1；</li>
     * <li>否则终止过程，并返回 s0 + s1</li>
     */
    public int countStudents(int[] students, int[] sandwiches) {
        int s1 = Arrays.stream(students).sum();
        int s0 = students.length - s1;
        for (int sandwich : sandwiches) {
            if (sandwich == 0 && s0 > 0) {
                s0--;
            } else if (sandwich == 1 && s1 > 0) {
                s1--;
            } else {
                break;
            }
        }
        return s0 + s1;
    }
}
