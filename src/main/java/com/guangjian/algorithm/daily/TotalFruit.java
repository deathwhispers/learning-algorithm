package com.guangjian.algorithm.daily;

/**
 * <b>水果成篮</b>
 * <p>
 * 其实就是从任意位置开始，同时使用两个篮子采集，一旦选择后不能修改篮子所装的水果种类，当所有树处理完或遇到第一棵种类不同的树则停止。
 * <p>
 * 滑动窗口模拟题：使用 j 和 i 分别代表滑动窗口的两端，窗口种类不超过 2 种为合法。
 *
 * @author yanggj
 * @version 1.0.0
 * @date 2022/10/17 15:07
 */
public class TotalFruit {

    /**
     * 滑动窗口
     */
    public int totalFruit(int[] fruits) {
        int n = fruits.length, ans = 0;
        int[] cnts = new int[n];
        for (int i = 0, j = 0, tot = 0; i < n; i++) {
            if (++cnts[fruits[i]] == 1) tot++;
            while (tot > 2) {
                if (--cnts[fruits[j++]] == 0) tot--;
            }
            ans = Math.max(ans, i - j + 1);
        }
        return ans;
    }
}
