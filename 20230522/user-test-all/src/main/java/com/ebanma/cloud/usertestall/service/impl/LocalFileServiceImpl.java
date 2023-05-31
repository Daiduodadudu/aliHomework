package com.ebanma.cloud.usertestall.service.impl;

import com.ebanma.cloud.usertestall.domain.common.ErrorCode;
import com.ebanma.cloud.usertestall.exception.BusinessException;
import com.ebanma.cloud.usertestall.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;

/**
 * @author : 连峰
 * @version $ Id: LocalFileServiceImpl, v 0.1 2023/03/29 10:16 banma- Exp $
 */
@Service(value = "localFileServiceImpl")
public class LocalFileServiceImpl implements FileService {
    private static final Logger logger = LoggerFactory.getLogger(LocalFileServiceImpl.class);

    private static final String BUCKET = "uploads";
    @Override
    public void upload(InputStream inputStream, String filename) {
        //拼接文件上传路径
        String path = BUCKET + "/" + filename;
        //定义输入输出流
        try (
                InputStream innerInputStream = inputStream;
                FileOutputStream fileOutputStream = new FileOutputStream(path);
        ) {
           //定义缓冲区， 每次从文件读 1024字节
            byte[] buffer = new byte[1024];
            int len;
            while ((len = innerInputStream.read(buffer)) != -1){
                fileOutputStream.write(buffer, 0, len);
            }
            fileOutputStream.flush();
        } catch (Exception e) {
            logger.error("文件上传失败", e);
            throw new BusinessException(ErrorCode.FILE_UPLOAD_FAILURE);
        }
    }

    @Override
    public void upload(File file) {
        try {
//            InputStream inputStream = new FileInputStream(file);
            upload(Files.newInputStream(file.toPath()), file.getName());
        } catch (IOException e) {
            logger.error("文件上传失败",e);
            throw new BusinessException(ErrorCode.FILE_UPLOAD_FAILURE);
        }
    }
}
