package org.pstcl.controller;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.pstcl.model.entity.User;
import org.pstcl.model.entity.UserEntityPasswordChange;
import org.pstcl.model.entity.UserProfile;
import org.pstcl.service.UserProfileService;
import org.pstcl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping({"/"})
@SessionAttributes({"roles"})
public class AppController {
	
	
	@Autowired
	UserService userService;
	@Autowired
	UserProfileService userProfileService;
	@Autowired
	MessageSource messageSource;
	@Autowired
	PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices;
	@Autowired
	AuthenticationTrustResolver authenticationTrustResolver;
	

	@PreAuthorize("hasRole('ODTL')")
	@RequestMapping(value = {"/", "/list"}, method = {RequestMethod.GET})
	public String listUsers(final ModelMap model) {
		final List<User> users = this.userService.findAllUsers();
		model.addAttribute("users",  users);
		model.addAttribute("loggedinuser",  userService.getLoggedInUser());
		return "userslist";
	}
	
	
	@RequestMapping(value = { "/testSMS"}, method = {RequestMethod.GET})
	public String testSMS(final ModelMap model,HttpServletRequest httpServletRequest) {
		List<String> paramnames= (List<String>) httpServletRequest.getParameterNames();
		
		for (String string : paramnames) {
			System.out.println(string+"-----------"+ httpServletRequest.getParameter(string));
		}
		
		return "abc";
	}
	
	
	private void prepareModel(ModelMap model,User equipment)
	{
		
		model.addAttribute("loggedinuser",  userService.getLoggedInUser());


		model.addAttribute("circleList", userService.getCircleList(equipment));
		model.addAttribute("divisionList", userService.getDivisionList(equipment));
		model.addAttribute("substationList", userService.getSubstationList(equipment));
		model.addAttribute("colleagues",  this.userService.findAllUsers());
		model.addAttribute("designationList",  this.userService.findAllDesignations());
	}
	
	@PreAuthorize("hasRole('ODTL')") 
	@RequestMapping(value = { "/setLocationForUser"  }, method = RequestMethod.POST)
	public String setLocation(@Valid final User user, final BindingResult result, final ModelMap model) {
		userService.setLocation(user);

		prepareModel(model,user);
		
		model.addAttribute("loggedinoffice", getPrincipal());

		return "registration";
	}

	@PreAuthorize("hasRole('ODTL')")
	@RequestMapping(value = {"/newuser"}, method = {RequestMethod.GET})
	public String newUser(final ModelMap model) {
		final User user = new User();
		prepareModel(model,user);
		model.addAttribute("user",  user);
		model.addAttribute("edit",  false);
		model.addAttribute("colleagues",  this.userService.findAllUsers());
		model.addAttribute("designationList",  this.userService.findAllDesignations());
		model.addAttribute("loggedinuser",  userService.getLoggedInUser());
		return "registration";
	}
	
	@RequestMapping(value = {"/header"}, method = {RequestMethod.GET})
	public String header(final ModelMap model) {
		return "header";
	}

	@PreAuthorize("hasRole('ODTL')")
	@RequestMapping(value = {"/saveUser"}, method = {RequestMethod.POST})
	public String saveUser(@Valid final User user, final BindingResult result, final ModelMap model) {
		if (result.hasErrors()) {
			prepareModel(model,user);
			return "registration";
		}
		if (!this.userService.isUserSSOUnique(user.getId(), user.getSsoId())) {
			final FieldError ssoError = new FieldError("user", "ssoId", this.messageSource
					.getMessage("non.unique.ssoId", (Object[]) new String[]{user.getSsoId()}, Locale.getDefault()));
			result.addError((ObjectError) ssoError);
			prepareModel(model,user);
			return "registration";
		}
		String msg= this.userService.saveUser(user);
		model.clear();
		model.addAttribute("success",  msg);
		model.addAttribute("loggedinuser",  userService.getLoggedInUser());
		this.listUsers(model);
		return "userslist";
	}
	
	
	@PreAuthorize("hasRole('ODTL')")
	@RequestMapping(value = {"/edit-user-{ssoId}"}, method = {RequestMethod.GET})
	public String editUser(@PathVariable final String ssoId, final ModelMap model) {
		final User user = this.userService.findBySSO(ssoId);
		prepareModel(model,user);
		model.addAttribute("user",  user);
		model.addAttribute("edit",  true);
		model.addAttribute("loggedinuser",  userService.getLoggedInUser());
		prepareModel(model,user);
		return "registration";
	}

	

	@PreAuthorize("hasRole('ODTL')")
	@RequestMapping(value = {"/delete-user-{ssoId}"}, method = {RequestMethod.GET})
	public String deleteUser(@PathVariable final String ssoId) {
		this.userService.deleteUserBySSO(ssoId);
		return "redirect:/list";
	}

