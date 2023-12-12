package com.example.controller;

import cn.hutool.core.io.FileUtil;
import com.example.common.Result;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;

@RestController
@RequestMapping("/files")
public class FileController {

    private static final String ROOT_PATH = System.getProperty("user.dir") + "/files";
    @PostMapping("/upload")
    public Result upload (@RequestParam("file") MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();   // get original filename
        long flag = System.currentTimeMillis();
        String filename = flag + "_" + originalFilename;
        File finalFile = new File(ROOT_PATH +"/"+ filename);
        if (!finalFile.getParentFile().exists()) {
                finalFile.getParentFile().mkdirs();
        }
        file.transferTo(finalFile);
        String url = "http://localhost:9090/files/download?fileName=" + filename;
        return Result.success(url);
    }

    @GetMapping("/download")
    public void download(String filename, HttpServletResponse response) throws IOException {
        File file = new File(ROOT_PATH +"/"+ filename);
        ServletOutputStream outputStream = response.getOutputStream();
        response.setHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode(filename,"UTF-8"));
        response.setContentType("application/octet-stream");
        FileUtil.writeToStream(file, outputStream);
        outputStream.flush();
        outputStream.close();

    }
}
