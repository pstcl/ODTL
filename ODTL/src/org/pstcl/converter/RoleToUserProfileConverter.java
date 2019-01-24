package org.pstcl.converter;

import org.pstcl.model.entity.UserProfile;
import org.pstcl.service.UserProfileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RoleToUserProfileConverter implements Converter<Object, UserProfile> {
	static final Logger logger;
	@Autowired
	UserProfileService userProfileService;

	static {
		logger = LoggerFactory.getLogger((Class) RoleToUserProfileConverter.class);
	}

	public UserProfile convert(final Object element) {
		final Integer id = Integer.parseInt((String) element);
		final UserProfile profile = this.userProfileService.findById(id);
		RoleToUserProfileConverter.logger.info("Profile : {}", (Object) profile);
		return profile;
	}
}