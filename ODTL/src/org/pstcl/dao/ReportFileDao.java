package org.pstcl.dao;

import java.util.List;

import org.pstcl.model.FilterModel;
import org.pstcl.model.entity.ReportFile;
import org.pstcl.model.entity.User;



public interface ReportFileDao {
	
	

	ReportFile findById(int id);
	
	
	
	
	void deleteById(String id);
	
	List<ReportFile> findAllReportFiles(Integer start, Integer end);




	void save(ReportFile reportFile, User user);




	List<ReportFile> findAllReportFiles(FilterModel filterModel);




	//ReportFile findByReportId(Integer id);




	ReportFile findBySampleId(Integer id);




	//ReportFile findByReportId(Integer id);

	
}

