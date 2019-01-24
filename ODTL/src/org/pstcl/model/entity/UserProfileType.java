package org.pstcl.model.entity;

import java.io.Serializable;

public enum UserProfileType implements Serializable {
	
	ADMIN("ADMIN", 1, "ADMIN"),
	ODTL("ODTL",2,"ODTL"),
	GRID("GRID",3,"GRID"),
	SSD("SSD",4,"SSD"),
	JE("JE", 6, "JE"),
	AEE("AEE", 7, "AEE"), 
	ASE("ASE", 8,"ASE"),
	MGMT("MGMT", 9,"MGMT")
	;
	
	

	String userProfileType;
	String userProfileLabel;

	private UserProfileType(final String s, final int n, final String userProfileType) {
		this.userProfileType = userProfileType;
		this.userProfileLabel=s;
	}

	public String getUserProfileType() {
		return this.userProfileType;
	}

	public String getUserProfileLabel() {
		return this.userProfileLabel;
	}
}