package org.pstcl.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "circle_master")
public class Circle {
	@Id
	@Column(name = "CR_CODE")
	private String circleCode;
	@Column(name = "CIRCLE_DESC")
	private String circleDescription;

	@Override
	public boolean equals(final Object obj) {
		String circleCode;
		if (obj.getClass().equals(this.getClass())) {
			circleCode = ((Circle) obj).getCircleCode();
		} else {
			circleCode = (String) obj;
		}
		return circleCode.equals(this.getCircleCode());
	}

	public String getCircleCode() {
		return this.circleCode;
	}

	public void setCircleCode(final String circleCode) {
		this.circleCode = circleCode;
	}

	public String getCircleDescription() {
		return this.circleDescription;
	}

	public void setCircleDescription(final String circleDescription) {
		this.circleDescription = circleDescription;
	}
}