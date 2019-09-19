package com.bbs.service;

import com.bbs.domain.Zone;
import com.bbs.domain.ZoneApply;

import java.util.List;

public interface ZoneService {

    List<Zone> findAll();

    List<ZoneApply> findByPage(Integer pageNum, Integer pageSize);

    void changeZoneApplyStatus(Integer applyZoneId);

    void addZoneByZoneName(String zoneName);
}
