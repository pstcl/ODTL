package org.pstcl.service;

import java.util.List;

import org.pstcl.model.FilterModel;
import org.pstcl.model.entity.Circle;
import org.pstcl.model.entity.Division;
import org.pstcl.model.entity.Equipment;
import org.pstcl.model.entity.Substation;

public interface EquipmentService {
	String saveEquipment(Equipment arg0);

	void deleteEquipmentById(String arg0);

	List<Equipment> findAllEquipments();

	List<String> getEquipmentTypeList();

	List<String> getVoltageClassList();

	List<String> getCoolingTypeList();

	List<String> getOilTypeList();

	List<Circle> getCircleList(Equipment equipment);

	List<Division> getDivisionList(Equipment equipment);

	List<Substation> getSubstationList(Equipment equipment);

	Circle findCircleById(String arg0);

	Division findDivisionById(String arg0);

	Substation findSubstationById(String arg0);

	Equipment findEquipmentById(Integer arg0);

	List<Equipment> getEquipmentsList(Equipment equipment);


	boolean isEquipmentIDUniqueForSS(Equipment equipment);

	boolean isEquipmentSNoValidForSS(Equipment equipment);

	void setLocation(Equipment equipment);

	



}