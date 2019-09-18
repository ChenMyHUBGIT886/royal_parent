package com.bbs.dao;

import com.bbs.domain.ZoneApply;
import org.apache.ibatis.annotations.Insert;

public interface ZoneApplayDao {

    //用户申请版块
    @Insert("insert into bbs_zoneapply_table(zoneName,userName,reason,status) values(#{zoneName},#{userName},#{reason},0)")
    void save(ZoneApply zoneApply);
}
