package com.petsquare.service;

import com.petsquare.dao.DataSequenceDao;

public interface DataSequenceService {

    public Boolean addSequence(DataSequenceDao dataSequenceDao);

    public DataSequenceDao getDataSequenceByName(String name);

    public void updateDataSequence(DataSequenceDao dataSequenceDao);


}
