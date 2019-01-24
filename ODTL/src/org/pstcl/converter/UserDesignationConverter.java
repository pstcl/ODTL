package org.pstcl.converter;

import org.pstcl.model.entity.UserDesignation;
import org.pstcl.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserDesignationConverter implements Converter<String, UserDesignation> {
	static final Logger logger;
	@Autowired
	UserService userService;

	static {
		logger = LoggerFactory.getLogger((Class) UserDesignationConverter.class);
	}

	public UserDesignation convert(final String post) {
		final Integer integer = Integer.parseInt(post);
		final UserDesignation userDesignation = this.userService.findUserDesignation(integer);
		UserDesignationConverter.logger.info("Desiignation : {}", (Object) post);
		return userDesignation;
	}
}