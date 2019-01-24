package org.pstcl.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_designation_master")
public class UserDesignation implements Serializable {
	@Column
	private String designation;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer designationCode;
	@Column
	private Integer designationLevel;

	public String getDesignation() {
		return this.designation;
	}

	public Integer getDesignationCode() {
		return this.designationCode;
	}

	public Integer getDesignationLevel() {
		return this.designationLevel;
	}

	public String getLogDetails() {
		return "Designation [designationCode=" + this.designationCode + ", designationLevel=" + this.designationLevel
				+ ", designation=" + this.designation + "]";
	}

	public void setDesignation(final String designation) {
		this.designation = designation;
	}

	public void setDesignationCode(final Integer designationCode) {
		this.designationCode = designationCode;
	}

	public void setDesignationLevel(final Integer designationLevel) {
		this.designationLevel = designationLevel;
	}

	@Override
	public String toString() {
		return new StringBuilder().append(this.designationCode).toString();
	}
}