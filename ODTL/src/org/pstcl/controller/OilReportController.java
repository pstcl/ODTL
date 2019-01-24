package org.pstcl.controller;

import javax.validation.Valid;

import org.pstcl.model.entity.OilReport;
import org.pstcl.service.EquipmentService;
import org.pstcl.service.OilReportService;
import org.pstcl.service.OilSampleService;
import org.pstcl.service.UserService;
import org.pstcl.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/")
@SessionAttributes("roles")
public class OilReportController {

	@Autowired
	OilReportService oilReportService;

	@Autowired
	OilSampleService oilSampleService;

	@Autowired
	EquipmentService equipmentService;
	@Autowired
	UserService userService;


	@PreAuthorize("hasRole('JE') and hasRole('ODTL')")
	@RequestMapping(value = { "/runTask" }, method = RequestMethod.GET)
	public String runTask(ModelMap model)
	{
		OilReport oilReport=oilReportService.runTask();
		
		return "home";
	}

	
	
	@PreAuthorize("hasRole('JE') and hasRole('ODTL')")
	@RequestMapping(value = { "/addOilReport" }, method = RequestMethod.GET)
	public String addOilReport(ModelMap model)
	{
		OilReport oilReport=oilReportService.createOilReportForSample(null);
		model.addAttribute("oilReport",oilReport);
		prepareModel(model,oilReport);


		return "addOilReport";
	}

	@PreAuthorize("hasRole('JE') and hasRole('ODTL')")
	@RequestMapping(value = { "/newOilReport{id}" }, method = RequestMethod.GET)
	public String newOilReport(@PathVariable Integer id,ModelMap model)
	{
		OilReport oilReport=oilReportService.createOilReportForSample(id);
		model.addAttribute("oilReport",oilReport);
		prepareModel(model,oilReport);
		return "addOilReport";
	}




	private void prepareModel(ModelMap model,OilReport oilReport)
	{
		model.addAttribute("appearanceList", oilReportService.getAppearanceList());
		model.addAttribute("corrosiveSulphurList", oilReportService.getCorrosiveSulphurList());
		model.addAttribute("oxidationInhibitorList", oilReportService.getOxidationInhibitorList());
		model.addAttribute("sampleConditionList", oilReportService.getSampleConditionList());

		model.addAttribute("loggedinuser",  userService.getLoggedInUser());


		model.addAttribute("circleList", equipmentService.getCircleList(oilReport.getEquipment()));
		model.addAttribute("divisionList", equipmentService.getDivisionList(oilReport.getEquipment()));
		model.addAttribute("substationList", equipmentService.getSubstationList(oilReport.getEquipment()));
		model.addAttribute("equipmentList", equipmentService.getEquipmentsList(oilReport.getEquipment()));
		model.addAttribute("oilSampleList", oilSampleService.getPendingOilSamples());

	}

	@PreAuthorize("hasRole('JE') and hasRole('ODTL')")
	@RequestMapping(value = { "/filterForOilReport"  }, method = RequestMethod.POST)
	public String filterForSample( @Valid OilReport oilReport, BindingResult result,
			ModelMap model) 
	{
		prepareModel(model,oilReport);
		model.addAttribute("loggedinoffice", getPrincipal());
		return "addOilReport";
	}

	@PreAuthorize("hasRole('JE') and hasRole('ODTL')")
	@RequestMapping(value = { "/setOilReportEquipment"  }, method = RequestMethod.POST)
	public String setOilReportEquipment( @Valid OilReport oilReport, BindingResult result,
			ModelMap model) 
	{
		oilReport.setEquipment(equipmentService.findEquipmentById(oilReport.getEquipment().getId()));
		prepareModel(model,oilReport);
		model.addAttribute("loggedinoffice", getPrincipal());
		return "addOilReport";
	}

	@PreAuthorize("hasRole('JE') and hasRole('ODTL')")
	@RequestMapping(value = { "/setOilReportDate"  }, method = RequestMethod.POST)
	public String setOilReportDate( @Valid OilReport oilReport, BindingResult result,
			ModelMap model) 
	{
		oilReport.setOilSample(oilSampleService.findOilSampleById(oilReport.getOilSample().getId()));
		oilReport.setEquipment(oilReport.getOilSample().getEquipment());
		oilReportService.setReportNo(oilReport);
		prepareModel(model,oilReport);
		model.addAttribute("loggedinoffice", getPrincipal());
		return "addOilReport";
	}


	@PreAuthorize("hasRole('JE') and hasRole('ODTL')")
	@RequestMapping(value = { "/setEditReportDate"  }, method = RequestMethod.POST)
	public String setEditReportDate( @Valid OilReport oilReport, BindingResult result,
			ModelMap model) 
	{
		oilReport.setOilSample(oilSampleService.findOilSampleById(oilReport.getOilSample().getId()));
		oilReport.setEquipment(oilReport.getOilSample().getEquipment());
		oilReportService.setReportNo(oilReport);
		prepareModel(model,oilReport);
		model.addAttribute("loggedinoffice", getPrincipal());
		return "editOilReport";
	}



	@PreAuthorize("hasRole('JE') and hasRole('ODTL')")
	@RequestMapping(value = { "/setOilReportSample"  }, method = RequestMethod.POST)
	public String setOilReportSample( @Valid OilReport oilReport, BindingResult result,
			ModelMap model) 
	{

		oilReport.setEquipment(oilReport.getOilSample().getEquipment());
		oilReportService.setReportNo(oilReport);
		prepareModel(model,oilReport);
		model.addAttribute("loggedinoffice", getPrincipal());
		return "addOilReport";
	}

