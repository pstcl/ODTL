package org.pstcl.converter;

import org.pstcl.model.entity.User;
import org.pstcl.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserConverter implements Converter<String, User> {
	static final Logger logger;
	@Autowired
	UserService userService;

	static {
		logger = LoggerFactory.getLogger((Class) UserConverter.class);
	}

	public User convert(final String id) {
		if (id.trim().equalsIgnoreCase("")) {
			return null;
		}
		final Integer integer = Integer.parseInt(id);
		final User user = this.userService.findById(integer);
		UserConverter.logger.info("Profile : {}",
				(Object) (String.valueOf(user.getFirstName()) + user.getSsoId()));
		return user;
	}
}