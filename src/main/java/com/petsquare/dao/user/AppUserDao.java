package com.petsquare.dao.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AppUserDao {
    @JsonIgnore
    private Integer id;
    @NotBlank
    private String user_id;
    @NotBlank
    private String name;
    private String avatar_url;
    private Integer age;
    private Integer gender;
    @NotBlank
    private String phone;
    @NotBlank
    @JsonIgnore
    private String password;
    private String email;
    private Timestamp regist_at;
    private Timestamp update_at;

}
