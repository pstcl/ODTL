package org.pstcl.service;

import java.util.List;

import org.pstcl.model.entity.Circle;
import org.pstcl.model.entity.Division;
import org.pstcl.model.entity.Equipment;
import org.pstcl.model.entity.Substation;
import org.pstcl.model.entity.User;
import org.pstcl.model.entity.UserDesignation;
import org.pstcl.model.entity.UserEntityPasswordChange;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;

public interface UserService {
	void deleteUserBySSO(String arg0);

	List<UserDesignation> findAllDesignations();

	List<User> findAllUsers();



	User findById(int arg0);

	User findBySSO(String arg0);

	List<User> findColleagues(User arg0);

	UserDesignation findUserDesignation(Integer arg0);

	List<Circle> getCircleList(User user);

	List<Division> getDivisionList(User user);

	List<Substation> getSubstationList(User user);

	boolean isUserSSOUnique(Integer arg0, String arg1);


	String saveUser(User arg0);

	void setLocation(User equipment);

	String updateUser(User arg0);

	Boolean hasRole(String role);

	User getLoggedInUser();

	public Boolean changePassword(UserEntityPasswordChange eaUserPasswordChange);
	public UserEntityPasswordChange getUserForPwdChange(User eaUser) ;

	boolean isBothNewPasswordsEqual(UserEntityPasswordChange eaUserPasswordChange);

	boolean isUserLoggedIn(UserEntityPasswordChange eaUserPasswordChange);

	boolean isNewPassSameAsOld(UserEntityPasswordChange eaUserPasswordChange);

}