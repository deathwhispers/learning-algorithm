package com.guangjian.algorithm.easy;

/**
 * 最长公共前缀<br/>
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 * <p>
 * 如果不存在公共前缀，返回空字符串  ""。
 * <p>
 * 示例 1：
 * <p>
 * <lu>
 * <li>输入：strs = ["flower","flow","flight"]</li>
 * <li>输出："fl"</li>
 * </lu>
 * <p>
 * 示例 2：
 * <p>
 * <lu>
 * <li>输入：strs = ["dog","racecar","car"]</li>
 * <li>输出：""</li>
 * </lu>
 * 解释：输入不存在公共前缀。
 *
 * @author yanggj
 * @version 1.0.0
 * @date 2022/10/9 15:55
 */
public class LongestCommonPrefix {

    /**
     * 横向扫描
     * 遍历每一个字符串，更新公共最长前缀
     */
    public String solution1(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        // 默认前缀为第一个字符串
        String prefix = strs[0];
        int count = strs.length;
        for (int i = 1; i < count; i++) {
            // 依次比较每一个字符串，并更新最大公共前缀
            prefix = longestCommonPrefix(prefix, strs[i]);
            if (prefix.length() == 0) {
                break;
            }
        }
        return prefix;
    }

    // 更新最大公共前缀
    public String longestCommonPrefix(String str1, String str2) {
        int length = Math.min(str1.length(), str2.length());
        int index = 0;
        while (index < length && str1.charAt(index) == str2.charAt(index)) {
            index++;
        }
        return str1.substring(0, index);
    }

    /**
     * 纵向扫描
     * 从前往后遍历字符串的每一列，如果相同则继续，如果不同，则返回
     */
    public String solution2(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        int len = strs[0].length();
        int count = strs.length;
        for (int i = 0; i < len; i++) {
            char c = strs[0].charAt(i);
            for (int j = 1; j < count; j++) {
                if (i == strs[j].length() || strs[j].charAt(i) != c) {
                    return strs[0].substring(0, i);
                }
            }
        }
        return strs[0];
    }

    /**
     * 注意到 LCP\\textit{LCP}LCP 的计算满足结合律，有以下结论：
     * <p>
     * LCP(S1…Sn)\=LCP(LCP(S1…Sk),LCP(Sk+1…Sn))\\textit{LCP}(S\_1 \\ldots S\_n) = \\textit{LCP}(\\textit{LCP}(S\_1 \\ldots S\_k), \\textit{LCP} (S\_{k+1} \\ldots S\_n)) LCP(S1​…Sn​)\=LCP(LCP(S1​…Sk​),LCP(Sk+1​…Sn​))
     * <p>
     * 其中 LCP(S1…Sn)\\textit{LCP}(S\_1 \\ldots S\_n)LCP(S1​…Sn​) 是字符串 S1…SnS\_1 \\ldots S\_nS1​…Sn​ 的最长公共前缀，1<k<n1 < k < n1<k<n。
     * <p>
     * 基于上述结论，可以使用分治法得到字符串数组中的最长公共前缀。对于问题 LCP(Si⋯Sj)\\textit{LCP}(S\_i\\cdots S\_j)LCP(Si​⋯Sj​)，可以分解成两个子问题 LCP(Si…Smid)\\textit{LCP}(S\_i \\ldots S\_{mid})LCP(Si​…Smid​) 与 LCP(Smid+1…Sj)\\textit{LCP}(S\_{mid+1} \\ldots S\_j)LCP(Smid+1​…Sj​)，其中 mid\=i+j2mid=\\frac{i+j}{2}mid\=2i+j​。对两个子问题分别求解，然后对两个子问题的解计算最长公共前缀，即为原问题的解。
     */
    public String solution3(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        } else {
            return longestCommonPrefix(strs, 0, strs.length - 1);
        }

    }

    public String longestCommonPrefix(String[] strs, int start, int end) {
        if (start == end) {
            return strs[start];
        } else {
            int mid = (end - start) / 2 + start;
            String lcpLeft = longestCommonPrefix(strs, start, mid);
            String lcpRight = longestCommonPrefix(strs, mid + 1, end);
            return commonPrefix(lcpLeft, lcpRight);
        }
    }

    public String commonPrefix(String lcpLeft, String lcpRight) {
        int minLength = Math.min(lcpLeft.length(), lcpRight.length());
        for (int i = 0; i < minLength; i++) {
            if (lcpLeft.charAt(i) != lcpRight.charAt(i)) {
                return lcpLeft.substring(0, i);
            }
        }
        return lcpLeft.substring(0, minLength);
    }
    /*
      复杂度分析
      时间复杂度：O(mn)O(mn)
      空间复杂度：O(m \log n)O(mlogn)，其中 mm 是字符串数组中的字符串的平均长度，nn 是字符串的数量。空间复杂度主要取决于递归调用的层数，层数最大为 \log nlogn，每层需要 mm 的空间存储返回结果。
     */


}
