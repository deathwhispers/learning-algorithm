package com.guangjian.algorithm.daily;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * <b>最短的桥</b>
 *
 * @author yanggj
 * @version 1.0.0
 * @date 2022/10/27 9:20
 */
public class ShortestBridge {
    static int N = 10010;
    static int[] p = new int[N];
    static int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    int n;

    int getIdx(int x, int y) {
        return x * n + y;
    }

    int find(int x) {
        if (p[x] != x) p[x] = find(p[x]);
        return p[x];
    }

    void union(int x, int y) {
        p[find(x)] = p[find(y)];
    }

    public int shortestBridge(int[][] g) {
        n = g.length;
        for (int i = 0; i <= n * n; i++) p[i] = i;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (g[i][j] == 0) continue;
                for (int[] di : dirs) {
                    int x = i + di[0], y = j + di[1];
                    if (x < 0 || x >= n || y < 0 || y >= n) continue;
                    if (g[x][y] == 0) continue;
                    union(getIdx(i, j), getIdx(x, y));
                }
            }
        }
        int a = -1, b = -1;
        Deque<int[]> d1 = new ArrayDeque<>(), d2 = new ArrayDeque<>();
        Map<Integer, Integer> m1 = new HashMap<>(), m2 = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (g[i][j] == 0) continue;
                int idx = getIdx(i, j), root = find(idx);
                if (a == -1) a = root;
                else if (a != root && b == -1) b = root;
                if (root == a) {
                    d1.addLast(new int[]{i, j});
                    m1.put(idx, 0);
                } else if (root == b) {
                    d2.addLast(new int[]{i, j});
                    m2.put(idx, 0);
                }
            }
        }
        while (!d1.isEmpty() && !d2.isEmpty()) {
            int t = -1;
            if (d1.size() < d2.size()) t = update(d1, m1, m2);
            else t = update(d2, m2, m1);
            if (t != -1) return t - 1;
        }
        return -1; // never
    }

    int update(Deque<int[]> d, Map<Integer, Integer> m1, Map<Integer, Integer> m2) {
        int sz = d.size();
        while (sz-- > 0) {
            int[] info = d.pollFirst();
            int x = info[0], y = info[1], idx = getIdx(x, y), step = m1.get(idx);
            for (int[] di : dirs) {
                int nx = x + di[0], ny = y + di[1], nidx = getIdx(nx, ny);
                if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
                if (m1.containsKey(nidx)) continue;
                if (m2.containsKey(nidx)) return step + 1 + m2.get(nidx);
                d.addLast(new int[]{nx, ny});
                m1.put(nidx, step + 1);
            }
        }
        return -1;
    }
}