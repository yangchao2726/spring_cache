/**
 * @Description:TODO
 * @author:YC
 * @time:2016��12��28�� ����1:55:41
 */
package org.yc.spring_cache.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yc.spring_cache.dao.TestBeanMapper;
import org.yc.spring_cache.model.TestBean;
import org.yc.spring_cache.service.TestBeanService;

/**
 * @Description:TODO
 * @author:YC
 * @time:2016��12��28�� ����1:55:41
 */
@Service("testBeanService")
@Transactional
public class TestBeanServiceImpl implements TestBeanService {
	
	@Autowired
	private TestBeanMapper testBeanMapper;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return testBeanMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(TestBean record) {
		return testBeanMapper.insert(record);
	}

	@Override
	public int insertSelective(TestBean record) {
		return testBeanMapper.insertSelective(record);
	}

	@Override
	public TestBean selectByPrimaryKey(Integer id) {
		return testBeanMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(TestBean record) {
		return testBeanMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(TestBean record) {
		return testBeanMapper.updateByPrimaryKey(record);
	}

}
