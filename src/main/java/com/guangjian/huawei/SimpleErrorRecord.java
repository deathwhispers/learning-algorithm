package com.guangjian.huawei;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * <h1>简单错误记录</h1>
 *
 * @author yanggj
 * @version 1.0.0
 * @date 2023/3/29 16:29
 */
public class SimpleErrorRecord {

    static class Record {
        String name;
        Integer line;
        Integer count = 1;

        public Record(String name, Integer line) {
            this.name = name;
            this.line = line;
        }

        public boolean equals(Record record) {
            return this.name.equals(record.name) && this.line.equals(record.line);
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ArrayList<Record> result = new ArrayList<>(8);
        while (in.hasNextLine()) {
            String str = in.nextLine();
            String[] split = str.split(" ");
            Record record = new Record(correctName(split[0]), Integer.parseInt(split[1]));
            int flag = 0;
            for (Record record1 : result) {
                if (record1.equals(record)) {
                    record1.count++;
                    flag++;
                }
            }
            if (flag == 0) {
                result.add(record);
            }
        }
        int n = result.size();
        int i = 0;
        if (n > 8) {
            i = n - 8;
        }
        for (; i < n; i++) {
            System.out.printf("%s %s %s %n", result.get(i).name, result.get(i).line,
                    result.get(i).count);
        }
    }

    public static String correctName(String str) {
        String[] split = str.split("\\\\");
        String name = split[split.length - 1];
        if (name.length() > 16) {
            return name.substring(name.length() - 16);
        } else {
            return name;
        }
    }
}
