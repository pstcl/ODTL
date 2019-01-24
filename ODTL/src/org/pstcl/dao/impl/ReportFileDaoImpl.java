package org.pstcl.dao.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.pstcl.dao.AbstractDao;
import org.pstcl.dao.ReportFileDao;
import org.pstcl.model.FilterModel;
import org.pstcl.model.entity.ReportFile;
import org.pstcl.model.entity.User;
import org.pstcl.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;



@Repository("reportFileDao")
@Transactional
public class ReportFileDaoImpl extends AbstractDao<Integer, ReportFile> implements ReportFileDao {

	static final Logger logger = LoggerFactory.getLogger(ReportFileDaoImpl.class);

	@Override
	public ReportFile findById(int id) {
		ReportFile oilReport = getByKey(id);
		return oilReport;
	}



	//	@Override
	//	public List<ReportFile> findAllReportFiles() {
	//		Criteria crit = createEntityCriteria();
	//
	//		return (List<ReportFile>)crit.list();
	//
	//	}

	@Override
	public List<ReportFile> findAllReportFiles(Integer start, Integer end) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.ge("id",start));
		crit.add(Restrictions.le("id",end));
		crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);  
		return crit.list() ;
	}



	@Override
	@Transactional
	public void deleteById(String id) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("id", id));
		ReportFile oilReport = (ReportFile)crit.uniqueResult();
		delete(oilReport);
	}

	@Autowired
	ServletContext context;
	@Override
	@Transactional
	public void save(ReportFile reportFile,User user) {
		
		//Write the PDF to folder//DB gets too slow
		String reportName=reportFile.getOilSample().getSampleNo()+" ODTL_Circle_"+reportFile.getOilSample().getSampleSentByUser().getCircle().getCircleDescription()
				   +"_Div_"+reportFile.getOilSample().getSampleSentByUser().getDivision().getDivisionDescription()
					  +"_SS_"+reportFile.getOilSample().getSampleSentByUser().getSubstation().getSubstationDescription()
					 +".pdf";
		File file=new File(StringUtil.FILE_REPOSITORY+reportName) ;
		try {
			FileOutputStream fileOutputStream = new FileOutputStream(file);
			fileOutputStream.write(reportFile.getPdfFile());
			fileOutputStream.flush();
			fileOutputStream.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		reportFile.setPdfFileName(reportName);
		//logging not enabled
		persist(reportFile);
		getSession().flush();

	}





	@Override
	public List<ReportFile> findAllReportFiles(FilterModel filterModel) {
		Criteria crit =  createEntityCriteria();

		return (List<ReportFile>) crit.list();
	}



	@Override
	public ReportFile findBySampleId(Integer id) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("oilSample.id", id));
		return (ReportFile)crit.uniqueResult();
		
	}








}
