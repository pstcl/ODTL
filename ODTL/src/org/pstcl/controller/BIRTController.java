package org.pstcl.controller;

import org.pstcl.birt.spring.core.BirtEngineFactory;
import org.pstcl.birt.spring.core.BirtView;
import org.pstcl.birt.spring.odtl.BirtViewOilReport;
import org.pstcl.model.entity.OilReport;
import org.pstcl.service.OilReportService;
import org.pstcl.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping({"/"})
@SessionAttributes({"roles"})
public class BIRTController {

	@Autowired
	OilReportService oilReportService;
	@Autowired
	BirtEngineFactory engine;
	//BirtViewOilReport birtViewOilReport;



	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = { "/pdfOilReport-{id}" }, method = RequestMethod.GET)
	public ModelAndView pdfOilReport(@PathVariable Integer id, ModelMap model) {
		BirtViewOilReport  birtViewOilReport=new BirtViewOilReport();
		birtViewOilReport.setBirtEngine(engine.getObject());
		birtViewOilReport.setReportFormat("pdf");
		prepareReport(id,birtViewOilReport);
		return new ModelAndView(birtViewOilReport);
	}






	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = { "/xlsOilReport-{id}" }, method = RequestMethod.GET)
	public ModelAndView xlsOilReport(@PathVariable Integer id, ModelMap model) {

		BirtViewOilReport  birtViewOilReport=new BirtViewOilReport();
		birtViewOilReport.setBirtEngine(engine.getObject());
		birtViewOilReport.setReportFormat("xlsx");
		prepareReport(id,birtViewOilReport);

		return new ModelAndView(birtViewOilReport);
	}




	private void prepareReport(Integer id,BirtViewOilReport  birtViewOilReport)
	{
		OilReport oilReport=oilReportService.findOilReportById(id);
		birtViewOilReport.setReportID(oilReport.getId());


		if(oilReport.getOilSample().getSampleType().contentEquals(StringUtil.OIL_REPORT_ROUTINE_OIL))
		{
			birtViewOilReport.setReportOutputFileName("ODTL_Circle_"+oilReport.getOilSample().getSampleSentByUser().getCircle().getCircleDescription()+"_Div_"+oilReport.getOilSample().getSampleSentByUser().getDivision().getDivisionDescription()+"_SS_"+oilReport.getOilSample().getSampleSentByUser().getSubstation().getSubstationDescription()+"id"+oilReport.getId());
			birtViewOilReport.setReportName("RoutineOil.rptdesign");
		} 
		else if(oilReport.getOilSample().getSampleType().contentEquals(StringUtil.OIL_REPORT_NEW_OIL))
		{
			birtViewOilReport.setReportOutputFileName("ODTL_Circle_"+oilReport.getOilSample().getSampleSentByUser().getCircle().getCircleDescription()+"_Div_"+oilReport.getOilSample().getSampleSentByUser().getDivision().getDivisionDescription()+"_SS_"+oilReport.getOilSample().getSampleSentByUser().getSubstation().getSubstationDescription()+"id"+oilReport.getId());
			birtViewOilReport.setReportName("NewOil.rptdesign");	
		}
		else if(oilReport.getOilSample().getSampleType().contentEquals(StringUtil.OIL_REPORT_FRESH_OIL))
		{
			birtViewOilReport.setReportOutputFileName("ODTL_Circle_"+oilReport.getOilSample().getSampleSentByUser().getCircle().getCircleDescription()+"_Div_"+oilReport.getOilSample().getSampleSentByUser().getDivision().getDivisionDescription()+"_SS_"+oilReport.getOilSample().getSampleSentByUser().getSubstation().getSubstationDescription()+"id"+oilReport.getId());
			birtViewOilReport.setReportName("RoutineOil.rptdesign");	
		}

	}


	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = { "/previewPdfOilReport-{id}" }, method = RequestMethod.GET)
	public ModelAndView previewPdfOilReport(@PathVariable Integer id, ModelMap model) {
		BirtViewOilReport  birtViewOilReport=new BirtViewOilReport();
		birtViewOilReport.setBirtEngine(engine.getObject());

		birtViewOilReport.setReportFormat("pdf");
		preparePreviewPDFReport(id,birtViewOilReport);

		return new ModelAndView(birtViewOilReport);
	}


	private void preparePreviewPDFReport(Integer id,BirtViewOilReport  birtViewOilReport)
	{
		OilReport oilReport=oilReportService.findOilReportById(id);
		birtViewOilReport.setReportID(oilReport.getId());


		if(oilReport.getOilSample().getSampleType().contentEquals(StringUtil.OIL_REPORT_ROUTINE_OIL))
		{
			birtViewOilReport.setReportName("PreviewRoutineOil.rptdesign");
		} 
		else if(oilReport.getOilSample().getSampleType().contentEquals(StringUtil.OIL_REPORT_NEW_OIL))
		{
			birtViewOilReport.setReportName("PreviewNewOil.rptdesign");	
		}
		else if(oilReport.getOilSample().getSampleType().contentEquals(StringUtil.OIL_REPORT_FRESH_OIL))
		{
			birtViewOilReport.setReportName("PreviewRoutineOil.rptdesign");	
		}

	}




	//	@RequestMapping(value = { "/printOilReport-{id}" }, method = RequestMethod.GET)
	//	public String printOilReport(@PathVariable Integer id, ModelMap model) {
	//		OilReport oilReport=oilReportService.findOilReportById(id);
	//		model.addAttribute("oilReport",oilReport);
	//
	//		if(oilReport.getOilSample().getSampleType().contentEquals(StringUtil.OIL_REPORT_NEW_OIL))
	//		{
	//			return "oilReportFresh";
	//		}
	//		else
	//		{
	//			return "oilReportRoutine";
	//		}
	//
	//	}

}
