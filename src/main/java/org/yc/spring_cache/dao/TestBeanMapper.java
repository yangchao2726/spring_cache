package org.yc.spring_cache.dao;

import org.yc.spring_cache.model.TestBean;


public interface TestBeanMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TestBean record);

    int insertSelective(TestBean record);

    TestBean selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TestBean record);

    int updateByPrimaryKey(TestBean record);
}