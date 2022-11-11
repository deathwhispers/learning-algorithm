package com.guangjian.algorithm.doublepointer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * <h1>从相邻元素对还原数组</h1>
 * 存在一个由 n 个不同元素组成的整数数组 nums ，但你已经记不清具体内容。好在你还记得 nums 中的每一对相邻元素。
 * <p>
 * 给你一个二维整数数组 adjacentPairs ，大小为 n - 1 ，其中每个 adjacentPairs[i] = [ui, vi] 表示元素 ui 和 vi 在 nums 中相邻。
 * <p>
 * 题目数据保证所有由元素 nums[i] 和 nums[i+1] 组成的相邻元素对都存在于 adjacentPairs 中，存在形式可能是 [nums[i], nums[i+1]] ，
 * 也可能是 [nums[i+1], nums[i]] 。这些相邻元素对可以 <b>按任意顺序</b> 出现。
 * <p>
 * 返回 <b>原始数组</b> nums 。如果存在多种解答，返回 <b>其中任意一个</b> 即可。
 *
 * @author yanggj
 * @version 1.0.0
 * @date 2022/11/9 17:53
 */
public class RestoreArray {

    /**
     * <h2>单向构造(hash表计数)</h2>
     * 由于所有的相邻关系都会出现在 nums 中,假设其中一个合法数组为 ans ,长度为 n
     * <p>
     * 那么, 显然 ans[0] 和 ans[n-1] 在 nums 中只会出现一次, 而其他位置则会出现两次
     * <p>
     * 因此, 可以通过 hash 表记录每个数值出现的次数, 找到仅出现一次的数值作为 ans 首位,然后根据相邻关系进行 <b>单向构造</b>
     * <p>
     * 另外, 再创建一个 hash 表, 记录某个数的相邻数有哪些, 即 <b>相邻关系</b>
     */
    public int[] restoreArray(int[][] adjacentPairs) {
        int len = adjacentPairs.length;
        int n = len + 1;
        // 记录每个数值出现的次数
        HashMap<Integer, Integer> counts = new HashMap<>();
        // 记录每个数值的相邻数, key 为出现的数值, value 为相邻的两个元素(首位仅一个)
        HashMap<Integer, List<Integer>> relation = new HashMap<>();

        for (int[] pair : adjacentPairs) {
            int a = pair[0];
            int b = pair[1];
            // 统计出现次数
            counts.put(a, counts.getOrDefault(a, 0) + 1);
            counts.put(b, counts.getOrDefault(b, 0) + 1);

            // 保存每个数值的相邻元素关系
            List<Integer> aList = relation.getOrDefault(a, new ArrayList<>());
            // b 作为 a 的相邻元素
            aList.add(b);
            relation.put(a, aList);

            List<Integer> bList = relation.getOrDefault(b, new ArrayList<>());
            // a 作为 b 的相邻元素
            bList.add(a);
            relation.put(b, bList);
        }

        // 找到起始点
        Integer start = counts.keySet().stream().filter(item -> counts.get(item) == 1).findFirst().orElse(-1);

        int[] ans = new int[n];
        ans[0] = start;
        ans[1] = relation.get(start).get(0);
        for (int i = 2; i < n; i++) {
            List<Integer> list = relation.get(ans[i - 1]);
            for (Integer num : list) {
                // 从左向右依次构造
                if (num != ans[i - 2]) {
                    ans[i] = num;
                }
            }
        }
        return ans;
    }

    /**
     * <h2>双向构造（双指针）</h2>
     * 可以构造一个长度为 ans 两倍的数组,从中间开始向两边构造(不会数组越界)
     * <p>
     * 以随意一个元素开始, 通过双指针分别往 两边扩展, 当 left 和 right 中间有 n 各数值时, 构造完成
     * <p>
     * 然后将 left 和 right 中间的结果返回即可
     */
    public int[] restoreArray2(int[][] adjacentPairs) {
        int len = adjacentPairs.length;
        int n = len + 1;
        // 记录每个数值的相邻数, key 为出现的数值, value 为相邻的两个元素(首位仅一个)
        HashMap<Integer, List<Integer>> relation = new HashMap<>();
        for (int[] pair : adjacentPairs) {
            int a = pair[0];
            int b = pair[1];

            // 无论是单向构造,还是双向构造, 关键在于记录所有元素的 [相邻关系]
            // 保存每个数值的相邻元素关系
            List<Integer> aList = relation.getOrDefault(a, new ArrayList<>());
            // b 作为 a 的相邻元素
            aList.add(b);
            relation.put(a, aList);

            List<Integer> bList = relation.getOrDefault(b, new ArrayList<>());
            // a 作为 b 的相邻元素
            bList.add(a);
            relation.put(b, bList);
        }

        // 定义左右指针,向两边构造数组
        int left = N / 2, right = left + 1;
        int std = adjacentPairs[0][0];
        List<Integer> list = relation.get(std);
        q[left--] = std;
        q[right++] = list.get(0);
        if (list.size() > 1) q[left--] = list.get(1);
        while ((right - 1) - (left + 1) + 1 < n) {
            List<Integer> alist = relation.get(q[left + 1]);
            int j = left;
            for (int i : alist) {
                if (i != q[left + 2]) q[j--] = i;
            }
            left = j;
            List<Integer> blist = relation.get(q[right - 1]);
            j = right;
            for (int i : blist) {
                if (i != q[right - 2]) q[j++] = i;
            }
            right = j;
        }
        int[] ans = new int[n];
        for (int i = left + 1, idx = 0; idx < n; i++, idx++) {
            ans[idx] = q[i];
        }
        return ans;
    }

    static int N = (int) 1e6 + 10;
    static int[] q = new int[N];
}
