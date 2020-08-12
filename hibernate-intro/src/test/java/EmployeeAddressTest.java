import java.time.LocalDate;

import org.junit.Test;

import com.lti.dao.EmployeeDao;
import com.lti.entity.Address;
import com.lti.entity.Employee;

public class EmployeeAddressTest {
	
	@Test
	public void addAnEmployee() {
		
	}
	
	@Test
	public void addAddressForAnExistingEmployee() {
		EmployeeDao ed = new EmployeeDao();
		Employee e = ed.fetchByPsno(102);
		Address a = new Address();
		//a.setId(01);
		a.setCity("Delhi");
		a.setPincode(110092);
		a.setLandmark("Axis Bank");
		e.setAddress(a);
		ed.add(a);
		ed.update(e);
	}
	
	@Test
	public void addAnEmployeeAlognWithAddress() {
		Employee e = new Employee();
		e.setPsno(108);
		e.setName("Cascade");
		e.setDateOfJoining(LocalDate.of(1995, 06, 25));
		e.setSalary(60000);
		
		Address a = new Address();
		a.setCity("Chennai");
		a.setPincode(110090);
		a.setLandmark("das");
		
		e.setAddress(a);
		
		//we want a way by which emp and address both can get saved in the db automatically
		//we're trying to add data into 2 tables at once
		//cascading effect - enable it in Employee cass one-to-one annotation
		EmployeeDao ed = new EmployeeDao();
		ed.add(e);
		
		// alternatively, we can do this if reqd:
//		 ed.add(a);
//		 ed.add(e);
		 
	}
	
}
