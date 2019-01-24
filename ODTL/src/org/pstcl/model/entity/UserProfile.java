package org.pstcl.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_profile")
public class UserProfile implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "TYPE", length = 15, unique = true, nullable = false)
	private String type;
	
	@Column(name = "LABEL", length = 15, unique = true, nullable = false)
	private String label;

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public UserProfile() {
		
		this.type = UserProfileType.ADMIN.getUserProfileType();
		this.label=UserProfileType.ADMIN.getUserProfileLabel();
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof UserProfile)) {
			return false;
		}
		final UserProfile other = (UserProfile) obj;
		if (this.id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!this.id.equals(other.id)) {
			return false;
		}
		if (this.type == null) {
			if (other.type != null) {
				return false;
			}
		} else if (!this.type.equals(other.type)) {
			return false;
		}
		return true;
	}

	public Integer getId() {
		return this.id;
	}

	public String getType() {
		return this.type;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = 31 * result + ((this.id == null) ? 0 : this.id.hashCode());
		result = 31 * result + ((this.type == null) ? 0 : this.type.hashCode());
		return result;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	public void setType(final String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "UserProfile [id=" + this.id + ", type=" + this.type + "]";
	}
}