package com.bbs.dao;

import com.bbs.domain.Zone;
import com.bbs.domain.ZoneApply;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface ZoneDao {

    @Select("select * from bbs_zone_table")
    List<Zone> findAll();

    @Select("select * from bbs_zoneapply_table where status = 0")
    List<ZoneApply> findByPage();

    @Update("update bbs_zoneapply_table set status = 1 where applyZoneId = #{applyZoneId}")
    void changeZoneApplyStatus(Integer applyZoneId);

    @Insert("insert into bbs_zone_table (zoneName,isDef) values(#{zoneName},2)")
    void addZoneByZoneName(String zoneName);
}
