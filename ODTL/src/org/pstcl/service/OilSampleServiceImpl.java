package org.pstcl.service;

import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.pstcl.logging.LogDetails;
import org.pstcl.model.entity.Equipment;
import org.pstcl.model.entity.OilReport;
import org.pstcl.model.entity.OilSample;
import org.pstcl.model.entity.ReportOfficer;
import org.pstcl.model.entity.User;
import org.pstcl.util.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("oilSampleService")
@Transactional
public class OilSampleServiceImpl extends ODTLServiceUtil implements OilSampleService {
	public List<OilSample> findAllOilSamples() {
		return this.oilSampleDao.findAllOilSamples();
	}
	@Override
	public List<OilSample> getPendingOilSamples() {
		return this.oilSampleDao.findPendingOilSamples();
	}
	
	@Transactional
	private void logEvent(OilSample oldEntity,OilSample updatedEntity,String action) {

		LogDetails logDetails=new LogDetails();
		logDetails.setEntityAction(action);
		logDetails.setUser(userDao.findBySSO(getPrincipal()));
		if(null!=oldEntity)
		{
		logDetails.setObjectValueOld(oldEntity.toString());;
		}		logDetails.setObjectValueUpdated(updatedEntity.toString());
		logDetails.setEntityID(updatedEntity.getId());
		logDetails.setTableName(updatedEntity.getClass().toString());
		logDetails.setTimeStamp(new Date(System.currentTimeMillis()));

		logDetailsDao.save(logDetails);

	}

	@Transactional
	public String saveOilSample(OilSample oilSample) {
		String msg="";
		if (null==oilSample.getId()) {


			oilSample.setSenderDetails(new ReportOfficer(oilSample.getSampleSentByUser(),oilSample.getClass()));
			logEvent(null,oilSample,StringUtil.CREATE_EVENT);

			this.oilSampleDao.save(oilSample,userDao.findBySSO(getPrincipal()));
			msg="OilSample No." + oilSample.getSampleNo()+ " saved successfully";
		}
		else
		{
			OilSample entity = this.oilSampleDao.findById(oilSample.getId());

			if (entity != null) {

				logEvent(entity,oilSample,StringUtil.UPDATE_EVENT);
				entity.updateOilSample(oilSample);
				entity.getSenderDetails().updateReportOfficer(oilSample.getSampleSentByUser());
				msg="OilSample No." + oilSample.getSampleNo()+ " updated successfully";
			}
		}
		return msg;
	}

	public OilSample findOilSampleById(Integer id) {
		return this.oilSampleDao.findById(id.intValue());
	}

	@Transactional
	public OilSample createOilSampleForRoutineOil(Integer equipmentId) {
		OilSample entity = new OilSample(StringUtil.OIL_REPORT_ROUTINE_OIL);
		initializeSample(entity);
		if(null!=equipmentId)
		{
			Equipment equipment=this.equipmentDao.findById(equipmentId);
			entity.setEquipment(equipment);
		}
		else
		{
			entity.setEquipment(new Equipment());
		}
		setDefaultSampleSender(entity);
		return entity;
	}


	
	@Transactional
	public OilSample createOilSampleForFreshOil() {
		OilSample entity = new OilSample(StringUtil.OIL_REPORT_FRESH_OIL);
		initializeSample(entity);
		setDefaultSampleSender(entity);
		return entity;
	}



	@Override
	@Transactional
	public OilSample createOilSampleForNewOil() {
		OilSample entity = new OilSample(StringUtil.OIL_REPORT_NEW_OIL);
		initializeSample(entity);
		Equipment equipment=equipmentDao.findById(StringUtil.NA_EQUIPMENT_ID);
		if(null==equipment)
		{
			equipment=createDummyEquipmentForSSD();
		}
		entity.setEquipment(equipment);
		setDefaultSampleSender(entity);
		return entity;
	}


	private void setDefaultSampleSender(OilSample oilSample)
	{
		if(oilSample.getSampleType().equalsIgnoreCase(StringUtil.OIL_REPORT_FRESH_OIL))
		{
			User gridConstUser=new User();
			gridConstUser.setCircle(locationDao.findCircleByID("G"));
			List<User> list=userDao.findLocationUsers(gridConstUser);
			if(null!=list)
			{
				if(null!=list&list.size()>0)
				{
					Collections.sort(list);
					oilSample.setSampleSentByUser(list.get(0));
				}
			}
		}
		else if(null!=oilSample.getEquipment())
		{
			//if Equipment has been selected then, AE/AEE of the Subdivision can be set as default sender. USer can still change it in the addSample page
			//method finds all users of the location
			List<User> list=userDao.findLocationUsers(oilSample.getEquipment());
			if(null!=list&list.size()>0)
			{
				Collections.sort(list);
				oilSample.setSampleSentByUser(list.get(0));
			}
		}
		else
		{
			// "Logged In user can be set as sample set by user by default."
			oilSample.setSampleSentByUser(userDao.findBySSO(getPrincipal()));
		}


	}



