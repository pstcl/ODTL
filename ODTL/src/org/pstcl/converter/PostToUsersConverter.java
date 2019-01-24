package org.pstcl.converter;

import java.util.ArrayList;
import java.util.List;

import org.pstcl.model.entity.User;
import org.pstcl.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PostToUsersConverter implements Converter<String[], List<User>> {
	static final Logger logger;
	@Autowired
	UserService userService;

	static {
		logger = LoggerFactory.getLogger((Class) PostToUsersConverter.class);
	}

	public List<User> convert(final String[] posts) {
		final List<User> users = new ArrayList<User>();
		for (final String post : posts) {
			final Integer integer = Integer.parseInt(post);
			final User user = this.userService.findById(integer);
			PostToUsersConverter.logger.info("Profile : {}", (Object) user);
			users.add(user);
		}
		return users;
	}
}