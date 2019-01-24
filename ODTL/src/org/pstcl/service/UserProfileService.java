package org.pstcl.service;

import java.util.List;

import org.pstcl.model.entity.UserProfile;

public interface UserProfileService {
	UserProfile findById(int arg0);

	UserProfile findByType(String arg0);

	List<UserProfile> findAll();
}