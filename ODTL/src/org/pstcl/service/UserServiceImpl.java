package org.pstcl.service;

import java.util.Collection;
import java.util.List;

import org.pstcl.dao.IUserDesignationDao;
import org.pstcl.dao.LocationDao;
import org.pstcl.dao.UserDao;
import org.pstcl.model.entity.Circle;
import org.pstcl.model.entity.Division;
import org.pstcl.model.entity.Substation;
import org.pstcl.model.entity.User;
import org.pstcl.model.entity.UserDesignation;
import org.pstcl.model.entity.UserEntityPasswordChange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao dao;
	@Autowired
	private IUserDesignationDao designationDao;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private LocationDao locationDao;

	public List<UserDesignation> findAllDesignations() {
		return this.designationDao.findAllDesignations();
	}

	public UserDesignation findUserDesignation(Integer id) {
		return this.designationDao.findById(id.intValue());
	}

	public User findById(int id) {
		return this.dao.findById(id);
	}

	public User findBySSO(String sso) {
		User user = this.dao.findBySSO(sso);
		return user;
	}

	@Override
	public List<Circle> getCircleList(User user) {
		return this.locationDao.listCircles(user);
	}

	@Override
	public List<Division> getDivisionList(User user) {
		return this.locationDao.listDivisions(user);
	}

	@Override
	public List<Substation> getSubstationList(User user) {
		return this.locationDao.listSubstations(user);
	}


	private String getPrincipal() {
		String userName = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			userName = ((UserDetails) principal).getUsername();
		} else {
			userName = principal.toString();
		}

		return userName;
	}



	public String saveUser(User user) {
		String msg="";
		if(null==user.getId())
		{
			user.setPassword(this.passwordEncoder.encode(user.getPassword()));
			this.dao.save(user,dao.findBySSO(getPrincipal()));

			msg="User " + user.getFirstName() + " " + user.getLastName() + " "+ user.getDesignation().getDesignation() + " added successfully";
		}
		else
		{
			msg=updateUser(user);
		}


		return msg;
	}





	public String updateUser(User user) {
		User entity = this.dao.findById(user.getId());
		String msg="";
		if (entity != null) {
			entity.setSsoId(user.getSsoId());
			if (!user.getPassword().equals(entity.getPassword())) {
				entity.setPassword(this.passwordEncoder.encode(user.getPassword()));
			}
			entity.setFirstName(user.getFirstName());
			entity.setLastName(user.getLastName());
			entity.setEmail(user.getEmail());
			entity.setUserProfiles(user.getUserProfiles());
			//entity.setSeniors(user.getSeniors());
			entity.setDesignation(user.getDesignation());

			entity.setSubstation(locationDao.findSubstationByID(user.getSubstation().getSubstationCode()));
			entity.setCircle(locationDao.findCircleByID(user.getCircle().getCircleCode()));
			entity.setDivision(locationDao.findDivisionByID(user.getDivision().getDivisionCode()));
			entity.setAppUser(dao.findBySSO(getPrincipal()));

			msg="User " + user.getFirstName() + " " + user.getLastName() + " "				+ user.getDesignation().getDesignation() + " updated successfully";

		}
		else
		{
			msg="Updation Error";
		}
		return msg;

	}

	@Override
	public void setLocation(User user) {
		if(null!=user)
		{
			if(null!=user.getSubstation())
			{
				user.setDivision(user.getSubstation().getDivision());
				user.setCircle(user.getSubstation().getCircle());
			}
			else if(null!=user.getDivision())
			{
				user.setCircle(user.getDivision().getCircle());	
			}
		}


	}

	@Override
	public Boolean hasRole(String role) {
		Boolean hasRole=false;
		Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		for (GrantedAuthority grantedAuthority : authorities) {
			if (grantedAuthority.getAuthority().equalsIgnoreCase("ROLE_"+role)) 
			{
				hasRole=true;	
			}
		}
		return hasRole;
	}
	public User getLoggedInUser() {
		User loggedinUser= dao.findBySSO(getPrincipal());
		return loggedinUser;
	}
	public void deleteUserBySSO(String sso) {
		this.dao.deleteBySSO(sso);
	}

	public List<User> findAllUsers() {
		
		//String abc= passwordEncoder.matches(password, "password")?"matches":"bulshit";
		
		List<User> users= this.dao.findAllUsers();
		//=passwordEncoder.matches("password",us.getPassword())?"matches":"bulshit"
		//users.forEach(us->us.setPassword(passwordEncoder.encode("password")));
		
		return users;
	}




	public List<User> findColleagues(User user) {
		List users = this.findAllUsers();
		users.remove(user);
		return users;
	}

	public boolean isUserSSOUnique(Integer id, String sso) {
		User user = this.findBySSO(sso);
		return user == null || id != null && user.getId().equals(id);
	}

	@Override
	public Boolean changePassword(UserEntityPasswordChange eaUserPasswordChange) {


		User eaUser= getLoggedInUser();

		if(eaUserPasswordChange.getUsername().equals(eaUser.getSsoId()))
		{


			eaUser.setPassword(this.passwordEncoder.encode(eaUserPasswordChange.getNewPassword1()));
			// no need to update due to @transactional
			//	dao.update(eaUser);
		}
		else
		{
			return false;
		}
		return true;
	}


	@Override
	public UserEntityPasswordChange getUserForPwdChange(User eaUser) {
		UserEntityPasswordChange eaUserPasswordChange=new UserEntityPasswordChange();
		eaUserPasswordChange.setUsername(eaUser.getSsoId());
		return eaUserPasswordChange;
	}

	@Override
	public boolean isBothNewPasswordsEqual(UserEntityPasswordChange eaUserPasswordChange) {
		return eaUserPasswordChange.getNewPassword1().equals(eaUserPasswordChange.getNewPassword2());
	}

	@Override
	public boolean isUserLoggedIn(UserEntityPasswordChange eaUserPasswordChange) {
		boolean loggedin=false;
		if(eaUserPasswordChange.getUsername().equals(getLoggedInUser().getSsoId()))
		{
			if( passwordEncoder.matches(eaUserPasswordChange.getUserPassword(),getLoggedInUser().getPassword()))
			{
				loggedin=true;
			}
		}
		return loggedin;
			
	}

	@Override
	public boolean isNewPassSameAsOld(UserEntityPasswordChange eaUserPasswordChange) {
		
		//return passwordEncoder.encode(eaUserPasswordChange.getNewPassword1()).equals(getLoggedInUser().getPassword());

		return passwordEncoder.matches(eaUserPasswordChange.getNewPassword1(),getLoggedInUser().getPassword());
		//return false;
	}



}