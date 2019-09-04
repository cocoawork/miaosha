package com.petsquare.mapper;

import com.petsquare.dao.DataSequenceDao;

public interface DataSequenceMapper {

    public Integer addSequence(DataSequenceDao dataSequenceDao);

    public DataSequenceDao getDataSequenceByName(String name);

    public void updateDataSequence(DataSequenceDao dataSequenceDao);

}
