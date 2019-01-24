package org.pstcl.model;

import org.pstcl.model.entity.Circle;
import org.pstcl.model.entity.Division;
import org.pstcl.model.entity.Substation;

public class RestModelDelME {
	private String selectedCircle;
	public String getSelectedCircle() {
		return selectedCircle;
	}
	public void setSelectedCircle(String selectedCircle) {
		this.selectedCircle = selectedCircle;
	}
	public String getSelectedDivision() {
		return selectedDivision;
	}
	public void setSelectedDivision(String selectedDivision) {
		this.selectedDivision = selectedDivision;
	}
	public String getSelectedSubstation() {
		return selectedSubstation;
	}
	public void setSelectedSubstation(String selectedSubstation) {
		this.selectedSubstation = selectedSubstation;
	}
	private String selectedDivision;
	private String selectedSubstation;
	
	
}
