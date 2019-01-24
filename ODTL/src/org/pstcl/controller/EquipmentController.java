package org.pstcl.controller;

import java.util.Locale;

import javax.validation.Valid;

import org.pstcl.model.entity.Equipment;
import org.pstcl.service.EquipmentService;
import org.pstcl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.lang.UsesSunHttpServer;
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
public class EquipmentController {

	@Autowired
	EquipmentService equipmentService;
	
	@Autowired
	UserService userService;


	@Autowired
	MessageSource messageSource;


	
	

	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value= {"/newEquipment"},method=RequestMethod.GET)
	public String newEquipment(ModelMap model)
	{
		Equipment equipment=new Equipment();
		prepareModel(model,equipment);
		model.addAttribute("equipment",equipment);

		return "addEquipment";
	}

	private void prepareModel(ModelMap model,Equipment equipment)
	{
		model.addAttribute("equipmentTypeList", equipmentService.getEquipmentTypeList());
		model.addAttribute("coolingTypeList", equipmentService.getCoolingTypeList());
		model.addAttribute("oilTypeList", equipmentService.getOilTypeList());
		model.addAttribute("voltageClassTypeList", equipmentService.getVoltageClassList());
		model.addAttribute("loggedinuser",  userService.getLoggedInUser());



		model.addAttribute("circleList", equipmentService.getCircleList(equipment));
		model.addAttribute("divisionList", equipmentService.getDivisionList(equipment));
		model.addAttribute("substationList", equipmentService.getSubstationList(equipment));
	}
	
	

	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = { "/edit-equipment-{id}" }, method = RequestMethod.GET)
	public String editEquipment(@PathVariable Integer id, ModelMap model) {
		Equipment equipment=equipmentService.findEquipmentById(id);
		model.addAttribute("equipment",equipment);
		prepareModel(model,null);
		model.addAttribute("edit", true);
		return "addEquipment";
	}
	
	

	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = { "/viewEquipment-{id}" }, method = RequestMethod.GET)
	public String viewEquipment(@PathVariable Integer id, ModelMap model) {
		Equipment equipment=equipmentService.findEquipmentById(id);
		model.addAttribute("equipment",equipment);
		prepareModel(model,null);
		model.addAttribute("edit", true);
		return "equipment";
	}

	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = { "/saveEquipment"  }, method = RequestMethod.POST)
	public String saveEquipment(@Valid Equipment equipment, BindingResult result,
			ModelMap model) 
	
	{
		if (result.hasErrors()) {
			prepareModel(model,equipment);
			return "addEquipment";
		}

		//	Equipment ID shall be unique in combination �Division+Sub-station+Equipment ID�
		//	Equipment Serial No. shall be unique in combination �Division+Sub-station+Equipment ID+Serial No.�


		if(!equipmentService.isEquipmentIDUniqueForSS(equipment)){
			FieldError equipIdError =new FieldError("equipment","equipmentID",messageSource.getMessage("non.unique.equipment.ID", new String[]{equipment.getEquipmentID(),equipment.getSubstation().getSubstationDescription()}, Locale.getDefault()));
			result.addError(equipIdError);
			prepareModel(model,equipment);
			return "addEquipment";
		}
		
		
		if(!equipmentService.isEquipmentSNoValidForSS(equipment))
		{
			FieldError equipIdError =new FieldError("equipment","serialNo",messageSource.getMessage("non.unique.equipment.Sno", new String[]{equipment.getSerialNo()}, Locale.getDefault()));
			result.addError(equipIdError);
			prepareModel(model,equipment);
			return "addEquipment";
		}
		String msg= equipmentService.saveEquipment(equipment);
		model.clear();
		model.addAttribute("success", msg);
		model.addAttribute("loggedinoffice", getPrincipal());
		return "redirect:/home";
	}

	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = { "/setLocation"  }, method = RequestMethod.POST)
	public String setLocation(@Valid Equipment equipment, BindingResult result,
			ModelMap model) {
		equipmentService.setLocation(equipment);
		prepareModel(model,equipment);

		model.addAttribute("loggedinoffice", getPrincipal());

		return "addEquipment";
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
