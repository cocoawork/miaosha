package com.petsquare.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import java.sql.Timestamp;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AppUserRelationDao {

    @JsonIgnore
    private Integer id;
    @NotBlank
    private String user_id; //用户id
    @NotBlank
    private String follow_id;//用户关注的用户id
    @PastOrPresent
    private Timestamp create_at;

}
