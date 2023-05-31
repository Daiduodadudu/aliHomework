package com.ebanma.cloud.usertestall.controller;

import com.ebanma.cloud.usertestall.domain.common.ErrorCode;
import com.ebanma.cloud.usertestall.domain.common.Result;
import com.ebanma.cloud.usertestall.exception.BusinessException;
import com.ebanma.cloud.usertestall.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.io.IOException;

/**
 * @author : 连峰
 * @version $ Id: FileController, v 0.1 2023/03/29 10:13 banma- Exp $
 */
@RestController
@RequestMapping("/api/file")
public class FileController {

    public static final Logger logger = LoggerFactory.getLogger(FileController.class);

    @Resource(name = "localFileServiceImpl")
    private FileService fileService;

    @PostMapping("/upload")
    public Result<String> upload(@NotNull MultipartFile file) {
        try {
//            file.transferTo(new File("/uploads" + file.getOriginalFilename()));
            fileService.upload(file.getInputStream(), file.getOriginalFilename());
        } catch (IOException e) {
            logger.error("文件上传失败", e);
            throw new BusinessException(ErrorCode.FILE_UPLOAD_FAILURE, e);
        }
        return Result.success(file.getOriginalFilename());
    }
}
























