package org.pstcl.controller;


import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.pstcl.model.EquipmentFilterModel;
import org.pstcl.model.FilterModel;
import org.pstcl.model.ODTLFilterModel;
import org.pstcl.model.OilReportFilterModel;
import org.pstcl.model.OilSampleFilterModel;
import org.pstcl.model.entity.Circle;
import org.pstcl.model.entity.Division;
import org.pstcl.model.entity.ReportFile;
import org.pstcl.model.entity.Substation;
import org.pstcl.service.RestService;
import org.pstcl.service.UserService;
import org.pstcl.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ibm.icu.text.DateFormat;
import com.ibm.icu.text.SimpleDateFormat;
import com.oracle.jrockit.jfr.ContentType;

@Controller
public class RestController {
	@Autowired
	private RestService restService;


	@Autowired
	UserService userService;




	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = {"/approveOilReports"}, method = RequestMethod.GET)
	public String approveOilReports(ModelMap model) {
		//ODTLFilterModel filterModel=oilReportService.createODTLFilter();
		//prepareModel(model, filterModel);
		model.addAttribute("aeeApproval", false);
		model.addAttribute("finalReport", false);
		model.addAttribute("rejectedReport", false);
		return "oilReportsListNew";
	}

	
	
	
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = {"/listOilReport","/listOilReportNew" }, method = RequestMethod.GET)
	public String listOilReport(ModelMap model) {
		//ODTLFilterModel filterModel=oilReportService.createODTLFilter();
		//prepareModel(model, filterModel);
		model.addAttribute("aeeApproval", false);
		model.addAttribute("finalReport", false);
		model.addAttribute("rejectedReport", false);
		return "oilReportsListNew";
	}

	@PreAuthorize("hasRole('ODTL')")
	@RequestMapping(value = {"/listOilReportPublished" }, method = RequestMethod.GET)
	public String listOilReportPublished(ModelMap model) {
		model.addAttribute("finalReport", true);
		model.addAttribute("rejectedReport", false);
		model.addAttribute("aeeApproval", false);
		//		

		return "oilReportsListNew";
	}


	@PreAuthorize("hasRole('ODTL')")
	@RequestMapping(value = {"/listOilReportRejected" }, method = RequestMethod.GET)
	public String listOilReportRejected(ModelMap model) {
		model.addAttribute("aeeApproval", false);
		model.addAttribute("finalReport", false);
		model.addAttribute("rejectedReport", true);
		//		
		return "oilReportsListNew";
	}



	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = {"/equipmentListNew" }, method = RequestMethod.GET)
	public String listEquipmentNew(ModelMap model) {
		model.addAttribute("loggedinuser",  userService.getLoggedInUser());
		return "equipmentListNew";
	}


	@PreAuthorize("hasRole('ODTL')")
	@RequestMapping(value = {"/pendingOilSamples" }, method = RequestMethod.GET)
	public String pendingOilSamples(ModelMap model) {

		model.addAttribute("pendingSamples", true);
		return "oilSampleListNew";
	}







	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = {"/listOilSampleNew" }, method = RequestMethod.GET)
	public String listOilSampleNew(ModelMap model) {
		return "oilSampleListNew";
	}
	
	
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = {"/filterEquipment" }, method = RequestMethod.POST)
	public ModelAndView filterEquipmentsNew(
			@RequestParam(value="circleSelected") Circle circle,
			@RequestParam(value="divisionSelected") Division div,
			@RequestParam(value="substationSelected") Substation substation,
			@RequestParam(value="capacity") String capacity,
			@RequestParam(value="serialNo") String serialNo,
			@RequestParam(value="equipmentID") String equipmentID,
			@RequestParam(value="make") String make,
			ModelMap model) 
	{

		EquipmentFilterModel equipmentFilterModel=new EquipmentFilterModel(circle, div, substation, capacity, serialNo,equipmentID, make);
		model.addAttribute("equipments", restService.getEquipmentsList(equipmentFilterModel));
		model.addAttribute("loggedinuser", restService.getLoggedInUser());
		
		return new ModelAndView("equipmentTable", model) ;
	}






	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = {"/filterOilSample" }, method = RequestMethod.POST)
	public ModelAndView filterOilSample(
			@RequestParam(value="circleSelected") Circle circle,
			@RequestParam(value="divisionSelected") Division div,
			@RequestParam(value="substationSelected") Substation substation,


			@RequestParam(value="bottleNo")Integer bottleNo, 
			@RequestParam(value="sampleNo")Integer sampleNo, 
			@RequestParam(value="referenceMemoDate")String referenceMemoDate, 
			@RequestParam(value="referenceMemoNo")String referenceMemoNo,
	
			@RequestParam(value="pendingSamples")Boolean pendingSamples,
			@RequestParam(value="month")Integer month,
			@RequestParam(value="year")Integer year,
			ModelMap model) 
	
	{
		DateFormat dateFormat=new SimpleDateFormat("dd/MM/YYYY");

		//OilSampleFilterModel oilSampleFilterModel=new OilSampleFilterModel(circle, div, substation,Integer.parseInt(bottleNo),Integer.parseInt(sampleNo),null,referenceMemoNo);
		OilSampleFilterModel oilSampleFilterModel=new OilSampleFilterModel(circle, div, substation,bottleNo,sampleNo,null,referenceMemoNo,pendingSamples,month,year);
		model.addAttribute("oilSamples", restService.findAllOilSamples(oilSampleFilterModel));
		model.addAttribute("loggedinuser", restService.getLoggedInUser());

		return new ModelAndView("oilSamplesTable", model) ;
	}



	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = {"/filterOilReport" }, method = RequestMethod.POST)
	public ModelAndView filterOilReport(
			@RequestParam(value="circleSelected") Circle circle,
			@RequestParam(value="divisionSelected") Division div,
			@RequestParam(value="substationSelected") Substation substation,


			@RequestParam(value="sampleNo")Integer sampleNo, 
			@RequestParam(value="reportDateStart")String reportDateStart,
			@RequestParam(value="reportDateEnd")String reportDateEnd,
			@RequestParam(value="month")Integer month,
			@RequestParam(value="year")Integer year,
			@RequestParam(value="finalReport")Boolean finalReport,
			@RequestParam(value="aeeApproval")Boolean aeeApproval,
			@RequestParam(value="rejectedReport")Boolean rejectedReport,
			ModelMap model) 
	{
		OilReportFilterModel oilReportFilterModel=new OilReportFilterModel(circle, div, substation,sampleNo,reportDateStart,reportDateEnd,month,year,finalReport,aeeApproval,rejectedReport);
		model.addAttribute("oilReports", restService.findAllOilReports(oilReportFilterModel));
		model.addAttribute("loggedinuser", restService.getLoggedInUser());

		return new ModelAndView("oilReportsListTable", model) ;
	}

	@RequestMapping(value = "/getLocationsModel", method = RequestMethod.POST,produces = "application/json")
	public @ResponseBody FilterModel getEmployee(@RequestParam(value="circleSelected") String circleCode,@RequestParam(value="divisionSelected") String divCode,@RequestParam(value="substationSelected") String substationCode,ModelMap model) {
		FilterModel locationModel= restService.getLocationModel(circleCode,divCode,substationCode);

		return locationModel;

	}
	@RequestMapping(value = "/downloadZips", produces="application/zip")
	public String download(ModelMap modelMap)
	{
		return "downloadZips";
	}




	@Autowired
	ServletContext context;

	@RequestMapping(value = "/downloadFiles", produces="application/zip")
	@ResponseBody
	public HttpEntity<byte[]> zipFiles(@RequestParam(value="start") Integer start,
			@RequestParam(value="end") Integer end,HttpServletResponse response) throws IOException{
		//setting headers
		response.setContentType("application/zip");
		response.setStatus(HttpServletResponse.SC_OK);


		//creating byteArray stream, make it bufforable and passing this buffor to ZipOutputStream
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(byteArrayOutputStream);
		ZipOutputStream zipOutputStream = new ZipOutputStream(bufferedOutputStream);

		//simple file list, just for tests
		List<ReportFile> files = restService.getPDFFiles(start,end);

		//new ArrayList<>(2);
		//files.add(new File("README.md"));

		//packing files
		for (ReportFile file : files) {

			//new zip entry and copying inputstream with file to zipOutputStream, after all closing streams
			//zipOutputStream.putNextEntry(new ZipEntry("File"+file.getId()));
			zipOutputStream.putNextEntry(new ZipEntry(file.getPdfFileName()));
			File fileToRead=new File(StringUtil.FILE_REPOSITORY+file.getPdfFileName()) ;
			FileInputStream fileInputStream = new FileInputStream(fileToRead);
			//
			IOUtils.copy(fileInputStream, zipOutputStream);
			//
			fileInputStream.close();
			//zipOutputStream.write(file.getPdfFile());
			zipOutputStream.closeEntry();

		}

		if (zipOutputStream != null) {
			zipOutputStream.finish();
			zipOutputStream.flush();
			IOUtils.closeQuietly(zipOutputStream);
		}
		IOUtils.closeQuietly(bufferedOutputStream);
		IOUtils.closeQuietly(byteArrayOutputStream);
		byte[] zipByteArray=byteArrayOutputStream.toByteArray();
		response.addHeader("Content-Disposition", "attachment; filename=\"Reports_"+start+"_"+end+".zip\"");
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM); //or what ever type it is
		headers.setContentLength(zipByteArray.length);

		return new HttpEntity<byte[]>(zipByteArray, headers);
		//return byteArrayOutputStream.toByteArray();
	}



}
