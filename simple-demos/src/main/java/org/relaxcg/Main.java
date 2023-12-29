package org.relaxcg;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author relaxcg
 * @date 2023/11/29 17:46
 */
public class Main {
    public static void main(String[] args) {
        String formatted = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmm"));
        System.out.println(formatted);
    }
}