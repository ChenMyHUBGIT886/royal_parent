package com.bbs.service.impl;

import com.bbs.dao.ZoneDao;
import com.bbs.domain.Zone;
import com.bbs.service.ZoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZoneServiceImpl implements ZoneService {
    @Autowired
    private  ZoneDao zoneDao;
//根据Id查询zone
    @Override
    public List<Zone> findAll() {
        return  zoneDao.findAll();

    }
}
