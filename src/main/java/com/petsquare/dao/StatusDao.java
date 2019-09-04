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
@NoArgsConstructor
@AllArgsConstructor
public class StatusDao {

    @JsonIgnore
    private String id;
    @NotBlank
    private String user_id;
    private String content;
    private String img_urls;
    private Double lat;
    private Double lon;
    private String province;
    private String city;
    private String district;
    private Integer like_num;
    private Integer remark_num;
    private Integer share_num;
    private String tags;
    private String topic;
    @PastOrPresent
    private Timestamp create_at;

}
