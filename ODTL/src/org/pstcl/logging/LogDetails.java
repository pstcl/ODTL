package org.pstcl.logging;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.pstcl.model.entity.User;


@Entity
@Table(name = "log_master")
public class LogDetails{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer iD;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "appUser")
	private User user;
	
	@Column
	private Date timeStamp;
	
	@Column
	private Integer entityID;
	
	@Column
	private String entityAction;
	
	@Column
	private String tableName;
	
	@Column(columnDefinition="text")
	private String objectValueOld;
	
	
	@Column(columnDefinition="text")
	private String objectValueUpdated;
	
	public String getObjectValueOld() {
		return objectValueOld;
	}
	public void setObjectValueOld(String objectValueOld) {
		this.objectValueOld = objectValueOld;
	}
	public String getObjectValueUpdated() {
		return objectValueUpdated;
	}
	public void setObjectValueUpdated(String objectValueUpdated) {
		this.objectValueUpdated = objectValueUpdated;
	}
	public Integer getiD() {
		return iD;
	}
	public void setiD(Integer iD) {
		this.iD = iD;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
	public Date getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public Integer getEntityID() {
		return entityID;
	}
	public void setEntityID(Integer entityID) {
		this.entityID = entityID;
	}
	public String getEntityAction() {
		return entityAction;
	}
	public void setEntityAction(String entityAction) {
		this.entityAction = entityAction;
	}
}
