package org.pstcl.dao;

import java.util.List;

import org.pstcl.model.EquipmentFilterModel;
import org.pstcl.model.FilterModel;
import org.pstcl.model.ODTLFilterModel;
import org.pstcl.model.entity.Equipment;
import org.pstcl.model.entity.User;



public interface EquipmentDao {

	Equipment findById(int id);
	
	
	
	void deleteById(String id);
	
	List<Equipment> findAllEquipments();


	List<Equipment> getEquipmentsList(Equipment equipment);


	void save(Equipment equipment, User user);



	List<Equipment> getEquipmentsList(Equipment equipment, Boolean getNAEquipments);







	List<Equipment> getEquipmentsList(EquipmentFilterModel filterModel);



	List<Equipment> getEquipmentsList(EquipmentFilterModel filterModel, Boolean getNAEquipments);

	
}

