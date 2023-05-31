package com.ebanma.cloud.usertestall.controller;

import com.ebanma.cloud.usertestall.domain.common.ErrorCode;
import com.ebanma.cloud.usertestall.domain.common.PageQuery;
import com.ebanma.cloud.usertestall.domain.common.PageResult;
import com.ebanma.cloud.usertestall.domain.common.Result;
import com.ebanma.cloud.usertestall.domain.dto.UserDTO;
import com.ebanma.cloud.usertestall.domain.dto.UserQueryDTO;
import com.ebanma.cloud.usertestall.domain.vo.UserVO;
import com.ebanma.cloud.usertestall.service.ExcelService;
import com.ebanma.cloud.usertestall.service.UserService;
import com.ebanma.cloud.usertestall.util.InsertValidationGroup;
import com.ebanma.cloud.usertestall.util.UpdateValidationGroup;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author : 连峰
 * @version $ Id: UserController, v 0.1 2023/03/16 13:51 banma- Exp $
 */
@Validated
@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private ExcelService excelService;

    @Autowired
    private RestTemplate restTemplate;

//    @Autowired
//    private ApplicationContext applicationContext;

//    @GetMapping("/test")
//    public String test(){
//        return "test";
//    }

    @CacheEvict(value = "users-cache", allEntries = true)
    @PostMapping
    public Result save(@Validated(InsertValidationGroup.class) @RequestBody UserDTO userDTO) {
        int save = userService.save(userDTO);
        if (save == 1) {
            return Result.success();
        } else {
            return Result.fail(ErrorCode.SYSTEM_ERROR);
        }
    }

    @PostMapping("/update/{id}")
    public Result update(@NotNull(message = "用户id不能为空") @PathVariable("id") Long id,
                         @Validated(UpdateValidationGroup.class) @RequestBody UserDTO userDTO) {
        int update = userService.update(id, userDTO);
        if (update == 1) {
            return Result.success();
        } else {
            return Result.fail(ErrorCode.SYSTEM_ERROR);
        }
    }

    @DeleteMapping
    public Result delete(@NotNull(message = "id不能为空") @Param("id") Long id) {
        int delete = userService.delete(id);
        if (delete == 1) {
            return Result.success();
        } else {
            return Result.fail(ErrorCode.SYSTEM_ERROR);
        }
    }

    @Cacheable(value = "c2", key = "#pageNo")
    @GetMapping("/query")
    public Result<PageResult<List<UserVO>>> query(@Param("pageNo") Integer pageNo,
                                    @Param("pageSize") Integer pageSize,
                                    @RequestBody UserQueryDTO queryDTO){
//                                    Model model) {

//        System.out.println(applicationContext.getBean("userController"));
//        String url = "http://localhost:8080/api/user/update/1";
//        MultiValueMap map0 = new LinkedMultiValueMap();
//        map0.add("username", "尼古拉斯赵四");
//        UserDO userDO = new UserDO();
//        userDO.setUsername("尼古拉斯赵dd");
//        userDO.setVersion(1L);
//        ResponseEntity<Result> obj = restTemplate.postForEntity(url, map0, Result.class);
//        HttpHeaders headers = obj.getHeaders();
//        HttpHeaders httpHeaders = new HttpHeaders();
//        HttpEntity<Object> objectHttpEntity = new HttpEntity<>(userDO, httpHeaders);
//        URI uri = restTemplate.postForLocation(url, objectHttpEntity);

        //测试 @ModelAttribute 属性
//        Map<String,Object> map = model.asMap();
//        System.out.println("111111111111111111111");
//        System.out.println(map.get("md"));


        System.out.println("---------------没走缓存");
        PageQuery<UserQueryDTO> pageQuery = new PageQuery<>();
        pageQuery.setPageNo(pageNo);
        pageQuery.setPageSize(pageSize);
        pageQuery.setQuery(queryDTO);
        PageResult<List<UserDTO>> pageResult = userService.query(pageQuery);

        //密码隐藏  DTO 转 VO
        List<UserVO> userVOList = Optional.ofNullable(pageResult.getData())
                .map(List::stream)
                .orElseGet(Stream::empty)
                .map(user -> {
                    UserVO userVO = new UserVO();
                    BeanUtils.copyProperties(user, userVO);
                    userVO.setPassword("******");
                    return userVO;
                })
                .collect(Collectors.toList());

        PageResult<List<UserVO>> result = new PageResult<>();
        BeanUtils.copyProperties(pageResult, result);
        result.setData(userVOList);

        return Result.success(result);
    }

//    @InitBinder
//    private void initBind(WebDataBinder binder) {
////        binder.setDisallowedFields("password");
////        binder.setRequiredFields("username");
////        binder.setIgnoreInvalidFields(true);
////        binder.setIgnoreUnknownFields(true);
////        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
//    }

    //查询数据并导出
    @GetMapping("/export")
    public Result<Boolean> export(@NotEmpty String filename, UserQueryDTO queryDTO) {
//        excelService.export(filename, queryDTO);
        excelService.asyncExport(filename, queryDTO);
        return Result.success(Boolean.TRUE);
    }


//    @PreAuthorize("hasAuthority('QUERY')")
    //@PreAuthorize("#")可以用在其他类里，例如service层
//    @PreAuthorize(("#queryDTO.getUserName() == authentication.principal.username")) 这样可以吗？？？
//    @PostMapping("/security")
//    public Result securityTest(@RequestBody UserQueryDTO queryDTO) {
//        userService.getUser(queryDTO.getUsername());
//        System.out.println("security: ------------");
//        return Result.success("Security");
//    }
}













































