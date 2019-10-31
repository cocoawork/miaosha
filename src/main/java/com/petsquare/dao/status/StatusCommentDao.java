package com.petsquare.dao.status;


import com.fasterxml.jackson.annotation.JsonIgnore;
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
@AllArgsConstructor
@NoArgsConstructor
public class StatusCommentDao {

    @JsonIgnore
    private Integer id;
    @NotBlank
    private String status_id;
    @NotBlank
    private String user_id;
    @NotBlank
    private String comment;
    @PastOrPresent
    private Timestamp create_at;
    private AppUserDao author;

}
