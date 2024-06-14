package org.relaxcg.web.controller;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * @author relaxcg
 * @date 2024/5/27 17:46
 */
@RestController
@RequestMapping("/image")
public class ImageController {

    @GetMapping("/download-image")
    public ResponseEntity<Resource> downloadImage(@RequestParam("filename") String filename, HttpServletResponse response) throws IOException {
        // 检查文件是否存在
        ClassPathResource classPathResource = new ClassPathResource("images/"+filename);
        if (!classPathResource.exists()) {
            return ResponseEntity.notFound().build();
        }

        InputStream is = classPathResource.getInputStream();
        OutputStream os = response.getOutputStream();

        response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''"+filename);
        byte[] buff = new byte[2048];
        int i;
        while ((i = is.read(buff)) != -1) {
            os.write(buff, 0, i);
            os.flush();
        }
        return ResponseEntity.ok().build();
    }


    @PostMapping("/upload/chunk")
    public String saveChunk(@RequestParam("file") MultipartFile chunk,
                            @RequestParam("fileName") String fileName,
                            @RequestParam("chunkNumber") int chunkNumber ) {
        String chunkFilePath = fileName + "_" + chunkNumber;
        try {
            chunk.transferTo(new File(chunkFilePath));
        } catch (IOException e) {
            throw new RuntimeException("Failed to save chunk", e);
        }
        return chunkFilePath;
    }

    @PostMapping("/merge")
    public String mergeChunks(@RequestParam("fileName") String fileName,
                              @RequestParam("totalChunks") int totalChunks) {
        List<String> chunkFilePaths = new ArrayList<>();
        for (int i = 1; i <= totalChunks; i++) {
            chunkFilePaths.add(fileName + "_" + i);
        }
        File file = new File("1.text_1");
        System.out.println(file.exists());
        Path outputPath = Paths.get(fileName);
        try {
            byte[] concatenatedBytes = concatenateFiles(chunkFilePaths.stream().map(File::new).toList());
            Files.write(outputPath, concatenatedBytes);
            // 清理临时分片文件
            chunkFilePaths.forEach(this::deleteFile);
        } catch (IOException e) {
            throw new RuntimeException("Failed to merge chunks", e);
        }
        return "File merged successfully";
    }

    private void deleteFile(String filePath) {
        File file = new File(filePath);
        file.delete();
    }

    private byte[] concatenateFiles(List<File> files) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        for (File file : files) {
            Files.copy(file.toPath(), outputStream);
        }
        return outputStream.toByteArray();
    }
}
