package org.pstcl.dao;

import java.util.List;

import org.pstcl.model.entity.UserDesignation;




public interface IUserDesignationDao {

	List<UserDesignation> findAllDesignations();

	UserDesignation findById(int id);
	
}
