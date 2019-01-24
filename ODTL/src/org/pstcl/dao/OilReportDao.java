package org.pstcl.dao;

import java.util.List;

import org.pstcl.model.ODTLFilterModel;
import org.pstcl.model.OilReportFilterModel;
import org.pstcl.model.entity.OilReport;
import org.pstcl.model.entity.User;



public interface OilReportDao {
	
	

	OilReport findById(int id);
	
	
	
	
	void deleteById(String id);
	
	List<OilReport> findAllOilReports();




	void save(OilReport oilReport, User user);




	List<OilReport> findAllOilReports(OilReportFilterModel filterModel);

	
}

