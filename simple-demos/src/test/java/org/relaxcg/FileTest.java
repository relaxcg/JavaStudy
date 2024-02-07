package org.relaxcg;

import org.junit.Test;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author relaxcg
 * @date 2024/1/4 9:06
 */
public class FileTest {

    @Test
    public void testZipFileExists() {
        String path = "C:\\Users\\gaocheng\\Desktop\\65ac04f7065543e75b04e2856244c0ba_1591766513854962616.zip";
        System.out.println(new File(path).exists());
        Path zipFilePath = Paths.get(path);
        System.out.println(zipFilePath.toFile().exists());
    }
}
