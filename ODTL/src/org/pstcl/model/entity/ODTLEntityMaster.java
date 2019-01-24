package org.pstcl.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@MappedSuperclass
public class ODTLEntityMaster {
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_Entered")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dateEntered;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "appUser")
	private User appUser;

	public Date getDateEntered() {
		return dateEntered;
	}

	public void setDateEntered(Date dateEntered) {
		this.dateEntered = dateEntered;
	}

	public User getAppUser() {
		return appUser;
	}

	public void setAppUser(User appUser) {
		this.appUser = appUser;
	}

}
