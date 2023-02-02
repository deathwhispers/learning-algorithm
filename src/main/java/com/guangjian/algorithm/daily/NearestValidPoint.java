package com.guangjian.algorithm.daily;

/**
 * @author yanggj
 * @version 1.0.0
 * @date 2022/12/1 17:52
 */
public class NearestValidPoint {

    public int nearestValidPoint(int x, int y, int[][] points) {
        int n = points.length;
        int ans = -1;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int a = points[i][0];
            int b = points[i][1];
            if (a == x) {
                int abs = Math.abs(b - y);
                if (abs < min) {
                    min = abs;
                    ans = i;
                }
            }
            if (b == y) {
                int abs = Math.abs(a - x);
                if (abs < min) {
                    min = abs;
                    ans = i;
                }
            }
        }
        return ans;
    }
}
