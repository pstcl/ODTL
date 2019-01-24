package org.pstcl.converter;

import org.pstcl.model.entity.Equipment;
import org.pstcl.service.EquipmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class EquipmentConverter implements Converter<String, Equipment> {
	static final Logger logger;
	@Autowired
	EquipmentService odtlService;

	static {
		logger = LoggerFactory.getLogger((Class) EquipmentConverter.class);
	}

	public Equipment convert(final String code) {
		EquipmentConverter.logger.info("Profile : {}", (Object) code);
		final Integer integer = Integer.parseInt(code);
		return this.odtlService.findEquipmentById(integer);
	}
}