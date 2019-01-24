package org.pstcl.dao;

import java.util.List;

import org.pstcl.model.entity.Equipment;
import org.pstcl.model.entity.LocationMaster;
import org.pstcl.model.entity.User;


public interface UserDao {

	User findById(int id);
	
	User findBySSO(String sso);
	
	void save(User newEntity, User appUser);
	
	void deleteBySSO(String sso);
	


	List<User> findColleagues(User user);

	

	List<User> findLocationUsers(LocationMaster user);

	List<User> findAllUsers();



}

