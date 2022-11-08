package com.guangjian.algorithm.doublepointer;

/**
 * <h1>到达终点数字</h1>
 *
 * @author yanggj
 * @version 1.0.0
 * @date 2022/11/4 9:55
 */
public class ReachNumber {

    /**
     * <h3>提示一：数轴上的任意点都以起点（0 点）对称，只需要考虑对称点的任意一边</h3>
     * 由于题目没有限制我们「不能到达哪些点」以及「出发的起始方向」，因此以起点为中心的左右两边对称。
     * <p>
     * <b>提示二：先往靠近 target 的方向移动，到达或越过 target 的时候则停止</b>
     * <p>
     * 只考虑 target 为正的情况，我们假定起始先往靠近 target 的方向移动（即所有步数均为正值），根据是「到达」还是「越过」target 位置分情况讨论：
     * <p>
     * 若能直接到达 target，此时消耗的必然是最小步数，可直接返回；
     * <p>
     * 若越过了 target，假设此时消耗的步数为 k，所走的距离为 dist > target，我们可以考虑是否需要增加额外步数来到达 target。
     * <p>
     * <b>提示三：越过 target 时，如何不引入额外步数</b>
     * <p>
     * 若不引入额外步数，意味着我们需要将此前某些移动的方向进行翻转，使得调整后的 dist = target。
     * <p>
     * 我们假设需要调整的步数总和为 tot，则有 dist−2×tot=target，变形可得 tot = (dist - target) / 2。
     * 若想满足上述性质，需要确保能找到这样的 tot，即 tot 合法，
     * <p>
     * 不难推导出当 dist 和 target 差值为「偶数」时（两者奇偶性相同），我们可以找到这样的 tot，从而实现不引入额外步数来到达 target 位置。
     * <p>
     * <b>提示四：越过 target 时，如何尽量减少引入额外步数</b>
     * <p>
     * 当 dist 和 target 差值不为「偶数」时，我们只能通过引入额外步数（继续往右走）来使得，两者差值为偶数。
     * <p>
     * 可以证明，最多引入步数不超过 4 步，可使用得两者奇偶性相同
     */
    public int reachNumber(int target) {
        // target 正负不影响步数
        if (target < 0) target = -target;
        int k = (int) Math.sqrt(2 * target), dist = k * (k + 1) / 2;
        // (dist - target) /2 为奇数时，需添加新的步数
        while (dist < target || (dist - target) % 2 == 1) {
            k++;
            dist = k * (k + 1) / 2;
        }
        return k;
    }
}
