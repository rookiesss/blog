package com.java1234.service.impl;

import com.java1234.dao.VisitMapper;
import com.java1234.entity.Visit;
import com.java1234.service.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class VisitServiceImpl implements VisitService {
    @Autowired
    private VisitMapper visitMapper;
    @Override
    public int deleteByPrimaryKey(Integer id) {
        return visitMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Visit record) {
        return visitMapper.insert(record);
    }

    @Override
    public int insertSelective(Visit record) {
        return visitMapper.insertSelective(record);
    }

    @Override
    public Visit selectByPrimaryKey(Integer id) {
        return visitMapper.selectByPrimaryKey(id);
    }

    @Override
    public Visit selectVisitByIp(String ip) {
        return visitMapper.selectVisitByIp(ip);
    }

    @Override
    public Long findVisitTimes(Visit visit) {
        return visitMapper.findVisitTimes(visit);
    }

    @Override
    public List<?> selectVisitListByDate(Map<String, Object> map) {
        return visitMapper.selectVisitListByDate(map);
    }

    @Override
    public List<?> selectLikeVisitListGroupByIp(Map<String, Object> map) {
        return visitMapper.selectLikeVisitListGroupByIp(map);
    }

    @Override
    public List<Visit> selectLikeVisitListByPage(Map<String, Object> map) {
        return visitMapper.selectLikeVisitListByPage(map);
    }

    @Override
    public int updateByPrimaryKeySelective(Visit record) {
        return visitMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Visit record) {
        return visitMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<?> selectVisitListByIp(Map<String, Object> map) {
        return visitMapper.selectVisitListByIp(map);
    }
}
