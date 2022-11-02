package com.guangjian.algorithm.daily;

/**
 * 网络信号最好的坐标
 *
 * @author yanggj
 * @version 1.0.0
 * @date 2022/11/2 10:16
 */
public class BestCoordinate {

    public int[] bestCoordinate1(int[][] towers, int radius) {
        // 记录坐标系上每个点的信号值
        int[][] g = new int[110][110];
        // x,y记录答案坐标， val记录最大信号值
        int x = 0, y = 0, val = 0;
        for (int[] tower : towers) {
            int a = tower[0], b = tower[1], q = tower[2];
            for (int i = Math.max(0, a - radius); i <= a + radius; i++) {
                for (int j = Math.max(0, b - radius); j <= b + radius; j++) {
                    double d = Math.sqrt((a - i) * (a - i) + (b - j) * (b - j));
                    // 距离比k大，则跳过
                    if (d > radius) continue;
                    g[i][j] += Math.floor(q / (1 + d));
                    if (g[i][j] > val) {
                        x = i;
                        y = j;
                        val = g[i][j];
                    } else if (g[i][j] == val && (i < x || (i == x && j < y))) {
                        x = i;
                        y = j;
                    }
                }
            }
        }
        return new int[]{x, y};
    }

    public int[] bestCoordinate(int[][] towers, int k) {
        int[][] g = new int[110][110];
        int x = 0, y = 0, val = 0;
        for (int[] t : towers) {
            int a = t[0], b = t[1], q = t[2];
            for (int i = Math.max(0, a - k); i <= a + k; i++) {
                for (int j = Math.max(0, b - k); j <= b + k; j++) {
                    double d = Math.sqrt((a - i) * (a - i) + (b - j) * (b - j));
                    if (d > k) continue;
                    g[i][j] += Math.floor(q / (1 + d));
                    if (g[i][j] > val) {
                        x = i;
                        y = j;
                        val = g[i][j];
                    } else if (g[i][j] == val && (i < x || (i == x && j < y))) {
                        x = i;
                        y = j;
                    }
                }
            }
        }
        return new int[]{x, y};
    }
}
