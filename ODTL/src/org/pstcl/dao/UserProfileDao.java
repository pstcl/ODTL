package org.pstcl.dao;

import java.util.List;

import org.pstcl.model.entity.UserProfile;


public interface UserProfileDao {

	List<UserProfile> findAll();
	
	UserProfile findByType(String type);
	
	UserProfile findById(int id);
}
