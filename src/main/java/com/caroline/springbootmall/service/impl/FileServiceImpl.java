package com.caroline.springbootmall.service.impl;

import com.caroline.springbootmall.service.FileService;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

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

    @Override
    public File unzip(File targetFile) throws IOException  {
      byte [] buffer = new byte[1024];
        String filePath = null;
        ZipInputStream zis = new ZipInputStream(new FileInputStream(targetFile.getAbsolutePath()));
        ZipEntry zipEntry = zis.getNextEntry();
        while (zipEntry != null){
            filePath = new File("").getAbsolutePath()+File.separator+zipEntry.getName();
            FileOutputStream fos = new FileOutputStream(filePath);
            int len;
            while ((len = zis.read(buffer))>0){
                fos.write(buffer, 0, len);
            }
            fos.close();
            zis.closeEntry();
            zipEntry = zis.getNextEntry();
        }
        zis.closeEntry();
        zis.close();
        return new File(filePath);

    }
}
