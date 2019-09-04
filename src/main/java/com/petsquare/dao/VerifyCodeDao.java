package com.petsquare.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class VerifyCodeDao {

    private Integer id;
    private String phone;
    private String verify_code;
    private Integer type;
    private Date create_at;
    private Boolean is_used;
    private Date expire_at;

}
