package com.guangjian.algorithm.daily;

import java.util.*;

/**
 * <h1>获取所有钥匙的最短路径</h1>
 * 给定一个二维网格 grid ，其中：
 * <ul>
 * <li>'.' 代表一个空房间</li>
 * <li>'#' 代表一堵墙</li>
 * <li>'@' 是起点</li>
 * <li>小写字母代表钥匙</li>
 * <li>大写字母代表锁</li>
 * </ul>
 * 我们从起点开始出发，一次移动是指向四个基本方向之一行走一个单位空间。我们不能在网格外面行走，也无法穿过一堵墙。
 * 如果途经一个钥匙，我们就把它捡起来。除非我们手里有对应的钥匙，否则无法通过锁。
 * <p>
 * 假设 k 为 钥匙/锁 的个数，且满足 1 <= k <= 6，字母表中的前 k 个字母在网格中都有自己对应的一个小写和一个大写字母。
 * 换言之，每个锁有唯一对应的钥匙，每个钥匙也有唯一对应的锁。另外，代表钥匙和锁的字母互为大小写并按字母顺序排列。
 * <p>
 * 返回获取所有钥匙所需要的移动的最少次数。如果无法获取所有钥匙，返回 -1 。
 *
 * @author yanggj
 * @version 1.0.0
 * @date 2022/11/10 9:18
 */
