package org.pstcl.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "equipment_master",uniqueConstraints={
		@UniqueConstraint(columnNames={"division", "substation","equipmentID"}),
		@UniqueConstraint(columnNames={"division", "substation","equipmentID","serialNo"})
})

public class Equipment extends LocationMaster {

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Equipment other = (Equipment) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Transient
	private String equipmentLabel;
	
	public String getEquipmentLabel() {
		StringBuilder label=new StringBuilder(equipmentType+":"+equipmentID);
		if(null!=substation)
		{
			label.append(substation.getSubstationDescription());
		}
		if(null!=division)
		{
			label.append(division.getDivisionDescription());
		}
		return label.toString();
	}

	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column
	private String equipmentType;
	
	
	@Column
	@NotBlank
	private String equipmentID;
	@Column(name = "MAKE")
	private String make;
	@Column
	private String serialNo;
	@Column
	private String yearOfMfg;
	@Column
	private String voltageClass;
	@Column
	private String capacity;
	@Column
	private String coolingType;


	@Temporal(TemporalType.TIMESTAMP)
	@Column
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date commissioningDate;

	public Date getCommissioningDate() {
		return commissioningDate;
	}

	public void setCommissioningDate(Date commissioningDate) {
		this.commissioningDate = commissioningDate;
	}

	@Column
	private String oilType;
	
	
//	@NotNull
//	@ManyToOne(fetch = FetchType.EAGER)
//	@JoinColumn(name = "CIRCLE", nullable = false )
//	private Circle circle;
//	
//	@NotNull
//	@ManyToOne(fetch = FetchType.EAGER)
//	@JoinColumn(name = "DIVISION", nullable = false)
//	private Division division;
//	
//	@NotNull
//	@ManyToOne(fetch = FetchType.EAGER)
//	@JoinColumn(name = "SUBSTATION", nullable = false)
//	private Substation substation;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_Entered")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dateEntered;

	public Equipment updateEquipment(final Equipment modifiedEquipment) {
		this.equipmentType = modifiedEquipment.equipmentType;
		this.equipmentID = modifiedEquipment.equipmentID;
		this.make = modifiedEquipment.make;
		this.serialNo = modifiedEquipment.serialNo;
		this.yearOfMfg = modifiedEquipment.yearOfMfg;
		this.voltageClass = modifiedEquipment.voltageClass;
		this.capacity = modifiedEquipment.capacity;
		this.coolingType = modifiedEquipment.coolingType;
		this.oilType = modifiedEquipment.oilType;
		this.circle = modifiedEquipment.circle;
		this.division = modifiedEquipment.division;
		this.substation = modifiedEquipment.substation;
		this.dateEntered = modifiedEquipment.dateEntered;
		this.commissioningDate=modifiedEquipment.dateEntered;
		return this;
	}

	public Date getDateEntered() {
		return this.dateEntered;
	}

	public void setDateEntered(final Date dateEntered) {
		this.dateEntered = dateEntered;
	}

	public Circle getCircle() {
		return this.circle;
	}

	public void setCircle(final Circle circle) {
		this.circle = circle;
	}

	public Division getDivision() {
		return this.division;
	}

	public void setDivision(final Division division) {
		this.division = division;
	}

	public Substation getSubstation() {
		return this.substation;
	}

	public void setSubstation(final Substation substation) {
		this.substation = substation;
	}

	public String getMake() {
		return this.make;
	}

	public String getSerialNo() {
		return this.serialNo;
	}

	public void setSerialNo(final String serialNo) {
		this.serialNo = serialNo;
	}

	public String getYearOfMfg() {
		return this.yearOfMfg;
	}

	public void setYearOfMfg(final String yearOfMfg) {
		this.yearOfMfg = yearOfMfg;
	}

	public String getVoltageClass() {
		return this.voltageClass;
	}

	public void setVoltageClass(final String voltageClass) {
		this.voltageClass = voltageClass;
	}

	public String getCapacity() {
		return this.capacity;
	}

	public void setCapacity(final String capacity) {
		this.capacity = capacity;
	}

	public String getCoolingType() {
		return this.coolingType;
	}

	public void setCoolingType(final String coolingType) {
		this.coolingType = coolingType;
	}

	public String getOilType() {
		return this.oilType;
	}

	public void setOilType(final String oilType) {
		this.oilType = oilType;
	}

	public void setMake(final String make) {
		this.make = make;
	}

	public String getEquipmentID() {
		return this.equipmentID;
	}

	public void setEquipmentID(final String equipmentID) {
		this.equipmentID = equipmentID;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	public String getEquipmentType() {
		return this.equipmentType;
	}

	public void setEquipmentType(final String equipmentType) {
		this.equipmentType = equipmentType;
	}
}