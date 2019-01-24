package org.pstcl.controller;

import java.util.Locale;

import javax.validation.Valid;

import org.pstcl.model.entity.Equipment;
import org.pstcl.model.entity.OilSample;
import org.pstcl.model.entity.User;
import org.pstcl.service.EquipmentService;
import org.pstcl.service.OilSampleService;
import org.pstcl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping("/")
@SessionAttributes("roles")
public class OilSampleController {

	@Autowired
	EquipmentService equipmentService;

	@Autowired
	OilSampleService oilSampleService;

	@Autowired
	MessageSource messageSource;

	@Autowired
	UserService userService;
	/**
	 * This method will list all existing users.
	 */


	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = { "/oilSampleRoutine{id}" }, method = RequestMethod.GET)
	public String oilSampleRoutine(@PathVariable Integer id,ModelMap model)
	{
		OilSample oilSample=oilSampleService.createOilSampleForRoutineOil(id);
		prepareModel(model, oilSample);

		model.addAttribute("oilSample",oilSample);
		return "addOilSample";
	}

	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = { "/addOilSampleRoutine" }, method = RequestMethod.GET)
	public String oilSampleRoutine(ModelMap model)
	{
		OilSample oilSample=oilSampleService.createOilSampleForRoutineOil(null);
		prepareModel(model, oilSample);
		model.addAttribute("oilSample",oilSample);
		return "addOilSample";
	}

	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = { "/addNewOilSample" }, method = RequestMethod.GET)
	public String addNewOilSample(ModelMap model)
	{
		OilSample oilSample=oilSampleService.createOilSampleForNewOil();
		equipmentService.setLocation(oilSample.getEquipment());
		prepareModel(model, oilSample);
		model.addAttribute("oilSample",oilSample);
		return "addOilSample";
	}
	
	
	
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = { "/addFreshOilSample" }, method = RequestMethod.GET)
	public String addFreshOilSample(ModelMap model)
	{
		OilSample oilSample=oilSampleService.createOilSampleForFreshOil();
		equipmentService.setLocation(oilSample.getEquipment());
		prepareModel(model, oilSample);
		model.addAttribute("oilSample",oilSample);
		return "addOilSample";
	}

	private void prepareModel(ModelMap model,OilSample oilSample)

	{
		Equipment equipment=oilSample.getEquipment();
		User user=oilSample.getSampleSentByUser();
		model.addAttribute("sampleTakenFromList", oilSampleService.getSampleTakenFromList());
		model.addAttribute("samplingValveList", oilSampleService.getSamplingValveList());
		model.addAttribute("sampleContainerList", oilSampleService.getSampleContainerList());
		//model.addAttribute("sampleConditionList", oilSampleService.getSampleConditionList());
		model.addAttribute("senderlist", oilSampleService.prepareSampleSenderList(oilSample));
		model.addAttribute("loggedinuser",  userService.getLoggedInUser());


		model.addAttribute("circleList", equipmentService.getCircleList(equipment));
		model.addAttribute("divisionList", equipmentService.getDivisionList(equipment));
		model.addAttribute("substationList", equipmentService.getSubstationList(equipment));
		model.addAttribute("equipmentList", equipmentService.getEquipmentsList(equipment));


		model.addAttribute("circleListForSender", userService.getCircleList(user));
		model.addAttribute("divisionListForSender", userService.getDivisionList(user));
		model.addAttribute("substationListForSender", userService.getSubstationList(user));
		
	}


	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = { "/filterForSample"  }, method = RequestMethod.POST)
	public String filterForSample(@Valid OilSample oilSample, BindingResult result,
			ModelMap model) {
		equipmentService.setLocation(oilSample.getEquipment());
		prepareModel(model, oilSample);


		model.addAttribute("oilSample",oilSample);
		model.addAttribute("loggedinoffice", getPrincipal());
		return "addOilSample";
	}

	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = { "/setSampleEquipment"  }, method = RequestMethod.POST)
	public String setSampleEquipment(@Valid OilSample oilSample, BindingResult result,
			ModelMap model) {
		//needed because circle,division and substation from form nullify/modify equipment location due to binding
		//oilSample.setEquipment(equipmentService.findEquipmentById(oilSample.getEquipment().getId()));

		oilSampleService.setEquipment(oilSample);

		prepareModel(model, oilSample);

		model.addAttribute("oilSample",oilSample);
		model.addAttribute("loggedinoffice", getPrincipal());
		return "addOilSample";
	}

	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = { "/setSampleUser"  }, method = RequestMethod.POST)
	public String setSampleUser(@Valid OilSample oilSample, BindingResult result,
			ModelMap model) {
	
		userService.setLocation(oilSample.getSampleSentByUser());
		oilSampleService.setSampleSender(oilSample);
		prepareModel(model, oilSample);

		model.addAttribute("oilSample",oilSample);
		model.addAttribute("loggedinoffice", getPrincipal());
		return "addOilSample";
	}



	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = { "/edit-oilSample-{id}" }, method = RequestMethod.GET)
	public String editOffice(@PathVariable Integer id, ModelMap model) {
		OilSample oilSample=oilSampleService.findOilSampleById(id);
		prepareModel(model, oilSample);
		model.addAttribute("oilSample",oilSample);
		model.addAttribute("edit", true);
		return "addOilSample";
	}


	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = { "/viewSample-{id}" }, method = RequestMethod.GET)
	public String viewSample(@PathVariable Integer id, ModelMap model) {
		OilSample oilSample=oilSampleService.findOilSampleById(id);
		model.addAttribute("oilSample",oilSample);
		return "oilSample";
	}




	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = { "/saveOilSample"  }, method = RequestMethod.POST)
	public String saveOilSample(@Valid OilSample oilSample, BindingResult result,
			ModelMap model) {

		if (result.hasErrors()) {
			prepareModel(model, oilSample);
			System.out.println(result.toString());
			return "addOilSample";
		}
		if(!oilSampleService.isEquipmentValid(oilSample)){
			FieldError sampleEntryError =new FieldError("oilSample","equipment",messageSource.getMessage("NotEmpty.equipment", new String[]{""}, Locale.getDefault()));
			result.addError(sampleEntryError);
			prepareModel(model, oilSample);
			System.out.println(result.toString());
			return "addOilSample";
		}
		if(!oilSampleService.isSampleNoUnique(oilSample)){
			FieldError sampleEntryError =new FieldError("oilSample","sampleNo",messageSource.getMessage("non.unique.oilSampleNo", new String[]{oilSample.getSampleNo().toString(),oilSampleService.getEquipmentLabelForSample(oilSample.getSampleNo())}, Locale.getDefault()));
			result.addError(sampleEntryError);
			prepareModel(model, oilSample);
			System.out.println(result.toString());
			return "addOilSample";
		}
		if(!oilSampleService.isSampleUserValid(oilSample)){
			FieldError sampleEntryError =new FieldError("oilSample","sampleSentByUser",messageSource.getMessage("NotEmpty.oilsample.sampleSentByUser", new String[]{""}, Locale.getDefault()));
			result.addError(sampleEntryError);
			prepareModel(model, oilSample);
			System.out.println(result.toString());
			return "addOilSample";
		}
		oilSampleService.saveOilSample(oilSample);
		model.clear();
		model.addAttribute("success", "OilSample " + oilSample.getSampleNo()+ " updated successfully");
		model.addAttribute("loggedinoffice", getPrincipal());
		return "redirect:/listOilSampleNew";
	}








	/**
	 * This method returns the principal[user-name] of logged-in user.
	 */
	private String getPrincipal(){
		String userName = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			userName = ((UserDetails)principal).getUsername();
		} else {
			userName = principal.toString();
		}
		return userName;
	}
}
