package com.bbs.dao;

import com.bbs.domain.Zone;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ZoneDao {

    @Select("select * from bbs_zone_table")
    List<Zone> findAll();

    /**
     * 根据zoneId查找zone
     * @param id
     * @return
     */
    @Select("select * from bbs_zone_table where zoneId=#{id}")
     Zone findById(int id) throws Exception;
}
