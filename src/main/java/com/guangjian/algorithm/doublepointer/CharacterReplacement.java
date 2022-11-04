package com.guangjian.algorithm.doublepointer;

/**
 * <b>替换后的最长重复字符</b>
 * <p>
 * 给你一个字符串 s 和一个整数 k 。你可以选择字符串中的任一字符，并将其更改为任何其他大写英文字符。该操作最多可执行 k 次。
 * <p>
 * 在执行上述操作后，返回包含相同字母的最长子字符串的长度。
 *
 * @author yanggj
 * @version 1.0.0
 * @date 2022/11/3 14:03
 */
public class CharacterReplacement {

    public int characterReplacement1(String s, int k) {
        char[] chars = s.toCharArray();
        // 用来存放每个字符出现的次数
        int[] count = new int[26];
        int ans = 0;
        int left = 0, right = 0;
        for (; right < s.length(); right++) {
            int cur = chars[right] - 'A';
            count[cur]++;
            // 判断字串是否合法
            while (!check(count, k)) {
                // 对不合法的字串
                int del = chars[left] - 'A';
                count[del]--;
                left++;
                // 可合并为一行
                // count[chars[left++] - 'A']--;
            }
            ans = Math.max(ans, right - left + 1);
        }
        return ans;
    }

    /**
     * sum 所有字符出现的次数之和
     * max 出现次数最多的字符次数
     * 校验字串是否合法，即 sum - max <= k
     */
    private boolean check(int[] count, int k) {
        int max = 0, sum = 0;
        for (int cnt : count) {
            max = Math.max(max, cnt);
            sum += cnt;
        }
        return sum - max <= k;
    }

    public int characterReplacement(String s, int k) {
        char[] chars = s.toCharArray();
        int res = 0;
        int max = 0;
        int left = 0, right = 0;
        int[] count = new int[26];
        while (right < s.length()) {
            // 每次右侧读近一个新字符
            count[chars[right] - 'A']++;
            max = Math.max(max, count[chars[right] - 'A']);
            right++;

            if (right - left > max + k) {
                count[chars[left] - 'A']--;
                left++;
            }
            res = Math.max(res, right - left);
        }
        return res;
    }
}
