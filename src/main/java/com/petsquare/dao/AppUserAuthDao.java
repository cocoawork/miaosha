package com.petsquare.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.sql.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AppUserAuthDao {

    @JsonIgnore
    private Integer id;
    @NotBlank
    private String user_id;
    @NotBlank
    private String card_num;
    @NotBlank
    private String real_name;
    private String address;
    @NotBlank
    @Past
    private Date birthday;

    private String real_avatar_url;
    private String card_img_urls;

}
