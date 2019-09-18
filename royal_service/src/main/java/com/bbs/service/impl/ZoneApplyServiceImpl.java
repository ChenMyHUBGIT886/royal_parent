package com.bbs.service.impl;

import com.bbs.dao.ZoneApplayDao;
import com.bbs.domain.ZoneApply;
import com.bbs.service.ZoneApplyService;
import com.bbs.service.ZoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ZoneApplyServiceImpl implements ZoneApplyService{

    @Autowired
    private  ZoneApplayDao zoneApplayDao;
    @Override
    public void save(ZoneApply zoneApply) {
        zoneApplayDao.save(zoneApply);
    }
}
