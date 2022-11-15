package com.guangjian.algorithm.daily;

import java.util.Arrays;

/**
 * <h1>卡车上的最大单元数</h1>
 * 请你将一些箱子装在 一辆卡车 上。给你一个二维数组 boxTypes ，其中 boxTypes[i] = [numberOfBoxesi, numberOfUnitsPerBoxi] ：
 * <ul>
 * <li>numberOfBoxesi 是类型 i 的箱子的数量。</li>
 * <li>numberOfUnitsPerBoxi 是类型 i 每个箱子可以装载的单元数量。</li>
 * </ul>
 * 整数 truckSize 表示卡车上可以装载 <b>箱子</b> 的 最大数量 。只要箱子数量不超过 truckSize ，你就可以选择任意箱子装到卡车上。
 * <p>
 * 返回卡车可以装载 <b>单元</b> 的 <b>最大</b> 总数。
 *
 * @author yanggj
 * @version 1.0.0
 * @date 2022/11/15 13:57
 */
public class MaximumUnits {

    /**
     * <h2>贪心</h2>
     * 由于每个箱子成本相同（均占用一个箱子位置），因此在 truckSize 给定的情况下，我们应当尽可能选择装载单元数量大的箱子。
     * <p>
     * 这引导我们可以将 boxTypes 根据「装载单元数量」排倒序，然后从前往后 pick 箱子，直到没有空闲位置。
     */
    public int maximumUnits(int[][] boxTypes, int truckSize) {

        int n = boxTypes.length, ans = 0;
        // 将 boxTypes 根据单元数量倒排序
        Arrays.sort(boxTypes, (a, b) -> b[1] - a[1]);
        int cnt = 0;
        for (int i = 0; i < n && cnt < truckSize; i++) {
            int num = boxTypes[i][0];
            int unitNum = boxTypes[i][1];
            // 取几个箱子
            int cur = Math.min(num, truckSize - cnt);
            cnt += cur;
            ans += cur * unitNum;
        }
        return ans;
    }
}