public class ShortestPathAllKeys {
    static int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    /**
     * <h2>状态压缩 + 广度优先搜索</h2>
     * <b>思路与算法</b>
     * <p>
     * 给定一个只包含空房间、墙、起点和终点的二维网格，我们可以使用广度优先搜索的方法求出起点到终点的最短路径。<br>
     * 这是因为在最短路径上，我们最多只会经过每个房间一次。因此从起点开始，使用队列进行广度优先搜索，当第一个搜索到某个节点的时候，我们就可以得到从起点到该节点正确的最短路。
     * <p>
     * 如果加上了钥匙和锁，我们应该如何解决问题呢？<br>
     * 类似地，在最短路径上也不可能存在如下的情况：我们经过了某个房间两次，并且这两次我们拥有钥匙的情况是完全一致的。
     * <p>
     * 因此，我们可以用一个三元组 (x,y,mask) 表示当前的状态，其中 (x, y) 表示当前所处的位置，mask 是一个二进制数，长度恰好等于网格中钥匙的数目，mask 的第 i 个二进制位为 1，当且仅当我们已经获得了网格中的第 i 把钥匙。<br>
     * 这样一来，我们就可以使用上述的状态进行广度优先搜索。<br>
     * 初始时，我们把 (sx,sy,0) 加入队列，其中 (sx,sy) 为起点。<br>
     * 在搜索的过程中，我们可以向上下左右四个方向进行扩展：
     * <ul>
     *  <li>如果对应方向是空房间，那么 mask 的值不变；</li>
     *  <li>如果对应方向是第 i 把钥匙，那么将 mask 的第 i 位置为 1；</li>
     *  <li>如果对应方向是第 i 把锁，那么只有在 mask 的第 i 位为 1 时，才可以通过。</li>
     * </ul>
     * 当我们搜索到一个 mask 每一个二进制都为 1 的状态时，说明获取了所有钥匙，此时就可以返回最短路作为答案。
     */
    public int shortestPathAllKeys(String[] grid) {
        int m = grid.length, n = grid[0].length();
        // 记录起点坐标
        int sx = 0, sy = 0;
        Map<Character, Integer> keyToIndex = new HashMap<>();
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i].charAt(j) == '@') {
                    sx = i;
                    sy = j;
                } else if (Character.isLowerCase(grid[i].charAt(j))) {
                    if (!keyToIndex.containsKey(grid[i].charAt(j))) {
                        int idx = keyToIndex.size();
                        keyToIndex.put(grid[i].charAt(j), idx);
                    }

                }
            }
        }
        // dist
        int[][][] dist = new int[m][n][1 << keyToIndex.size()];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                // 填充dist
                Arrays.fill(dist[i][j], -1);
            }
        }
        Queue<int[]> queue = new ArrayDeque<>();
        // 将起点坐标入队
        queue.offer(new int[]{sx, sy, 0});
        dist[sx][sy][0] = 0;
        while (!queue.isEmpty()) {
            int[] arr = queue.poll();
            int x = arr[0], y = arr[1], mask = arr[2];
            for (int[] dir : dirs) {
                int nx = x + dir[0];
                int ny = y + dir[1];
                // 不超出棋盘位置,不遇到墙
                if (nx >= 0 && nx < m && ny >= 0 && ny < n && grid[nx].charAt(ny) != '#') {
                    if (grid[nx].charAt(ny) == '.' || grid[nx].charAt(ny) == '@') {
                        if (dist[nx][ny][mask] == -1) {
                            dist[nx][ny][mask] = dist[x][y][mask] + 1;
                            queue.offer(new int[]{nx, ny, mask});
                        }
                    } else if (Character.isLowerCase(grid[nx].charAt(ny))) {
                        // 小写字母为钥匙,更新钥匙
                        int idx = keyToIndex.get(grid[nx].charAt(ny));
                        if (dist[nx][ny][mask | (1 << idx)] == -1) {
                            dist[nx][ny][mask | (1 << idx)] = dist[x][y][mask] + 1;
                            if ((mask | (1 << idx)) == (1 << keyToIndex.size()) - 1) {
                                return dist[nx][ny][mask | (1 << idx)];
                            }
                            queue.offer(new int[]{nx, ny, mask | (1 << idx)});
                        }
                    } else {
                        // 遇到锁, 判断是否有钥匙
                        int idx = keyToIndex.get(Character.toLowerCase(grid[nx].charAt(ny)));
                        if ((mask & (1 << idx)) != 0 && dist[nx][ny][mask] == -1) {
                            // 有开锁的钥匙
                            dist[nx][ny][mask] = dist[x][y][mask] + 1;
                            queue.offer(new int[]{nx, ny, mask});
                        }
                    }
                }
            }
        }
        return -1;
    }

    static int N = 35, K = 10, INF = 0x3f3f3f3f;
    static int[][][] dist = new int[N][N][1 << K];
    static int[][] dirs2 = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public int shortestPathAllKeys2(String[] grid) {
        // cnt 记录钥匙的数量
        int n = grid.length, m = grid[0].length(), cnt = 0;
        Deque<int[]> queue = new ArrayDeque<>();
        // 遍历棋盘
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                Arrays.fill(dist[i][j], INF);
                char c = grid[i].charAt(j);
                if (c == '@') {
                    // 找到起点位置,并入队,维护钥匙收集状态
                    queue.addLast(new int[]{i, j, 0});
                    dist[i][j][0] = 0;
                } else if (c >= 'a' && c <= 'z') {
                    // 记录钥匙数量
                    cnt++;
                }
            }
        }
        while (!queue.isEmpty()) {
            int[] info = queue.pollFirst();
            // (x,y)表示在棋盘中的位置
            int x = info[0], y = info[1], cur = info[2], step = dist[x][y][cur];
            for (int[] dir : dirs2) {

                int nx = x + dir[0], ny = y + dir[1];
                if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
                    // 超出棋盘范围
                    continue;
                }
                // 移动后,棋盘上的元素
                char c = grid[nx].charAt(ny);
                if (c == '#') {
                    // 遇到墙,移动无效,尝试下一种移动方式
                    continue;
                }
                if ((c >= 'A' && c <= 'Z') && (cur >> (c - 'A') & 1) == 0) {
                    // 遇到锁,但未拿到钥匙,尝试下一种移动
                    continue;
                }
                int ncur = cur;
                if (c >= 'a' && c <= 'z') {
                    // 遇到钥匙, 更新状态
                    ncur |= 1 << (c - 'a');
                }
                if (ncur == (1 << cnt) - 1) {
                    // 所有钥匙均被搜集完成, 结束搜索
                    return step + 1;
                }
                if (step + 1 >= dist[nx][ny][ncur]) {
                    continue;
                }
                dist[nx][ny][ncur] = step + 1;
                queue.addLast(new int[]{nx, ny, ncur});
            }
        }
        return -1;
    }
}