	@ModelAttribute("roles")
	public List<UserProfile> initializeProfiles() {
		return this.userProfileService.findAll();
	}

	@RequestMapping(value = {"/Access_Denied"}, method = {RequestMethod.GET})
	public String accessDeniedPage(final ModelMap model) {
		model.addAttribute("loggedinuser",  userService.getLoggedInUser());
		return "accessDenied";
	}

	@RequestMapping(value = {"/login"}, method = {RequestMethod.GET})
	public String loginPage() {
		if (this.isCurrentAuthenticationAnonymous()) {
			return "login";
		}
		return "redirect:/home";
	}
	
	
	



	@RequestMapping(value = {"/firstLoginPwdChange"}, method = {RequestMethod.GET})
	public String firstLoginPwdChange(final ModelMap model) {

		model.addAttribute("currentUser", userService.getLoggedInUser());
		model.addAttribute("passwordEntity", userService.getUserForPwdChange(userService.getLoggedInUser()) );
		return "firstLoginPwdChange";



	}


	//Save all meters data
	@RequestMapping(value = {"/firstLoginPwdChange"}, method = {RequestMethod.POST})
	public String firstLoginPwdChange(@ModelAttribute("passwordEntity") @Valid UserEntityPasswordChange eaUserPasswordChange, BindingResult result, ModelMap model,final HttpServletRequest request, final HttpServletResponse response) {

		
		model.addAttribute("passwordEntity", eaUserPasswordChange );
		if(userService.isBothNewPasswordsEqual(eaUserPasswordChange))
		{
			if(!userService.isUserLoggedIn(eaUserPasswordChange))
			{
				FieldError equipIdError =new FieldError("passwordEntity","username",messageSource.getMessage("odtl.username.error",new String[]{ eaUserPasswordChange.getUsername()}, Locale.getDefault()));
				result.addError(equipIdError);
				return "firstLoginPwdChange";
			}
			if(userService.isNewPassSameAsOld(eaUserPasswordChange))
			//if(eaUserPasswordChange.getUserPassword().equals(eaUserPasswordChange.getNewPassword2()))
			{
				FieldError equipIdError =new FieldError("passwordEntity","newPassword1",messageSource.getMessage("odtl.password.same.old", new String[]{ eaUserPasswordChange.getUsername()}, Locale.getDefault()));
				result.addError(equipIdError);
				return "firstLoginPwdChange";
			
			}
			if(!userService.changePassword(eaUserPasswordChange))
			{
				
				FieldError equipIdError =new FieldError("passwordEntity","username",messageSource.getMessage("odtl.password.error", new String[]{ eaUserPasswordChange.getUsername()}, Locale.getDefault()));
				result.addError(equipIdError);
				equipIdError =new FieldError("passwordEntity","userPassword",messageSource.getMessage("odtl.password.error", new String[]{ eaUserPasswordChange.getUserPassword()}, Locale.getDefault()));
				result.addError(equipIdError);
				model.addAttribute("passwordEntity", userService.getUserForPwdChange(userService.getLoggedInUser()) );
				return "firstLoginPwdChange";
			}
			else
			{
				
				model.addAttribute("passwordChanged", "Password Changed");
			}
		}
		
		else if(!userService.isBothNewPasswordsEqual(eaUserPasswordChange))
		{
			FieldError equipIdError =new FieldError("passwordEntity","newPassword1",messageSource.getMessage("odtl.password.error", new String[]{ eaUserPasswordChange.getNewPassword2()}, Locale.getDefault()));
			result.addError(equipIdError);
			return "firstLoginPwdChange";
		
		}
		 loguserout(request,response);
		 return "redirect:/login?passwordChanged";
	}

	
	
	
	
	
	
	



	@RequestMapping(value = {"/logout"}, method = {RequestMethod.GET})
	public String logoutPage(final HttpServletRequest request, final HttpServletResponse response) {
		loguserout(request, response);
		return "redirect:/login?logout";
	}


	private void loguserout(final HttpServletRequest request, final HttpServletResponse response) {
		final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			this.persistentTokenBasedRememberMeServices.logout(request, response, auth);
			SecurityContextHolder.getContext().setAuthentication((Authentication) null);
		}
	}

	private String getPrincipal() {
		String userName = null;
		final Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			userName = ((UserDetails) principal).getUsername();
		} else {
			userName = principal.toString();
		}
		return userName;
	}

	private boolean isCurrentAuthenticationAnonymous() {
		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return this.authenticationTrustResolver.isAnonymous(authentication);
	}
}