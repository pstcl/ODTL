

import java.util.List;

import org.pstcl.dao.OilReportDao;
import org.pstcl.model.entity.OilReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("dummyDataUtilService")
@Transactional
public class DummyDataUtilService  {

	@Autowired
	OilReportDao oilReportDao;

	public static void main(String[] args) {
		//ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		try {
			AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
			context.scan("org.pstcl");//This will load the configured components UserService, UserRepository,
			context.refresh();
			DummyDataUtilService contextUtil = context.getBean(DummyDataUtilService.class);
			contextUtil.generateDummyEmployees();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public void generateDummyEmployees() {
		List<OilReport>  oilReports=oilReportDao.findAllOilReports();

		for (OilReport oilReport : oilReports) {
			System.out.println(oilReport);
		}
	}
	
	

}
