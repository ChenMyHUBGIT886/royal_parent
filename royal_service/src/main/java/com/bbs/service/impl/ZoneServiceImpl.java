package com.bbs.service.impl;

import com.bbs.dao.ZoneDao;
import com.bbs.domain.Zone;
import com.bbs.service.ZoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ZoneServiceImpl implements ZoneService {
    @Autowired
    private  ZoneDao zoneDao;
//根据Id查询zone
    @Override
    public Zone findById(Integer zoneId) {
        Zone zone= zoneDao.findById(zoneId);
        return zone;
    }
}
