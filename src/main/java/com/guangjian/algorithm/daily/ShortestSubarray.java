package com.guangjian.algorithm.daily;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author yanggj
 * @version 1.0.0
 * @date 2022/10/26 18:02
 */
public class ShortestSubarray {

    static int N = 200010;
    static int[] tr = new int[N], sum = new int[N];
    int n, m, ans;
    int lowbit(int x) {
        return x & -x;
    }
    void update(int val, int loc) {
        for (int i = val; i < m; i += lowbit(i)) tr[i] = Math.max(tr[i], loc);
    }
    int query(int x) {
        int ans = -1;
        for (int i = x; i > 0; i -= lowbit(i)) ans = Math.max(ans, tr[i]);
        return ans;
    }
    int getIdx(List<Long> list, long x) {
        int l = 0, r = list.size() - 1;
        while (l < r) {
            int mid = l + r >> 1;
            if (list.get(mid) >= x) r = mid;
            else l = mid + 1;
        }
        return r + 1;
    }
    public int shortestSubarray(int[] nums, int k) {
        n = nums.length; m = 2 * n + 10; ans = n + 10;
        Arrays.fill(tr, -1);
        long[] temp = new long[m];
        List<Long> list = new ArrayList<>();
        list.add(0L);
        for (int i = 1; i <= 2 * n + 1; i++) {
            if (i <= n) temp[i] = temp[i - 1] + nums[i - 1];
            else temp[i] = temp[i - (n + 1)] + k;
            list.add(temp[i]);
        }
        Collections.sort(list);
        for (int i = 0; i <= 2 * n + 1; i++) sum[i] = getIdx(list, temp[i]);
        update(sum[n + 1], 0);
        for (int i = 1; i <= n; i++) {
            int j = query(sum[i]);
            if (j != -1) ans = Math.min(ans, i - j);
            update(sum[n + 1 + i], i);
        }
        return ans == n + 10 ? -1 : ans;
    }
}
