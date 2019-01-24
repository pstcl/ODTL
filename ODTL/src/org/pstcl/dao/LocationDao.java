package org.pstcl.dao;

import java.util.List;

import org.pstcl.model.FilterModel;
import org.pstcl.model.entity.Circle;
import org.pstcl.model.entity.Division;
import org.pstcl.model.entity.Equipment;
import org.pstcl.model.entity.LocationMaster;
import org.pstcl.model.entity.Substation;

public interface LocationDao {
	
	List<Circle> listCircles(LocationMaster equipment);
	List<Division> listDivisions(LocationMaster equipment);
	List<Substation> listSubstations(LocationMaster equipment);
	
	Circle findCircleByID(String code);
	Division findDivisionByID(String code);
	Substation findSubstationByID(String code);
//	List<Substation> listSubstations(LocationMaster equipment);
	List<Substation> listSubstations(FilterModel entity);
	List<Division> listDivisions(FilterModel equipment);
	List<Circle> listCircles(FilterModel equipment);
	
	

}
