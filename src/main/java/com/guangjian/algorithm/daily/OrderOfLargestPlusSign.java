package com.guangjian.algorithm.daily;

import java.util.Arrays;

/**
 * <h1>最大加号标志</h1>
 * 在一个 n x n 的矩阵 grid 中，除了在数组 mines 中给出的元素为 0，其他每个元素都为 1。mines[i] = [xi, yi]表示 grid[xi][yi] == 0
 * <p>
 * 返回  grid 中包含 1 的最大的 <b>轴对齐</b> 加号标志的阶数 。如果未找到加号标志，则返回 0 。
 * <p>
 * 一个 k 阶由 1 组成的 <b>“轴对称”加号标志</b> 具有中心网格 grid[r][c] == 1 ，以及4个从中心向上、向下、向左、向右延伸，长度为 k-1，由 1 组成的臂。注意，只有加号标志的所有网格要求为 1 ，别的网格可能为 0 也可能为 1 。
 *
 * @author yanggj
 * @version 1.0.0
 * @date 2022/11/9 10:03
 */
public class OrderOfLargestPlusSign {

    public int orderOfLargestPlusSign(int n, int[][] mines) {
        // 初始原有矩阵，防止数组越界，将 n*n 矩阵扩充一周变为 n+2 * n+2矩阵
        // 需要注意扩充后矩阵从 1 开始到 n 结束
        int[][] origin = new int[n + 2][n + 2];
        // 将 (1,1) 到 (n,n) 填充为 1
        for (int i = 1; i <= n; i++) {
            Arrays.fill(origin[i], 1);
        }
        // 将 mines 的位置填充为 0
        for (int[] mine : mines) {
            origin[mine[0] + 1][mine[1] + 1] = 0;
        }

        // 定义四个矩阵，分别表示从中心点向上、下、左、右四个方向的最大连续 1 的个数，即阶数；取四者中最小值建立新的矩阵
        int[][] up = new int[n + 2][n + 2];
        int[][] down = new int[n + 2][n + 2];
        int[][] left = new int[n + 2][n + 2];
        int[][] right = new int[n + 2][n + 2];

        // 填充 up,down,left,right 四个矩阵, 注意下标从 1 开始到 n (包含 n )结束
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (origin[i][j] == 1) {
                    // up 依赖上一层的值
                    up[i][j] = up[i - 1][j] + 1;
                    // left 依赖左一列的值
                    left[i][j] = left[i][j - 1] + 1;
                }
                // 此时 i + 1 行,j + 1 列还未赋值，需要换个方向来赋值
                // 从右下角开始，向左上填充
                int p = n - i + 1;
                int q = n - j + 1;
                if (origin[p][q] == 1) {
                    // down 比 下一层 +1
                    down[p][q] = down[p + 1][q] + 1;
                    // right 比 右一列 +1
                    right[p][q] = right[p][q + 1] + 1;
                }
            }
        }
        int ans = 0;
        // 每个点 (i,j) 取 up,down,left,right 中最小值,即为阶数 k
        // 取 所有点中 最大值为最终结果
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                // 上、下、左、右四个矩阵中，每一点的最小值极为 k；取所有 k 中的最大值
                ans = Math.max(ans, Math.min(Math.min(up[i][j], down[i][j]), Math.min(left[i][j], right[i][j])));
            }
        }
        return ans;
    }

}
