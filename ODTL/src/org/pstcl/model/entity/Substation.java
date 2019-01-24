package org.pstcl.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "substation_master")
public class Substation {
	@Id
	@Column(name = "SS_CODE")
	private String substationCode;
	@Column(name = "SS_DESC")
	private String substationDescription;
	
	
	

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CR_CODE", nullable = false)
	private Circle circle;
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "DIV_CODE", nullable = false)
	private Division division;

	@Override
	public boolean equals(final Object obj) {
		String circleCode;
		if (obj.getClass().equals(this.getClass())) {
			circleCode = ((Substation) obj).getSubstationCode();
		} else {
			circleCode = (String) obj;
		}
		return circleCode.equals(this.getSubstationCode());
	}

	public String getSubstationCode() {
		return this.substationCode;
	}

	public void setSubstationCode(final String substationCode) {
		this.substationCode = substationCode;
	}

	public String getSubstationDescription() {
		return this.substationDescription;
	}

	public void setSubstationDescription(final String substationDescription) {
		this.substationDescription = substationDescription;
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
}