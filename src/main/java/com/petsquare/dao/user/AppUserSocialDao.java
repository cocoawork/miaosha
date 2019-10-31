package com.petsquare.dao.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AppUserSocialDao {

    @JsonIgnore
    private Integer id;
    @NotBlank
    @JsonIgnore
    private String user_id;
    private String wx_id;
    private String qq_id;
    private String zfb_id;
    private String wb_id;

}
