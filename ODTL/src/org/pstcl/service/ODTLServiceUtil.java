package org.pstcl.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.pstcl.dao.EquipmentDao;
import org.pstcl.dao.IUserDesignationDao;
import org.pstcl.dao.LocationDao;
import org.pstcl.dao.OilReportDao;
import org.pstcl.dao.OilSampleDao;
import org.pstcl.dao.UserDao;
import org.pstcl.dao.UserProfileDao;
import org.pstcl.logging.ILogDetailsDao;
import org.pstcl.model.entity.LocationMaster;
import org.pstcl.model.entity.User;
import org.pstcl.model.entity.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;


@Transactional
public class ODTLServiceUtil {

	@Autowired
	UserService userService;
	
	

	@Autowired
	protected ILogDetailsDao logDetailsDao;
	
	@Autowired
	protected UserProfileDao userProfileDao;
	@Autowired
	private IUserDesignationDao designationDao;
	@Autowired
	protected OilSampleDao oilSampleDao;
	@Autowired
	protected EquipmentDao equipmentDao;
	@Autowired
	protected LocationDao locationDao;
	@Autowired
	protected UserDao userDao;

	@Autowired
	protected OilReportDao oilReportDao;

	@Autowired
	protected OilSampleService oilSampleService;


	public String getPrincipal() {
		String userName = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			userName = ((UserDetails) principal).getUsername();
		} else {
			userName = principal.toString();
		}

		return userName;
	}
	
	public User getLoggedInUser()
	{
		User user= userDao.findBySSO(getPrincipal());
		return user;
	}
	
	
	

	public List<String> getOilTypeList() {
		ArrayList oilTypeList = new ArrayList();
		oilTypeList.add("Naphtha based Mineral Oil");
		oilTypeList.add("Paraffin based Mineral Oil");
		return oilTypeList;
	}


	public List<String> getEquipmentTypeList() {
		ArrayList equipmentTypeList = new ArrayList();
		equipmentTypeList.add("Power Transformer");
		equipmentTypeList.add("Auto Transformer");
		equipmentTypeList.add("ICT");
		equipmentTypeList.add("Reactor");
		equipmentTypeList.add("GT");
		equipmentTypeList.add("Drum Oil");
		equipmentTypeList.add("Loose Oil");
		equipmentTypeList.add("NA");
		return equipmentTypeList;
	}

	public List<String> getVoltageClassList() {
		ArrayList voltageClassList = new ArrayList();
		voltageClassList.add("400/220/33 kV");
		voltageClassList.add("220/132 kV");
		voltageClassList.add("220/66/11 kV");
		voltageClassList.add("220/66 kV");
		voltageClassList.add("132/66-33 kV");
		voltageClassList.add("132/66 kV");
		voltageClassList.add("132/33 kV");
		voltageClassList.add("132/11 kV");
		voltageClassList.add("66/33 kV");
		voltageClassList.add("66/11 kV");
		voltageClassList.add("33/11 kV");
		voltageClassList.add("GT");
		voltageClassList.add("NA");
		return voltageClassList;
	}

	public List<String> getCoolingTypeList() {
		ArrayList coolingTypeList = new ArrayList();
		coolingTypeList.add("ONAN");
		coolingTypeList.add("OFAF");
		coolingTypeList.add("ONAF");
		coolingTypeList.add("OFAN");
		coolingTypeList.add("NA");
		return coolingTypeList;
	}

	public List<String> getAppearanceList() {
		ArrayList list = new ArrayList();
		list.add("Transparent");
		list.add("Pale Yellow");
		list.add("Light Yellow");
		list.add("Yellow");
		list.add("Dusty Yellow");
		list.add("Dark Yellow");
		list.add("Light Reddish Brown");
		list.add("Reddish Brown");
		list.add("Dark Reddish Brown");
		list.add("Dark Brown");
		list.add("Black");
		list.add("Clear");
		list.add("Colorless");
		list.add("Transparent & clear free from suspended particles");
		return list;
	}

	public List<String> getCorrosiveSulphurList() {
		ArrayList list = new ArrayList();
		list.add("Non-Corrosive");
		list.add("Corrosive");
		return list;
	}

	public List<String> getOxidationInhibitorList() {
		ArrayList list = new ArrayList();
		list.add("Present");
		list.add("Absent");
		return list;
	}

	protected void createLocationUsers(LocationMaster location) {
		List<User> users= userDao.findLocationUsers(location);
		if(users==null|users.size()<1)
		{
			createLocationAE(location);
			createLocationASE(location);

		}
	}

	private void createLocationAE(LocationMaster location) {


		User ae=new User();
		ae.setSsoId("SSE"+location.getSubstation().getSubstationDescription().replaceAll("\\s+",""));
		ae.setFirstName("SSE");
		ae.setLastName(location.getSubstation().getSubstationDescription());
		createUserMetadata(location, ae);
		setUserProfileAndDesignation(ae,"AE");


		userService.saveUser(ae);
	}

	private void createLocationASE(LocationMaster location) {
		User ase=new User();
		ase.setSsoId("ASE"+location.getSubstation().getSubstationDescription());
		ase.setFirstName("ASE");
		ase.setLastName(location.getDivision().getDivisionDescription());
		createUserMetadata(location, ase);

		setUserProfileAndDesignation(ase,"ASE");
		userService.saveUser(ase);
	}


	private void createUserMetadata(LocationMaster location, User ae) {
		ae.setPassword("password");
		ae.setSubstation(location.getSubstation());
		ae.setDivision(location.getDivision());
		ae.setCircle(location.getCircle());
		ae.setEmail("abc@abc.org");
	}

	private void setUserProfileAndDesignation(User ae,String userPosition) {
		Set<UserProfile> userProfiles=ae.getUserProfiles();
		userProfiles.add(userProfileDao.findById(1));
		if(userPosition.equalsIgnoreCase("AE"))
		{
			userProfiles.add(userProfileDao.findById(7));
			ae.setDesignation(designationDao.findById(6));
		}
		else if(userPosition.equalsIgnoreCase("ASE"))
		{
			userProfiles.add(userProfileDao.findById(8));
			ae.setDesignation(designationDao.findById(5));
		}
		ae.setUserProfiles(userProfiles);
	}

	public List<String> getSampleTakenFromList() {
		ArrayList list = new ArrayList();
		list.add("Main Tank");
		list.add("OLTC");
		list.add("New Loose Oil");
		list.add("Used Loose Oil");
		list.add("Filteration Set");
		list.add("Unused Loose Oil");
		list.add("Accessory Oil");
		list.add("Drum");
		list.add("Tank-1");
		list.add("Tank-2");
		list.add("Tank-3");
		list.add("Tank-4");
		list.add("Tank-5");
		list.add("Tank-6");
		list.add("Conservator Tank");
		list.add("Radiator");
		list.add("Other");
		return list;
	}

	public List<String> getSamplingValveList() {
		ArrayList list = new ArrayList();
		list.add("Bottom");
		list.add("Middle");
		list.add("Top");
		list.add("Red Phase");
		list.add("Yellow Phase");
		list.add("Blue Phase");
		list.add("Others");
		return list;
	}

	public List<String> getSampleContainerList() {
		ArrayList list = new ArrayList();
		list.add("Stainless Steel Bottle");
		list.add("Plastic Can");
		list.add("Amber Glass Bottle");
		list.add("Glass Syringe");
		list.add("Plastic Bottle");
		return list;
	}

	public List<String> getSampleConditionList() {
		ArrayList list = new ArrayList();
		list.add("Good");
		list.add("Contaminated");
		list.add("Bad");
		return list;
	}


}
