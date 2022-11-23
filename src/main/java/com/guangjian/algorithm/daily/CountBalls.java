package com.guangjian.algorithm.daily;

import java.util.HashMap;
import java.util.Map;

/**
 * <h1>盒子中小球的最大数量</h1>
 * 你在一家生产小球的玩具厂工作，有 n 个小球，编号从 lowLimit 开始，到 highLimit 结束（包括 lowLimit 和 highLimit ，
 * 即 n == highLimit - lowLimit + 1）。另有无限数量的盒子，编号从 1 到 infinity 。
 * <p>
 * 你的工作是将每个小球放入盒子中，其中盒子的编号应当等于小球编号上每位数字的和。
 * 例如，编号 321 的小球应当放入编号 3 + 2 + 1 = 6 的盒子，而编号 10 的小球应当放入编号 1 + 0 = 1 的盒子。
 * <p>
 * 给你两个整数 lowLimit 和 highLimit ，返回放有最多小球的盒子中的小球数量。
 * 如果有多个盒子都满足放有最多小球，只需返回其中任一盒子的小球数量。
 *
 * @author yanggj
 * @version 1.0.0
 * @date 2022/11/23 9:14
 */
public class CountBalls {

    /**
     * <h2>哈希表</h2>
     * 遍历所有的小球，对于编号为 ii 的小球，计算它应该放入的盒子编号 box，使用哈希表 count 记录每个盒子中的小球数量
     * <p>
     * 返回遍历结束后 count 中小球数量最大的盒子对应的小球数量即可。
     */
    public int countBalls(int lowLimit, int highLimit) {
        // 哈希表记录每个盒子中的小球个数
        Map<Integer, Integer> count = new HashMap<>();
        int res = 0;
        for (int i = lowLimit; i <= highLimit; i++) {
            int box = 0, x = i;
            // 计算每个小球各位数之和,即盒子的编号
            while (x != 0) {
                int temp = x % 10;
                box += temp;
                x /= 10;
            }
            // 球放入盒子中
            count.put(box, count.getOrDefault(box, 0) + 1);
            // 取最大值
            res = Math.max(res, count.get(box));
        }
        return res;
    }
}