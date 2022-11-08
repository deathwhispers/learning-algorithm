package com.guangjian.algorithm.doublepointer;

/**
 * <h1>翻转图像</h1>
 * 给定一个 n x n 的二进制矩阵 image ，先 <b>水平</b> 翻转图像，然后 <b>反转</b> 图像并返回结果 。
 * <p>
 * 水平翻转图片就是将图片的每一行都进行翻转，即逆序。
 * <p>
 * 例如，水平翻转 [1,1,0] 的结果是 [0,1,1]。
 * 反转图片的意思是图片中的 0 全部被 1 替换， 1 全部被 0 替换。
 * <p>
 * 例如，反转 [0,1,1] 的结果是 [1,0,0]。
 *
 * @author yanggj
 * @version 1.0.0
 * @date 2022/11/8 14:18
 */
public class FlipAndInvertImage {

    /**
     * <h2>双指针</h2>
     * 对于每行而言，我们都需要对其进行「翻转」和「反转」。
     * 这两步可以到放到一遍循环里做：
     * <ul>
     * <li>翻转部分：使用双指针进行数字交换</li>
     * <li>反转部分：将数字存储进目标位置前，使用「异或」对 0 1 进行翻转</li>
     * </ul>
     * 对于 Java 的基本类型拷贝，有三种方式进行拷贝：
     * <ol>
     * <li>System.arraycopy(): 底层的数组拷贝接口，具体实现与操作系统相关，调用的是系统本地方法。
     * 需要自己创建好目标数组进行传入，可指定拷贝长度，实现局部拷贝。</li>
     * <li>Arrays.copyOf(): 基于 System.arraycopy() 封装的接口，省去了自己目标数组这一步。但无法实现局部拷贝。</li>
     * <li>clone(): Object 的方法。会调用每个数组成员的 clone() 方法进行拷贝。
     * 因此对于一维数组而言，可以直接使用 clone() 得到「深拷贝数组」，
     * 而对于多维数组而言，得到的是「浅拷贝数组」。</li>
     * </ol>
     */
    public int[][] flipAndInvertImage(int[][] image) {
        int n = image.length;
        int[][] ans = new int[n][n];
        for (int i = 0; i < n; i++) {
            // ans[i] = image[i].clone();
            // ans[i] = Arrays.copyOf(image[i], n);
            System.arraycopy(image[i], 0, ans[i], 0, n);
            int left = 0, right = n - 1;
            while (left < right) {
                int temp = ans[i][right];
                // 左右指针元素交换，并反转，通过 异或 运算，将 0 1 反转
                ans[i][right] = ans[i][left] ^ 1;
                ans[i][left] = temp ^ 1;
                left++;
                right--;
            }
            // 针对基础情况，最中间元素需要单独反转（如果在 while 中则会反转两次变为原始值）
            if (n % 2 != 0) {
                ans[i][right] ^= 1;
            }
        }
        return ans;
    }

}
