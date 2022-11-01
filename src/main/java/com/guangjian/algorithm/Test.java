package com.guangjian.algorithm;

/**
 * @author yanggj
 * @version 1.0.0
 * @date 2022/11/1 12:29
 */
public class Test {

    private int digisum(int num) {
        int res = 0;
        while (num != 0) {
            res += num % 10;
            num /= 10;
        }
        return res > 10 ? digisum(res) : res;
    }

    /*
        def digisum(num):
            res = 0
            while num != 0:
                res += num % 10
                num //= 10
            return digisum(res) if res > 10 else res
     */

    /*
      def nSums(num):
          while num>=10:
              sums = 0
              num_list = []
              for i in str(num):
                  num_list.append(i)
              for j in num_list:
                  sums = sums + int(j)
              num = sums
          return num
     */

}
