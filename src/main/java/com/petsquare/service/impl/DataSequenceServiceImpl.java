package com.petsquare.service.impl;

import com.petsquare.dao.DataSequenceDao;
import com.petsquare.mapper.DataSequenceMapper;
import com.petsquare.service.DataSequenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataSequenceServiceImpl implements DataSequenceService {

    @Autowired
    private DataSequenceMapper dataSequenceMapper;

    @Override
    public Boolean addSequence(DataSequenceDao dataSequenceDao) {
        Integer row = dataSequenceMapper.addSequence(dataSequenceDao);
        return row != 0;
    }

    @Override
    public DataSequenceDao getDataSequenceByName(String name) {
        return dataSequenceMapper.getDataSequenceByName(name);
    }


    @Override
    public void updateDataSequence(DataSequenceDao dataSequenceDao) {
        dataSequenceMapper.updateDataSequence(dataSequenceDao);
    }
}
