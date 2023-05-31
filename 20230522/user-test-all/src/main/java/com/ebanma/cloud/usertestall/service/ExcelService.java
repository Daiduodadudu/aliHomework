package com.ebanma.cloud.usertestall.service;

import com.ebanma.cloud.usertestall.domain.dto.UserQueryDTO;

/**
 * @author : 连峰
 * @version $ Id: ExecelService, v 0.1 2023/03/29 10:45 banma- Exp $
 */
public interface ExcelService {
    void export(String filename, UserQueryDTO userQueryDTO);

    void asyncExport(String filename, UserQueryDTO userQueryDTO);
}
