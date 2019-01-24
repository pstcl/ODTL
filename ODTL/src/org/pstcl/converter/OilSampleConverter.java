package org.pstcl.converter;

import org.pstcl.model.entity.OilSample;
import org.pstcl.service.OilSampleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class OilSampleConverter implements Converter<String, OilSample> {
	static final Logger logger;
	@Autowired
	OilSampleService oilSampleService;

	static {
		logger = LoggerFactory.getLogger((Class) OilSampleConverter.class);
	}

	public OilSample convert(final String code) {
		OilSampleConverter.logger.info("Profile : {}", (Object) code);
		final Integer integer = Integer.parseInt(code);
		return this.oilSampleService.findOilSampleById(integer);
	}
}