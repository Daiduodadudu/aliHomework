package com.ebanma.cloud.usertestall.service;

import java.io.File;
import java.io.InputStream;

/**
 * @author : 连峰
 * @version $ Id: FileService, v 0.1 2023/03/29 10:14 banma- Exp $
 */
public interface FileService {

    void upload(InputStream inputStream, String filename);

    void upload(File file);
}
