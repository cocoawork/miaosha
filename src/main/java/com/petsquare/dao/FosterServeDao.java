package com.petsquare.dao;


import com.petsquare.dao.user.AppUserDao;
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
public class FosterServeDao {


    private Integer id;
    @NotBlank
    private String user_id;
    @NotBlank
    private String name;
    private String service_desc;
    private String family_desc;
    private String img_urls;
    private String address;
    @NotBlank
    private Double lat;
    @NotBlank
    private Double lon;
    private String province;
    private String city;
    private String district;
    private Integer max_pet_count;
    private Float house_space;
    private Integer house_type;
    private String walk_place;
    private String walk_duration;
    private String accept_pet_type;
    private String service_pet_type;
    private Integer foster_type;
    private String house_plant;
    private String self_provide;
    private String foster_limit;
    @PastOrPresent
    private Timestamp create_at;
    private Boolean is_open;

    private AppUserDao user;

}
