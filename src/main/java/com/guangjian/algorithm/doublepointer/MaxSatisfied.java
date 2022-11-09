package com.guangjian.algorithm.doublepointer;

/**
 * <h1>爱生气的书店老板</h1>
 * 有一个书店老板，他的书店开了 n 分钟。每分钟都有一些顾客进入这家商店。给定一个长度为 n 的整数数组 customers ，
 * 其中 customers[i] 是在第 i 分钟开始时进入商店的顾客数量，所有这些顾客在第 i 分钟结束后离开。
 * <p>
 * 在某些时候，书店老板会生气。 如果书店老板在第 i 分钟生气，那么 grumpy[i] = 1，否则 grumpy[i] = 0。
 * <p>
 * 当书店老板生气时，那一分钟的顾客就会不满意，若老板不生气则顾客是满意的。
 * <p>
 * 书店老板知道一个秘密技巧，能抑制自己的情绪，可以让自己连续 minutes 分钟不生气，但却只能使用一次。
 * <p>
 * 请你返回 这一天营业下来，最多有多少客户能够感到满意 。
 *
 * @author yanggj
 * @version 1.0.0
 * @date 2022/11/9 15:24
 */
public class MaxSatisfied {

    /**
     * <h2>滑动窗口</h2>
     * 连续的长度为 minutes 的区间中, grumpy为 1 的顾客量最大值
     * <p>
     * 由于「技巧」只会将情绪将「生气」变为「不生气」，不生气仍然是不生气。
     * <ol>
     * <li>我们可以先将原本就满意的客户加入答案，同时将对应的 customers[i] 变为 0。</li>
     * <li>之后的问题转化为：在 customers 中找到连续一段长度为 x 的子数组，使得其总和最大。这部分就是我们应用技巧所得到的客户。</li>
     * </ol>
     */
    public int maxSatisfied(int[] customers, int[] grumpy, int minutes) {
        int ans = 0;
        int left = 0, right = 0;
        int n = customers.length;
        // 计算通过 技巧 能够得到的最大满意度
        int max = 0;
        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = i; j < i + minutes && j < n; j++) {
                if (grumpy[j] == 1) {
                    sum += customers[j];
                }
            }
            max = Math.max(max, sum);
        }
        // 计算原本就能得到的满意度
        for (int i = 0; i < n; i++) {
            if (grumpy[i] == 0) {
                ans += customers[i];
            }
        }
        // 两者相加,即为最终的满意度
        ans = ans + max;
        return ans;
    }

}
