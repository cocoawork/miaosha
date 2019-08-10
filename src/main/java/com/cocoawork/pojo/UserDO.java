package com.cocoawork.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class UserDO implements Serializable {
    static final long serialVersionUID = 1L;
    private Long id;

    @NotBlank(message = "姓名不能为空")
    private String name;

    @JsonIgnore
    @NotNull(message = "密码不能为空")
    private String password;

    @Min(value = 0, message = "最小只能为0,标识女")
    @Max(value = 1, message = "最大只能为1,表示男")
    private Integer gender;
    private String address;

    @NotBlank(message = "手机号不能为空")
    private String telphone;

    public UserDO() {
    }

    public UserDO(String name, String password, Integer gender, String address, String telphone) {
        this.name = name;
        this.password = password;
        this.gender = gender;
        this.address = address;
        this.telphone = telphone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }

    @Override
    public String toString() {
        return "UserDO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", gender=" + gender +
                ", address='" + address + '\'' +
                ", telphone='" + telphone + '\'' +
                '}';
    }
}
