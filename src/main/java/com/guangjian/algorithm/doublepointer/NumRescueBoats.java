package com.guangjian.algorithm.doublepointer;

import java.util.Arrays;

/**
 * <h1>救生艇</h1>
 * 给定数组 people 。people[i]表示第 i 个人的体重 ，船的数量不限，每艘船可以承载的最大重量为 limit。
 * <p>
 * 每艘船最多可同时载两人，但条件是这些人的重量之和最多为 limit。
 * <p>
 * 返回 承载所有人所需的最小船数 。
 *
 * @author yanggj
 * @version 1.0.0
 * @date 2022/11/8 14:41
 */
public class NumRescueBoats {

    /**
     *
     */
    public int numRescueBoats(int[] people, int limit) {
        int ans = 0;
        // 对数组进行排序
        Arrays.sort(people);
        int n = people.length;
        // 取左、右指针指向数组两端，尽可能让一艘船装两人
        int left = 0, right = n - 1;
        while (left < right) {
            if (people[left] + people[right] <= limit) {
                left++;
            }
            right--;
            ans++;
        }
        // 循环结束后指向最后一个元素，ans+1
        if (left == right) ans++;
        return ans;
    }
}
