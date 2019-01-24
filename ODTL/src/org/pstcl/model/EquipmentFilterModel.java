package org.pstcl.model;

import org.pstcl.model.entity.Circle;
import org.pstcl.model.entity.Division;
import org.pstcl.model.entity.Substation;

public class EquipmentFilterModel  extends FilterModel{
	
	
	public EquipmentFilterModel(Circle circle,Division div,Substation sub,String capacity, String serialNo, String equipmentID, String make) {
		super();
		super.setSelectedCircle(circle);
		super.setSelectedDivision(div);
		super.setSelectedSubstation(sub);
		this.capacity = capacity;
		this.serialNo = serialNo;
		this.equipmentID = equipmentID;
		this.make = make;
	}
	private String capacity;
	private String serialNo;
	private String equipmentID;
	private String make;
	//model.addAttribute("equipments", (Object) equipments);
	public String getCapacity() {
		return capacity;
	}
	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}
	public String getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	public String getEquipmentID() {
		return equipmentID;
	}
	public void setEquipmentID(String equipmentID) {
		this.equipmentID = equipmentID;
	}
	public String getMake() {
		return make;
	}
	public void setMake(String make) {
		this.make = make;
	}
}
