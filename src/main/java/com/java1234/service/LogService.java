package com.java1234.service;

import com.java1234.entity.Log;

import java.util.List;
import java.util.Map;

public interface LogService {

    int deleteByPrimaryKey(Integer id);

    int insert(Log record);

    int insertSelective(Log record);

    Log selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Log record);

    int updateByPrimaryKey(Log record);

    List<Log> selectLogListByDate(Map<String, Object> map);

    List<?> selectUserLogByDate(Map<String, Object> map);
}
