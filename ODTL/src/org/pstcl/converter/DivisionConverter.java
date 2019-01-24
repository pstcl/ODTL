package org.pstcl.converter;

import org.pstcl.model.entity.Division;
import org.pstcl.service.EquipmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DivisionConverter implements Converter<String, Division> {
	static final Logger logger;
	@Autowired
	EquipmentService odtlService;

	static {
		logger = LoggerFactory.getLogger((Class) DivisionConverter.class);
	}

	public Division convert(final String code) {
		DivisionConverter.logger.info("Profile : {}", (Object) code);
		return this.odtlService.findDivisionById(code);
	}
}