	@PreAuthorize("hasRole('JE') and hasRole('ODTL')")
	@RequestMapping(value = { "/edit-oilReport-{id}" }, method = RequestMethod.GET)
	public String editOilReport(@PathVariable Integer id, ModelMap model) {
		OilReport oilReport=oilReportService.findOilReportById(id);
		model.addAttribute("oilReport",oilReport);
		prepareModel(model,oilReport);
		model.addAttribute("edit", true);
		return "editOilReport";
	}
	
	@PreAuthorize("hasRole('ODTL')")
	@RequestMapping(value = { "/oilReport-options-{id}" }, method = RequestMethod.GET)
	public String oilReportOptions(@PathVariable Integer id, ModelMap model) {
		OilReport oilReport=oilReportService.findOilReportById(id);
		model.addAttribute("oilReport",oilReport);
		return "viewOilReport";
	}


	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = { "/previewOilReport-{id}" }, method = RequestMethod.GET)
	public String previewOilReport(@PathVariable Integer id, ModelMap model) {

		OilReport oilReport=oilReportService.findOilReportById(id);
		model.addAttribute("oilReport",oilReport);
		String reportView = null;

		if(oilReport.getOilSample().getSampleType().contentEquals(StringUtil.OIL_REPORT_ROUTINE_OIL))
		{
			reportView="oilReportFrame";
		} 
		else if(oilReport.getOilSample().getSampleType().contentEquals(StringUtil.OIL_REPORT_NEW_OIL))
		{
			reportView="oilReportFrameNew";	
		}
		else if(oilReport.getOilSample().getSampleType().contentEquals(StringUtil.OIL_REPORT_FRESH_OIL))
		{
			reportView="oilReportFrameFresh";	
		}

		return reportView;
	}



	@PreAuthorize("hasRole('JE') and hasRole('ODTL')")
	@RequestMapping(value = { "/saveOilReport"  }, method = RequestMethod.POST)
	public String saveOilReport(@Valid OilReport oilReport, BindingResult result,
			ModelMap model,RedirectAttributes redirectAttributes) {

		if (result.hasErrors()) {

			return "addOilReport";
		}
		oilReport=oilReportService.saveOilReport(oilReport);
		model.addAttribute("oilReport",oilReport);
		//model.clear();
		//redirectAttributes.addAttribute("success", "OilReport " + oilReport.getMemoNo() + " updated successfully");
		//redirectAttributes.addAttribute("loggedinoffice", getPrincipal());
		return "viewOilReport";
	}

	@PreAuthorize("hasRole('JE') and hasRole('ODTL')")
	@RequestMapping(value = { "/updateOilReport"  }, method = RequestMethod.POST)
	public String updateOilReport(@Valid OilReport oilReport, BindingResult result,
			ModelMap model,RedirectAttributes redirectAttributes) {

		if (result.hasErrors()) {

			return "addOilReport";
		}
		oilReport=oilReportService.updateOilReport(oilReport);
		//model.clear();
		//redirectAttributes.addAttribute("success", "OilReport " + oilReport.getMemoNo() + " updated successfully");
		//redirectAttributes.addAttribute("loggedinoffice", getPrincipal());

		//oilReportService.findOilReportById(id);
		model.addAttribute("oilReport",oilReport);


		return "viewOilReport";
	}





	@PreAuthorize("hasRole('AEE') and hasRole('ODTL')")
	@RequestMapping(value = { "/aeeRemarksOilReport{id}" }, method = RequestMethod.GET)
	public String aeeRemarksOilReport(@PathVariable Integer id, ModelMap model) {
		OilReport oilReport=oilReportService.findOilReportById(id);
		model.addAttribute("oilReport",oilReport);
		prepareModel(model,oilReport);
		model.addAttribute("edit", true);
		return "addOilReportOfficerRemarks";
	}


	@PreAuthorize("hasRole('AEE') and hasRole('ODTL')")
	@RequestMapping(value = { "/aeeRemarksOilReport"  }, method = RequestMethod.POST)
	public String saveAEERemarksOilReport(@Valid OilReport oilReport, BindingResult result,
			ModelMap model) {
		if (result.hasErrors()) {
			prepareModel(model,oilReport);
			return "addOilReportOfficerRemarks";
		}
		oilReport=oilReportService.saveAEERemarksOilReport(oilReport);
		model.addAttribute("success", "OilReport " + oilReport.getMemoNo() + ":-AEE remarks updated successfully");
		model.addAttribute("loggedinoffice", getPrincipal());
		model.addAttribute("oilReport",oilReport);

		return "oilReportPreview";
	}


	@PreAuthorize("hasRole('ASE') and hasRole('ODTL')")
	@RequestMapping(value = { "/aseRemarksOilReport{id}" }, method = RequestMethod.GET)
	public String aseRemarksOilReport(@PathVariable Integer id, ModelMap model) {
		OilReport oilReport=oilReportService.findOilReportById(id);
		model.addAttribute("oilReport",oilReport);
		prepareModel(model,oilReport);
		model.addAttribute("edit", true);
		return "addOilReportOfficerRemarks";
	}


	@PreAuthorize("hasRole('ASE') and hasRole('ODTL')")
	@RequestMapping(value = { "/aseRemarksOilReport"  }, method = RequestMethod.POST)
	public String saveASERemarksOilReport(@Valid OilReport oilReport, BindingResult result,
			ModelMap model) {
		if (result.hasErrors()) {
			prepareModel(model,oilReport);
			return "addOilReportOfficerRemarks";
		}
		oilReport=	oilReportService.saveASERemarksOilReport(oilReport);
		model.addAttribute("success", "OilReport " + oilReport.getMemoNo() + ":- ASE Suggestions updated successfully");
		model.addAttribute("loggedinoffice", getPrincipal());
		model.addAttribute("oilReport",oilReport);

		return "viewOilReport";
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
