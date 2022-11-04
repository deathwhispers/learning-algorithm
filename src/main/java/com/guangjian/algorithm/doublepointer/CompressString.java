package com.guangjian.algorithm.doublepointer;

/**
 * <b>压缩字符串</b>
 * 给你一个字符数组 chars ，请使用下述算法压缩：
 * <p>
 * 从一个空字符串 s 开始。对于 chars 中的每组 连续重复字符 ：
 * <p>
 * 如果这一组长度为 1 ，则将字符追加到 s 中。
 * 否则，需要向 s 追加字符，后跟这一组的长度。
 * 压缩后得到的字符串 s 不应该直接返回 ，需要转储到字符数组 chars 中。需要注意的是，如果组长度为 10 或 10 以上，则在 chars 数组中会被拆分为多个字符。
 * <p>
 * 请在 修改完输入数组后 ，返回该数组的新长度。
 * <p>
 * 你必须设计并实现一个只使用常量额外空间的算法来解决此问题。
 *
 * @author yanggj
 * @version 1.0.0
 * @date 2022/11/3 14:50
 */
public class CompressString {
    public int compress(char[] chars) {
        int n = chars.length;
        // 定义 i 指向原字符串，j 指向压缩后的字符串
        int i = 0, j = 0;
        while (i < n) {
            // 定义 index 指向相同字符的最末位
            int index = i;
            // 字符相同，则 index 一直右移
            while (index < n && chars[i] == chars[index]) index++;
            // index 指向字符与 i 处不同,先把 i 处字符写入 j 处，j++
            chars[j++] = chars[i];
            // 再考虑插入 相同字符的长度 len = index - i (这里不+1，是因为再上面的 while 中 index++ 指向了下一个字符)
            int len = index - i;
            // len = 1 时，不需要添加数字
            if (len > 1) {
                // 从 j 的位置开始插入数字
                int start = j;
                int end = start;
                while (len > 0) {
                    // 先添加 个位数字，再十位、百位。。。
                    chars[end++] = (char) ((len % 10) + '0');
                    len /= 10;
                }
                // 反转 start，end 这一段数字,注意此处 end 经过上面的 while 循环后已经++了，所以需要 -1 ，回退到末位
                reverse(chars, start, end - 1);
                j = end;
            }
            // 指针 i 移动到下一个重复字符 index 处
            i = index;
        }
        return j;
    }

    // 字符串反转
    void reverse(char[] cs, int start, int end) {
        while (start < end) {
            char t = cs[start];
            cs[start] = cs[end];
            cs[end] = t;
            start++;
            end--;
        }
    }
}
