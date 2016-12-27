package org.yc.spring_cache.service;

import java.util.List;

import org.yc.spring_cache.model.MUser;

public interface MUserServiceI {

	List<MUser> getAll();
	
	MUser selectByPrimaryKey(Integer id);
	
    int insert(MUser muser) throws Exception;
    
    int update(MUser muser);
    
    int delete(Integer id);
}
