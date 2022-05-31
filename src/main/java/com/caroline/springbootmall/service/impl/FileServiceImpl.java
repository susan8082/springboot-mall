package com.caroline.springbootmall.service.impl;

import com.caroline.springbootmall.service.FileService;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
public class FileServiceImpl implements FileService {
    @Override
    public void downloadFileFromUrl(URL url, File targetFile) {
        try {
            FileUtils.copyURLToFile(url, targetFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<String> readFile(File targetFile) {
        List<String> result = null;
        try {
            result =  FileUtils.readLines(targetFile, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
