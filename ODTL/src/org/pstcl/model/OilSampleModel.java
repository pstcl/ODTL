package org.pstcl.model;

import org.pstcl.model.entity.OilSample;

public class OilSampleModel {
private OilSample oilSample;
private ODTLFilterModel filter;
public OilSample getOilSample() {
	return oilSample;
}

public void setOilSample(OilSample oilSample) {
	this.oilSample = oilSample;
}

public ODTLFilterModel getFilter() {
	return filter;
}

public void setFilter(ODTLFilterModel filter) {
	this.filter = filter;
}
}
