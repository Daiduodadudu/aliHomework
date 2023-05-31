package com.ebanma.cloud.usertestall.domain.dto;

import com.ebanma.cloud.usertestall.util.InsertValidationGroup;
import com.ebanma.cloud.usertestall.util.UpdateValidationGroup;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author : 连峰
 * @version $ Id: UserDTO, v 0.1 2023/03/16 10:14 banma- Exp $
 */
public class UserDTO implements Serializable {
    private static final long serialVersionUID = -3935540232845355428L;

    /**
    *
    */
    @NotBlank(message = "用户名不能为空！", groups = InsertValidationGroup.class)
    private String username;

    /**
    *
    */
    @NotBlank(message = "密码不能为空！", groups = InsertValidationGroup.class)
    @Length(min = 6, max = 20, message = "密码长度不能小于6为，不能多于20位")
    private String password;

    /**
    *
    */
    @NotEmpty(message = "邮箱不能为空", groups = InsertValidationGroup.class)
    @Email(message = "必须为有效的邮箱格式")
    private String email;

    @NotNull(message = "年龄不能为空", groups = InsertValidationGroup.class)
    @Max(value = 100, message = "年龄不能大于100岁")
    @Min(value = 10, message = "年龄不能小于10岁")
    private Integer age;

    /**
    *
    */
    @NotNull(message = "手机号不能为空")
    private String phone;

    /**
    *
    */
    @NotNull(message = "版本号不能为空", groups = UpdateValidationGroup.class)
    private Long version;
    /**
    *
    */
    private LocalDateTime created;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }
}