	@Override
	public Set<User> prepareSampleSenderList(OilSample oilSample) {

		Equipment equipment=oilSample.getEquipment();
		User selectedSender=oilSample.getSampleSentByUser();
		User gridConstUser=new User();
		gridConstUser.setCircle(locationDao.findCircleByID("G"));

		Set<User> sendersList = new HashSet<User>();

		if(oilSample.getSampleType().equalsIgnoreCase(StringUtil.OIL_REPORT_FRESH_OIL))
		{
			sendersList.addAll(userDao.findLocationUsers(gridConstUser));	
		}
		else
		{
//			List<User> list=userDao.findLocationUsers(selectedSender);
//			sendersList.addAll(list);
			List<User> list2=userDao.findLocationUsers(equipment);
			sendersList.addAll(list2);
			
		}
		return sendersList;
	}

	private Equipment createDummyEquipmentForSSD()
	{
		Equipment equipment=new Equipment();
		equipment.setDivision(locationDao.findDivisionByID("SSD"));
		equipment.setCircle(equipment.getDivision().getCircle());
		equipment.setEquipmentType("Not Applicable");
		equipment.setEquipmentID("NA");

		//equipment.setId(StringUtil.NA_EQUIPMENT_ID);
		equipmentDao.save(equipment, userDao.findBySSO(getPrincipal()));
		return equipmentDao.findById(StringUtil.NA_EQUIPMENT_ID);
	}


	private void initializeSample(OilSample oilSample)
	{
		OilSample largestSampleByNo=oilSampleDao.findLargestSampleByNo();
		if(null!=largestSampleByNo)
		{
			oilSample.setSampleNo(largestSampleByNo.getSampleNo()+1);
		}
		else
		{
			oilSample.setSampleNo(1);
		}
		oilSample.setSampleReceiptDate(new Date(System.currentTimeMillis()));

	}

	@Override
	public void setSampleSender(OilSample oilSample)
	{

		if(null!=oilSample.getSampleSentByUser())
		{
			if(null!=oilSample.getSampleSentByUser().getId())
			{
				oilSample.setSampleSentByUser(userDao.findById(oilSample.getSampleSentByUser().getId()));

			}
		}

	}




	@Override
	public void setEquipment(OilSample oilSample) {
		Equipment equipment=null;
		if(null!=(oilSample.getEquipment()))
		{
			if(null!=(oilSample.getEquipment().getId()))
			{
				equipment=this.equipmentDao.findById(oilSample.getEquipment().getId());
				oilSample.setEquipment(equipment);
				setSampleSender(oilSample);
			}

		}

	}

	//	private void setSampleTypeForSender(OilSample oilSample)
	//	{
	//
	//		//Hard Coding to check if Substation Design has sent the oil; If yes oil sample is treated as new oil;
	//		//Hard Coding to check if Grid Construction has sent the oil; If yes oil sample is treated as Fresh oil;
	//		//else it is regular oil; defines the report type and number of report paramters; User insisted on doing so
	//
	//		if(null!=oilSample.getSampleSentByUser())
	//		{
	//			if(oilSample.getSampleSentByUser().getDivision().getDivisionCode().equalsIgnoreCase("SSD"))
	//			{
	//				oilSample.setSampleType(StringUtil.OIL_REPORT_NEW_OIL);
	//			}
	//			else if(oilSample.getSampleSentByUser().getCircle().getCircleCode().equalsIgnoreCase("G"))
	//			{
	//				oilSample.setSampleType(StringUtil.OIL_REPORT_FRESH_OIL);
	//			}
	//			else
	//			{
	//				oilSample.setSampleType(StringUtil.OIL_REPORT_ROUTINE_OIL);
	//			}
	//		}
	//		else
	//		{
	//			oilSample.setSampleType(StringUtil.OIL_REPORT_ROUTINE_OIL);
	//		}
	//	}



	//VALIDATIONs and MSGS methods

	@Override
	public boolean isEquipmentValid(OilSample oilSample) {
		if(null!=oilSample.getEquipment())
		{
			if(null!=oilSample.getEquipment().getId())
			{
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean isSampleUserValid(OilSample oilSample) {
		Boolean valid=false;
		if(null!=oilSample)
		{
			User sampleSender= oilSample.getSampleSentByUser();
			if(null!=sampleSender)
			{
				if(null!=sampleSender.getId())
				{
					if(null!=userDao.findById(sampleSender.getId()))
					{
						valid=true;

					}
				}
			}

		}

		return valid;
	}


	@Override
	public boolean isSampleNoUnique(OilSample oilSample) {
		OilSample oilsample1 = oilSampleDao.findBySampleNo(oilSample.getSampleNo());
		Integer id=oilSample.getId();
		return ( oilsample1 == null || ((id != null) && (oilsample1.getId().equals(id))));
	}
	@Override
	public String getEquipmentLabelForSample(Integer sampleNo) {
		OilSample oilSample=oilSampleDao.findBySampleNo(sampleNo);
		String msg = null;
		if(null!=oilSample)
		{
			msg= oilSample.getEquipment().getEquipmentLabel();	
		}
		return msg;
	}




}