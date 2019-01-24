package org.pstcl.service;

import java.util.List;
import java.util.Set;

import org.pstcl.model.entity.Equipment;
import org.pstcl.model.entity.OilSample;
import org.pstcl.model.entity.User;

public interface OilSampleService {
	List<OilSample> findAllOilSamples();

	List<String> getSampleTakenFromList();

	List<String> getSamplingValveList();

	List<String> getSampleContainerList();

	

	String saveOilSample(OilSample arg0);

	OilSample findOilSampleById(Integer arg0);
	OilSample createOilSampleForRoutineOil(Integer arg0);

	List<OilSample> getPendingOilSamples();

	boolean isSampleNoUnique(OilSample oilSample);


	void setEquipment(OilSample oilSample);

	String getEquipmentLabelForSample(Integer integer);

	OilSample createOilSampleForNewOil();

	boolean isEquipmentValid(OilSample oilSample);

	boolean isSampleUserValid(OilSample oilSample);

	Set<User> prepareSampleSenderList(OilSample oilSample);

	void setSampleSender(OilSample oilSample);

	OilSample createOilSampleForFreshOil();

	

	

}