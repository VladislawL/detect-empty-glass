package com.emptyglass.core.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public final class FileProcessingUtils {

    private static final Logger logger =
            LoggerFactory.getLogger(FileProcessingUtils.class);

    private static File uploadDirectory = new File(System.getProperty("java.io.tmpdir"));

    private FileProcessingUtils() {
    }

    public static File saveTempFile(MultipartFile file) {
        String fileName = file.getOriginalFilename().split("\\.")[0];
        String format = file.getOriginalFilename().split("\\.")[1];
        OutputStream output = null;
        File temp = null;
        try {
            temp = File.createTempFile(fileName, "." + format, uploadDirectory);
            OutputStream out = new FileOutputStream(temp);
            out.write(file.getBytes());
        } catch (IOException e) {
            logger.error("couldn't open file", e);
        } finally {
            close(output);
        }
        return temp;
    }

    public static void close(Closeable c) {
        if (c == null) return;
        try {
            c.close();
        } catch (IOException e) {
            logger.error("couldn't open file", e);
        }
    }
}
