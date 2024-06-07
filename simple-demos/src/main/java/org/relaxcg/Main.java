package org.relaxcg;

public class Main {
    public static void main(String[] args) {
        String[] split = "avx        ".split(" ");
        for (String s : split) {
            System.out.println(s);
        }

        String code;
        int i = 1;
        while (" ".equals(code = split[i]) && i < split.length-1) {
            i++;
            System.out.println(i);
        }
        System.out.println(i);
    }
}