package org.pstcl.logging;

import java.util.List;

import org.pstcl.model.entity.User;


public interface ILogDetailsDao {
	void save(LogDetails user);
	
	
	List<LogDetails> getList();
	
	
	List<LogDetails> getList(User user,LogDetails logDetails);
	
	
}