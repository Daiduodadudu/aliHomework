package com.ebanma.cloud.usertestall.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@TableName(value = "t_user")
public class UserDO implements Serializable {

    /**
     * serialVersionUID     
     */
    private static final long serialVersionUID = -589896086247509038L;

    /*** 用户主信息 ***/
    /**     * 用户名  
     */
    private String username;

    /**
     * 用户密码  
     */
    private String password;

    /**
     * 邮箱  
     */
    private String email;

    /**
     * 年龄  
     */
    private Integer age;

    /**
     * 手机号  
     */
    private String phone;


    /*** 系统主信息 ***/
    /**     * 数据库主键  
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 数据的创建时间  
     */
    @TableField(value = "created", fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-DD HH:mm:ss")
    private LocalDateTime created;

    /**
     * 数据修改时间  
     */
    private LocalDateTime modified;

    /**
     * 创建者  
     */
//    @TableField(value = "creator", fill = FieldFill.INSERT)
    private String creator;

    /**
     * 最后修改者  
     */
    private String operator;

    /**
     * 逻辑删除字段：0：正常，1：逻辑删除  
     */
    @TableField(exist = false)
    private Integer deleted;

    /**
     * 版本号  
     */
    private Long version;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getModified() {
        return modified;
    }

    public void setModified(LocalDateTime modified) {
        this.modified = modified;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "UserDO{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", phone='" + phone + '\'' +
                ", id=" + id +
                ", created=" + created +
                ", modified=" + modified +
                ", creator='" + creator + '\'' +
                ", operator='" + operator + '\'' +
                ", deleted=" + deleted +
                ", version=" + version +
                '}';
    }
}