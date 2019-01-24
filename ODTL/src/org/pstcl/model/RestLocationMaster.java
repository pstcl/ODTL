package org.pstcl.model;

import org.pstcl.model.entity.LocationMaster;

public class RestLocationMaster extends LocationMaster
{

	public RestLocationMaster(FilterModel locationModel) {
		this.circle=locationModel.getSelectedCircle();
		this.division=locationModel.getSelectedDivision();
		this.substation=locationModel.getSelectedSubstation();
	}

}