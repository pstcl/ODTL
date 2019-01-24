package org.pstcl.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "division_master")
public class Division {
	@Id
	@Column(name = "DIV_CODE")
	private String divisionCode;
	@Column(name = "DIV_DESC")
	private String divisionDescription;
	
	@Column(name = "DIV_FILE")
	private String divisionFileNo;
	
	
	public String getDivisionFileNo() {
		return divisionFileNo;
	}

	public void setDivisionFileNo(String divisionFileNo) {
		this.divisionFileNo = divisionFileNo;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CR_CODE", nullable = false)
	private Circle circle;

	@Override
	public boolean equals(final Object obj) {
		String circleCode;
		if (obj.getClass().equals(this.getClass())) {
			circleCode = ((Division) obj).getDivisionCode();
		} else {
			circleCode = (String) obj;
		}
		return circleCode.equals(this.getDivisionCode());
	}

	public Circle getCircle() {
		return this.circle;
	}

	public void setCircle(final Circle circle) {
		this.circle = circle;
	}

	public String getDivisionCode() {
		return this.divisionCode;
	}

	public void setDivisionCode(final String divisionCode) {
		this.divisionCode = divisionCode;
	}

	public String getDivisionDescription() {
		return this.divisionDescription;
	}

	public void setDivisionDescription(final String divisionDescription) {
		this.divisionDescription = divisionDescription;
	}
}