package com.java1234.service;

import com.java1234.entity.Visit;

import java.util.List;
import java.util.Map;

public interface VisitService {

    int deleteByPrimaryKey(Integer id);

    int insert(Visit record);

    int insertSelective(Visit record);

    Visit selectByPrimaryKey(Integer id);

    Visit selectVisitByIp(String ip);

    Long findVisitTimes(Visit visit);

    /**
     *  ���������·ݷ����ѯ
     * @return
     */
    List<?> selectVisitListByDate(Map<String, Object> map);

    /**
     * ģ����ѯ  and ������ʾ
     * @param map
     * @return
     */
    List<?> selectLikeVisitListGroupByIp(Map<String, Object> map);


    List<Visit> selectLikeVisitListByPage(Map<String, Object> map);

    int updateByPrimaryKeySelective(Visit record);

    int updateByPrimaryKey(Visit record);

    /**
     *  ����IP�����ѯ
     * @return
     */
    List<?>  selectVisitListByIp(Map<String, Object> map);
}
