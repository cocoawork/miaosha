package com.petsquare.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import java.sql.Date;
import java.sql.Timestamp;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AppUserDto {


    private String user_id;
    @NotBlank
    private String name;
    private String avatar_url;
    private Integer age;
    private Integer gender;
    private String phone;
    private String email;
    @JsonIgnore
    private String password;
    @PastOrPresent
    private Timestamp regist_at;
    @PastOrPresent
    private Timestamp update_at;

    private Integer fans_num; //粉丝个数
    private Integer follow_num; //关注个数

    private AppUserAuthDto auth;
    private AppUserSocialDto social;


    @Setter
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AppUserAuthDto {
        private String card_num;
        private String real_name;
        private String address;
        private Date birthday;
        private String real_avatar_url;
        private String card_img_urls;
        private Timestamp create_at;
    }


    @Setter
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AppUserSocialDto{
        private String wx_id;
        private String qq_id;
        private String zfb_id;
        private String wb_id;
    }

    @Setter
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AppUserRelationDto{
        private String user_id; //用户id
        private String follow_id;//用户关注的用户id
        private Timestamp create_at;
    }


}
