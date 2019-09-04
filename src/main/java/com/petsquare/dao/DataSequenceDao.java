package com.petsquare.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DataSequenceDao {
    private Integer id;
    private String name;
    private Integer begin;
    private Integer current;
    private Integer step;
}
