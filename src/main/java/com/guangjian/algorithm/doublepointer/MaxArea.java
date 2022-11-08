package com.guangjian.algorithm.doublepointer;

/**
 * <h1>盛最多水的容器</h1>
 * 给你 n 个非负整数 a1，a2，…，an，每个数代表坐标中的一个点 (i, ai) 。
 * <p>
 * 在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0) 。
 * <p>
 * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 * <p>
 * 说明：你不能倾斜容器。
 *
 * @author yanggj
 * @version 1.0.0
 * @date 2022/11/1 15:46
 */
public class MaxArea {

    /**
     * 直接暴力破解，遍历所有哦情况，找出最大的结果
     */

    /**
     * <h3>双指针 + 贪心</h3>
     * 先用两个指针 i 和 j 指向左右边界，然后考虑指针应该怎么移动。
     * <p>
     * 由于构成矩形的面积，取决于 i 和 j 之间的距离（记为 w ） 和 i 和 j 下标对应的高度的最小值（记为 h ）。
     * <p>
     * 首先无论是 i 指针往右移动还是 j 指针往左移动都会导致 w 变小，所以想要能够枚举到更大的面积，我们应该让 h 在指针移动后变大。
     * <p>
     * 不妨假设当前情况是 height[i] < heigth[j] （此时矩形的高度为 height[i] ），然后分情
     * 况讨论
     * <ul>
     * <li>让 i 和 j 两者高度小的指针移动，即 i 往右移动：</li>
     *  <ol>
     *  <li>移动后，i 指针对应的高度变小，即
     * height[i] > height[i + 1] ： w 和 h 都变小了，面积一定变小
     *  <li>移动后，i 指针对应的高度不变，即
     * height[i] = height[i + 1] ： w 变小， h 不变，面积一定变小
     *  <li>移动后，i 指针对应的高度变大，即
     * height[i] < height[i + 1] ： w 变小， h 变大，面积可能会变大
     *  </ol>
     * <li>让 i 和 j 两者高度大的指针移动，即 j 往左移动：</li>
     *  <ol>
     *  <li>移动后，j 指针对应的高度变小，即
     * height[j] > height[j - 1] ： w 变小， h 可能不变或者变小（当
     * height[j - 1] >= height[i] 时， h 不变；当
     * height[j - 1] < height[i] 时， h 变小），面积一定变小
     *  <li>移动后，j 指针对应的高度不变，即
     * height[j] = height[j - 1] ： w 变小， h 不变，面积一定变小
     *  <li>移动后，j 指针对应的高度变大，即
     * height[j] < height[j - 1] ： w 变小， h 不变，面积一定变小
     *  </ol>
     * </ul>
     */
    public int maxArea(int[] height) {
        int max = 0;
        int left = 0, right = height.length - 1;
        while (left < right) {
            max = Math.max((right - left) * Math.min(height[left], height[right]), max);
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return max;
    }
}
