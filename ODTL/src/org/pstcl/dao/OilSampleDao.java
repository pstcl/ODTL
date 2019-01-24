package org.pstcl.dao;

import java.util.List;

import org.pstcl.model.ODTLFilterModel;
import org.pstcl.model.OilSampleFilterModel;
import org.pstcl.model.entity.OilSample;
import org.pstcl.model.entity.User;



public interface OilSampleDao {

	OilSample findById(int id);
	
	
	
	void deleteById(String id);
	
	List<OilSample> findAllOilSamples();


	OilSample findBySampleNo(Integer integer);


	OilSample findLatestByUser(User user);


	void save(OilSample oilSample, User user);



	OilSample findLargestSampleByNo();



	List<OilSample> findPendingOilSamples();



	



	List<OilSample> findAllOilSamples(OilSampleFilterModel filterModel);

	
}

