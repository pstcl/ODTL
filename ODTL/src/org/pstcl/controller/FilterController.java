package org.pstcl.controller;

import javax.validation.Valid;

import org.pstcl.model.ODTLFilterModel;
import org.pstcl.service.EquipmentService;
import org.pstcl.service.OilReportService;
import org.pstcl.service.OilSampleService;
import org.pstcl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping("/")
@SessionAttributes("roles")
public class FilterController {




	@Autowired
	OilReportService oilReportService;

	@Autowired
	OilSampleService oilSampleService;

	@Autowired
	EquipmentService equipmentService;

	@Autowired
	UserService userService;



	@RequestMapping(value = {"/home" }, method = RequestMethod.GET)
	public String home(ModelMap model) {
		model.addAttribute("loggedinuser",  userService.getLoggedInUser());
		return "home";
	}


	private void prepareModel(ModelMap model,ODTLFilterModel filter)
	{
		model.addAttribute("circleList", equipmentService.getCircleList(filter.getEquipment()));
		model.addAttribute("divisionList", equipmentService.getDivisionList(filter.getEquipment()));
		model.addAttribute("substationList", equipmentService.getSubstationList(filter.getEquipment()));
		model.addAttribute("equipments", equipmentService.getEquipmentsList(filter.getEquipment()));
		model.addAttribute("oilSamples", oilSampleService.findAllOilSamples());
		model.addAttribute("oilReports", oilReportService.findAllOilReports(filter));
		model.addAttribute("filter", filter);
		model.addAttribute("actionName", model.get("actionName"));
		model.addAttribute("loggedinuser",  userService.getLoggedInUser());

	}
	private void prepareModelForReport(ModelMap model,ODTLFilterModel filter)
	{
		model.addAttribute("circleList", equipmentService.getCircleList(filter.getEquipment()));
		model.addAttribute("divisionList", equipmentService.getDivisionList(filter.getEquipment()));
		model.addAttribute("substationList", equipmentService.getSubstationList(filter.getEquipment()));
		//model.addAttribute("equipments", equipmentService.getEquipmentsList(filter.getEquipment()));
		//model.addAttribute("oilSamples", oilSampleService.findAllOilSamples());
		model.addAttribute("oilReports", oilReportService.findAllOilReports(filter));
		model.addAttribute("filter", filter);
		model.addAttribute("actionName", model.get("actionName"));
		model.addAttribute("loggedinuser",  userService.getLoggedInUser());

	}




	
	
	
	


	//	@PreAuthorize("hasRole('ADMIN')")
	//	@RequestMapping(value = {"/listOilSampleNew" }, method = RequestMethod.POST)
	//	public String filterOilSamples(ODTLFilterModel filter, BindingResult result,ModelMap model) {
	//		//ODTLFilterModel filterModel=oilReportService.createODTLFilter();
	//		prepareModelForReport(model, filter);
	//		model.addAttribute("filterOpen", true);
	//
	//		//		List<OilSample> oilSamples=oilSampleService.findAllOilSamples();
	//		//		model.addAttribute("oilSamples", oilSamples);
	//		//		model.addAttribute("loggedinuser", getPrincipal());
	//		return "oilSampleListNew";
	//	}
	//
	//
	//
	//	@PreAuthorize("hasRole('ADMIN')")
	//	@RequestMapping(value = { "/equipmentFilterForReport"  }, method = RequestMethod.POST)
	//	public String equipmentFilterForReport(ODTLFilterModel filter, BindingResult result,
	//			ModelMap model) 
	//	{
	//		filter.setEquipment(equipmentService.findEquipmentById(filter.getEquipment().getId()));
	//		prepareModel(model,filter);
	//		model.addAttribute("loggedinoffice", getPrincipal());
	//		return "oilReportsListNew";
	//	}
	//
	//
	//	@PreAuthorize("hasRole('ADMIN')")
	//	@RequestMapping(value = { "/equipmentFilterForSample"  }, method = RequestMethod.POST)
	//	public String equipmentFilterForSample(ODTLFilterModel filter, BindingResult result,
	//			ModelMap model) 
	//	{
	//		filter.setEquipment(equipmentService.findEquipmentById(filter.getEquipment().getId()));
	//		prepareModel(model,filter);
	//		model.addAttribute("filterOpen", true);
	//		model.addAttribute("loggedinoffice", getPrincipal());
	//		return "oilSampleListNew";
	//	}
	//
	//




	private String getPrincipal()
	{
		return oilReportService.getPrincipal();
	}

	


	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = {"/viewSampleReport{oilSampleId}"}, method = {RequestMethod.GET})
	public String viewSampleReport(@PathVariable  Integer oilSampleId,  ModelMap model) {
		ODTLFilterModel filterModel = new ODTLFilterModel();
		filterModel.setOilSample(oilSampleService.findOilSampleById(oilSampleId));

		prepareModel(model, filterModel);
		return "oilReportsListNew";
	}

}
