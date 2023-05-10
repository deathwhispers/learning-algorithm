package com.guangjian.algorithm.recursion.bfs;

import java.util.*;

/**
 * 给你一个整数数组 nums ，其中可能包含重复元素，请你返回该数组所有可能的子集（幂集）。
 * <p>
 * 解集 不能 包含重复的子集。返回的解集中，子集可以按 任意顺序 排列。
 *
 * @author yanggj
 * @version 1.0.0
 * @date 2023/5/9 9:45
 */
public class SubsetsWithDup {

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        // 先对数组排序，确保所有的结果具有单调性，从而可以通过set去重
        Arrays.sort(nums);
        // 保存结果
        Set<List<Integer>> ans = new HashSet<>();
        List<Integer> cur = new ArrayList<>();
        dfs(nums, 0, cur, ans);
        return new ArrayList<>(ans);
    }

    /**
     * @param nums 原始输入数组
     * @param u    当前决策到原输入数组中的位置（从 0 开始）
     * @param cur  当前的方案（）
     * @param ans  最终结果集
     */
    private void dfs(int[] nums, int u, List<Integer> cur, Set<List<Integer>> ans) {
        if (nums.length == u) {
            ans.add(new ArrayList<>(cur));
            return;
        }
        // 选择当前位置的元素，往下决策
        cur.add(nums[u]);
        dfs(nums, u + 1, cur, ans);

        // 不选当前位置的元素（回溯），往下决策
        cur.remove(cur.size() - 1);
        dfs(nums, u + 1, cur, ans);
    }

}
