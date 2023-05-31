package com.ebanma.cloud.usertestall.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ebanma.cloud.usertestall.controller.data.TestControllerData;
import com.ebanma.cloud.usertestall.domain.common.ErrorCode;
import com.ebanma.cloud.usertestall.domain.common.PageQuery;
import com.ebanma.cloud.usertestall.domain.common.PageResult;
import com.ebanma.cloud.usertestall.domain.common.Result;
import com.ebanma.cloud.usertestall.domain.dto.UserDTO;
import com.ebanma.cloud.usertestall.domain.dto.UserQueryDTO;
import com.ebanma.cloud.usertestall.domain.vo.UserVO;
import com.ebanma.cloud.usertestall.service.ExcelService;
import com.ebanma.cloud.usertestall.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.powermock.api.mockito.PowerMockito;
import org.springframework.ui.Model;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

    @Mock
    private UserService userService;
    @Mock
    private ExcelService excelService;
    @Mock
    private RestTemplate restTemplate;
    @Mock
    private Result result;

    @InjectMocks
    private UserController userController;

    @Before
    public void setUp() throws Exception {

    }


    @Test
    public void should_invoke_user_service_to_save_given_user_DTO_success() {
        UserDTO userDTO = TestControllerData.getUserDtoData();

        int save = 1;
        Mockito.doReturn(save).when(userService).save(userDTO);
        Result save1 = userController.save(userDTO);

        Mockito.verify(userService).save(userDTO);
        assertEquals("返回结果异常",save1.getSuccess(), Boolean.TRUE);

    }
    @Test
    public void should_invoke_user_service_to_save_given_user_DTO_fail() {
        UserDTO userDTO = TestControllerData.getUserDtoData();

        int save = 0;
        Mockito.doReturn(save).when(userService).save(userDTO);
        Result save1 = userController.save(userDTO);

        Mockito.verify(userService).save(userDTO);
        assertEquals("返回结果异常",save1.getSuccess(), Boolean.FALSE);

    }

    @Test
    public void updateUserSuccess() {
        UserDTO userDtoData = TestControllerData.getUserDtoData();
        Long id = 1L;
        int update = 1;
        Mockito.when(userService.update(id,userDtoData)).thenReturn(update);
        userController.update(id,userDtoData);

        Mockito.verify(userService).update(id,userDtoData);
    }
    @Test
    public void updateUserFail() {
        UserDTO userDtoData = TestControllerData.getUserDtoData();
        Long id = 1L;
        int update = 0;
        Mockito.when(userService.update(id,userDtoData)).thenReturn(update);
        userController.update(id,userDtoData);

        Mockito.verify(userService).update(id,userDtoData);
    }
    @Test
    public void deleteSuccess() {
        Long id = 1L;
        int delete = 1;
        Mockito.when(userService.delete(id)).thenReturn(delete);
        userController.delete(id);

        Mockito.verify(userService).delete(id);
    }
    @Test
    public void deleteFail() {
        Long id = 1L;
        int delete = 0;
        Mockito.when(userService.delete(id)).thenReturn(delete);
        userController.delete(id);

        Mockito.verify(userService).delete(id);
    }

    @Test
    public void query() {
        Integer pageNo = 1;
        Integer pageSize = 10;

        UserQueryDTO userQueryDTO = TestControllerData.getUserQueryDtoData();
        PageQuery<UserQueryDTO> pageQuery = new PageQuery<>();
        pageQuery.setPageNo(pageNo);
        pageQuery.setQuery(userQueryDTO);
        pageQuery.setPageSize(pageSize);

        UserDTO userDTO = TestControllerData.getUserDtoData();
        ArrayList<UserDTO> list = new ArrayList<>();
        list.add(userDTO);

        PageResult<List<UserDTO>> pageResult = new PageResult<>();
        pageResult.setData(list);
        pageResult.setPageNum(10L);
        pageResult.setPageNo(1);
        pageResult.setTotal(1L);

        Mockito.doReturn(pageResult).when(userService).query(Mockito.any(PageQuery.class));//参数 给any 不会报错。
//        Mockito.doReturn(pageResult).when(userService).query(pageQuery); //给个参数，在下一行运行时会报空指针 ？？？？
        Result<PageResult<List<UserVO>>> result1 = userController.query(pageNo, pageSize, userQueryDTO);

        Mockito.verify(userService).query(Mockito.any());
        Assertions.assertEquals(result1.getCode(), ErrorCode.SUCCESS.getCode());
        Assertions.assertEquals(result1.getData().getData().get(0).getUsername(), "testName0" );

    }

    @Test
    public void export() {

        String fileName = "testFile.txt";
        UserQueryDTO userQueryDTO = TestControllerData.getUserQueryDtoData();

        Mockito.doNothing().when(excelService).asyncExport(fileName,userQueryDTO);
        userController.export(fileName,userQueryDTO);

        Mockito.verify(excelService).asyncExport(fileName,userQueryDTO);

    }
}