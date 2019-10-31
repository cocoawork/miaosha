package com.petsquare.dao.status;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StatusTopicDao {

    private Integer id;
    private Integer topic_id;
    private String topic_title;
    private String topic_desc;
    private String topic_thum_url;
    private Timestamp create_at;

}
