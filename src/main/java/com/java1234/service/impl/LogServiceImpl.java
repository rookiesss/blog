package com.java1234.service.impl;

import com.java1234.dao.LogMapper;
import com.java1234.entity.Log;
import com.java1234.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service("logService")
public class LogServiceImpl implements LogService {

    @Autowired
    private LogMapper logMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return logMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Log record) {
        return logMapper.insert(record);
    }

    @Override
    public int insertSelective(Log record) {
        return logMapper.insertSelective(record);
    }

    @Override
    public Log selectByPrimaryKey(Integer id) {
        return logMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Log record) {
        return logMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Log record) {
        return logMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Log> selectLogListByDate(Map<String, Object> map) {
        return logMapper.selectLogListByDate(map);
    }

    @Override
    public List<?> selectUserLogByDate(Map<String, Object> map) {
        return logMapper.selectLogListByDate(map);
    }
}
