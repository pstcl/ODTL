package org.pstcl.model.entity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

@Entity
@Table(name = "app_user")
public class User extends LocationMaster implements Comparable<User> {
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "designation", nullable = false)
	private UserDesignation designation;



	@NotEmpty
	@Column(name = "EMAIL", nullable = false)
	private String email;

	@NotEmpty
	@Column(name = "FIRST_NAME", nullable = false)
	private String firstName;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;


	//	@ManyToMany(fetch = FetchType.EAGER)
	//	@JoinTable(name = "app_user_hierachy", joinColumns = {@JoinColumn(name = "seniorId")}, inverseJoinColumns = {
	//			@JoinColumn(name = "empId")})
	//	private List<User> juniors;
	//	

	@NotEmpty
	@Column(name = "LAST_NAME", nullable = false)
	private String lastName;


	@NotEmpty
	@Column(name = "PASSWORD", nullable = false)
	private String password;

	@Transient
	public String abc;
	public String getPswrdenc()
	{
		//String abc= passwordEncoder.matches(password, "password")?"matches":"bulshit";
		return abc;
	}
	//	
	//	@ManyToMany(fetch = FetchType.EAGER)
	//	@JoinTable(name = "app_user_hierachy", joinColumns = {@JoinColumn(name = "empId")}, inverseJoinColumns = {
	//			@JoinColumn(name = "seniorId")})
	//	private List<User> seniors;
	//	

	@NotEmpty
	@Column(name = "SSO_ID", unique = true, nullable = false)
	private String ssoId;


	@NotEmpty
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "app_user_user_profile", joinColumns = {@JoinColumn(name = "USER_ID")}, inverseJoinColumns = {
			@JoinColumn(name = "USER_PROFILE_ID")})
	private Set<UserProfile> userProfiles;

	public User() {
		this.userProfiles = new HashSet<UserProfile>();
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof User)) {
			return false;
		}
		final User other = (User) obj;
		if (this.id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!this.id.equals(other.id)) {
			return false;
		}
		if (this.ssoId == null) {
			if (other.ssoId != null) {
				return false;
			}
		} else if (!this.ssoId.equals(other.ssoId)) {
			return false;
		}
		return true;
	}

	public UserDesignation getDesignation() {
		return this.designation;
	}

	public String getEmail() {
		return this.email;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public Integer getId() {
		return this.id;
	}

	//	public List<User> getJuniors() {
	//		return this.juniors;
	//	}

	public String getLastName() {
		return this.lastName;
	}
	public String getLabelForSample()
	{
		return firstName+" "+lastName;
	}


	public String getPassword() {
		return this.password;
	}

	//	public List<User> getSeniors() {
	//		return this.seniors;
	//	}

	public String getSsoId() {
		return this.ssoId;
	}

	public Set<UserProfile> getUserProfiles() {
		return this.userProfiles;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = 31 * result + ((this.id == null) ? 0 : this.id.hashCode());
		result = 31 * result + ((this.ssoId == null) ? 0 : this.ssoId.hashCode());
		return result;
	}

	public void setDesignation(final UserDesignation designation) {
		this.designation = designation;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	//	public void setJuniors(final List<User> juniors) {
	//		this.juniors = juniors;
	//	}

	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

	//	public void setSeniors(final List<User> seniors) {
	//		this.seniors = seniors;
	//	}

	public void setSsoId(final String ssoId) {
		this.ssoId = ssoId;
	}

	public void setUserProfiles(final Set<UserProfile> userProfiles) {
		this.userProfiles = userProfiles;
	}

	@Override
	public String toString() {
		return this.ssoId;
	}

	@Override
	public int compareTo(User o) {
		if(designation.getDesignationLevel()>o.getDesignation().getDesignationLevel())
		{
			return 1;
		}
		else if(designation.getDesignationLevel()==o.getDesignation().getDesignationLevel())
		{
			return 0;
		}
		else
		{
			return -1;
		}
	}
}