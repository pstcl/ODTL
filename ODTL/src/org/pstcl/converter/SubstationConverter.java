package org.pstcl.converter;

import org.pstcl.model.entity.Substation;
import org.pstcl.service.EquipmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class SubstationConverter implements Converter<String, Substation> {
	static final Logger logger;
	@Autowired
	EquipmentService odtlService;

	static {
		logger = LoggerFactory.getLogger((Class) SubstationConverter.class);
	}

	public Substation convert(final String code) {
		SubstationConverter.logger.info("Profile : {}", (Object) code);
		return this.odtlService.findSubstationById(code);
	}
}