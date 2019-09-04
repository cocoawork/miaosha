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
public class StatusLikeDao {
    @JsonIgnore
    private Integer id;
    @NotBlank
    private String status_id;
    @NotBlank
    private String user_id;
    @PastOrPresent
    private Timestamp create_at;
}
