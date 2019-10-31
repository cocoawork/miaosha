package com.petsquare.dao.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.sql.Date;
import java.sql.Timestamp;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AppUserAuthDao {

    @JsonIgnore
    private Integer id;
    @JsonIgnore
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
    private Timestamp create_at;
    private String real_avatar_url;
    private String card_img_urls;

}
