package org.pstcl.service;

import java.util.Date;
import java.util.List;

import org.pstcl.logging.LogDetails;
import org.pstcl.model.FilterModel;
import org.pstcl.model.entity.Circle;
import org.pstcl.model.entity.Division;
import org.pstcl.model.entity.Equipment;
import org.pstcl.model.entity.LocationMaster;
import org.pstcl.model.entity.Substation;
import org.pstcl.model.entity.User;
import org.pstcl.util.StringUtil;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("equipmentService")
@Transactional
public class EquipmentServiceImpl extends ODTLServiceUtil implements EquipmentService {

	public String saveEquipment(Equipment equipment) {
		String msg="";


		equipment.setDateEntered(new Date(System.currentTimeMillis()));


		if (null==equipment.getId()) {
			
			logEvent(null,equipment,StringUtil.CREATE_EVENT);
			this.equipmentDao.save(equipment,userDao.findBySSO(getPrincipal()));
			msg="Equipment " + equipment.getEquipmentID()+ " saved successfully";
			
			createLocationUsers(equipment);
		}


		else
		{
			Equipment entity = this.equipmentDao.findById(equipment.getId());
			if (entity != null) {
				msg="Equipment " + equipment.getEquipmentID()+ " updated successfully";
				logEvent(entity,equipment,StringUtil.UPDATE_EVENT);
				entity.updateEquipment(equipment);

			}
		}
		return msg;
	}
	private void logEvent(Equipment oldEntity,Equipment updatedEntity,String action) {

		LogDetails logDetails=new LogDetails();
		logDetails.setEntityAction(action);
		if(null!=oldEntity)
		{
		logDetails.setObjectValueOld(oldEntity.toString());;
		}
		logDetails.setUser(userDao.findBySSO(getPrincipal()));
		logDetails.setObjectValueUpdated(updatedEntity.toString());
		logDetails.setEntityID(updatedEntity.getId());
		logDetails.setTableName(updatedEntity.getClass().toString());
		logDetails.setTimeStamp(new Date(System.currentTimeMillis()));

		logDetailsDao.save(logDetails);

	}
	





	public void deleteEquipmentById(String id) {
		this.equipmentDao.deleteById(id);
	}

	public List<Equipment> findAllEquipments() {
		return this.equipmentDao.findAllEquipments();
	}



	public List<Circle> getCircleList(Equipment equipment) {
		return this.locationDao.listCircles(equipment);
	}

	public List<Division> getDivisionList(Equipment equipment) {
		return this.locationDao.listDivisions(equipment);
	}

	public List<Substation> getSubstationList(Equipment equipment) {
		return this.locationDao.listSubstations(equipment);
	}

	public Circle findCircleById(String code) {
		return this.locationDao.findCircleByID(code);
	}

	public Division findDivisionById(String code) {
		return this.locationDao.findDivisionByID(code);
	}

	public Substation findSubstationById(String code) {
		return this.locationDao.findSubstationByID(code);
	}

	public Equipment findEquipmentById(Integer id) {
		return this.equipmentDao.findById(id.intValue());
	}

	@Override
	public List<Equipment> getEquipmentsList(Equipment equipment) {
		return this.equipmentDao.getEquipmentsList(equipment);
	}
	


	@Override
	public boolean isEquipmentIDUniqueForSS(Equipment equipment) {
		Equipment equipment2=new Equipment();
		equipment2.setSubstation(equipment.getSubstation());
		equipment2.setEquipmentID(equipment.getEquipmentID());

		//if equipment ID entered search in DB. 
		List<Equipment> list=equipmentDao.getEquipmentsList(equipment2);
		Boolean unique=false;
		//if not found in db ie list is null or doesnot have object, it is valid

		if(null==list|list.size()<1)
		{
			unique=true;
		}
		else if(list.size()==1)
		{
			//if found in db ie but the id is same as entity being updated; it should be valid
			if(null==equipment.getId())
			{
				unique=false;				
			}
			else if(equipment.getId().equals(list.get(0).getId()))
			{
				unique=true;
			}
		}
		return unique;
	}

	@Override
	public boolean isEquipmentSNoValidForSS(Equipment equipment) {
		Equipment equipment2=new Equipment();
		Boolean valid=false;

		//check if serial no is null or empty; In case empty it is valid because many equipment may not have it
		if(null==equipment.getSerialNo()||equipment.getSerialNo().isEmpty())
		{
			valid=true;
		}
		else {
			//if serial no entered search in DB. 
			equipment2.setSerialNo(equipment.getSerialNo());
			List<Equipment> list=equipmentDao.getEquipmentsList(equipment2);
			//if not found in db ie list is null or doesnot have object, it is valid
			if(null==list|list.size()<1)
			{
				valid=true;
			}
			else if(list.size()==1)
			{
				//if found in db ie but the id is same as entity being updated; it should be valid
				if(null==equipment.getId())
				{
					valid=false;				
				}
				else if(equipment.getId().equals(list.get(0).getId()))
				{
					valid=true;
				}
			}
		}
		return valid;
	}

	@Override
	public void setLocation(Equipment equipment) {
		if(null!=equipment)
		{
			if(null!=equipment.getSubstation())
			{
				equipment.setDivision(equipment.getSubstation().getDivision());
				equipment.setCircle(equipment.getSubstation().getCircle());
			}
			else if(null!=equipment.getDivision())
			{
				equipment.setCircle(equipment.getDivision().getCircle());	
			}
		}


	}

}