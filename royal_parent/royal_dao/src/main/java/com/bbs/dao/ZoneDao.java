package com.bbs.dao;

import com.bbs.domain.Zone;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ZoneDao {

    //根据ID查询zone,并且查询article集合，作者郭付民
    @Select("select * from bbs_zone_table where zoneId=#{zoneId}")
    @Results({
         @Result(id = true,property = "zoneId",column = "zoneId"),
            @Result(property = "zoneName",column = "zoneName"),
            @Result(property = "isDef",column = "isDef"),
            @Result(property = "articles",javaType = List.class,column = "zoneId",many=@Many(select = "com.bbs.dao.ArticleDao.findByZoneid"))
    })
    Zone findAll(Integer zoneId);
}
