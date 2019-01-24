package org.pstcl.model;

import java.util.List;

import org.pstcl.model.entity.Circle;
import org.pstcl.model.entity.Division;
import org.pstcl.model.entity.Equipment;
import org.pstcl.model.entity.Substation;

public class FilterModel extends ODTLFilterModel{

	private Circle selectedCircle;
	private Division selectedDivision;
	private Substation selectedSubstation;
	
	
	private List< Circle >  circleList;
	public List<Circle> getCircleList() {
		return circleList;
	}
	public void setCircleList(List<Circle> circleList) {
		this.circleList = circleList;
	}
	public List<Division> getDivisionList() {
		return divisionList;
	}
	public void setDivisionList(List<Division> divisionList) {
		this.divisionList = divisionList;
	}
	public List<Substation> getSubstationList() {
		return substationList;
	}
	public void setSubstationList(List<Substation> substationList) {
		this.substationList = substationList;
	}
	private List< Division >  divisionList;
	private List< Substation >  substationList;
	private String oilSampleType;
	
	
	
	public Circle getSelectedCircle() {
		return selectedCircle;
	}
	public Division getSelectedDivision() {
		return selectedDivision;
	}
	public Substation getSelectedSubstation() {
		return selectedSubstation;
	}
	
	public void setSelectedCircle(Circle selectedCircle) {
		this.selectedCircle = selectedCircle;
	}
	public void setSelectedDivision(Division selectedDivision) {
		this.selectedDivision = selectedDivision;
	}
	public void setSelectedSubstation(Substation selectedSubstation) {
		this.selectedSubstation = selectedSubstation;
	}
	public String getOilSampleType() {
		return oilSampleType;
	}
	public void setOilSampleType(String oilSampleType) {
		this.oilSampleType = oilSampleType;
	}
	
	
	
	
}
