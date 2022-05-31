package com.caroline.springbootmall.service;

import java.io.File;
import java.net.URL;
import java.util.List;

public interface FileService {
    void downloadFileFromUrl(URL url, File targetFile);
    List<String> readFile(File targetFile);
}
