package org.pstcl.converter;

import org.pstcl.model.entity.Circle;
import org.pstcl.service.EquipmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CircleConverter implements Converter<String, Circle> {
	static final Logger logger;
	@Autowired
	EquipmentService odtlService;

	static {
		logger = LoggerFactory.getLogger((Class) CircleConverter.class);
	}

	public Circle convert(final String code) {
		CircleConverter.logger.info("Profile : {}", (Object) code);
		return this.odtlService.findCircleById(code);
	}
}