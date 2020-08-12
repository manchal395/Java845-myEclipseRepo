import java.time.LocalDate;
import java.util.List;

import org.junit.Test;

import com.lti.dao.EmployeeDao;
import com.lti.entity.Employee;

public class EmployeeTest {
	
	@Test
	public void addEmployee() {
		Employee e = new Employee();
//		e.setPsno(101);
//		e.setName("ABC");
//		e.setDateOfJoining(LocalDate.of(1980, 12, 11));
//		e.setSalary(90000);
		
//		e.setPsno(102);
//		e.setName("DEF");
//		e.setDateOfJoining(LocalDate.of(1990, 05, 3));
//		e.setSalary(80000);
		
		e.setPsno(104);
		e.setName("GHI");
		e.setDateOfJoining(LocalDate.of(1995, 03, 25));
		e.setSalary(70000);
		
		EmployeeDao ed = new EmployeeDao();
		ed.add(e);
	}
	
	@Test
	public void fetchByPsno() {
		EmployeeDao ed = new EmployeeDao();
		Employee e = ed.fetchByPsno(102);
		System.out.println(e.getPsno()+" "+e.getName()+" "+e.getDateOfJoining()+" "+e.getSalary());
	}
	
	@Test
	public void fetchByMonth() {
		EmployeeDao ed = new EmployeeDao();
		List<Employee> list = ed.fetchByMonth(3);
		for(Employee e : list)
			System.out.println(e.getPsno()+" "+e.getName()+" "+e.getDateOfJoining()+" "+e.getSalary());
	}
	
	@Test
	public void fetchBySalary() {
		EmployeeDao ed = new EmployeeDao();
		List<Employee> list = ed.fetchBySalary(70000);
		for(Employee e : list)
			System.out.println(e.getPsno()+" "+e.getName()+" "+e.getDateOfJoining()+" "+e.getSalary());
	}
	
	@Test
	public void fetchByCity() {
		EmployeeDao ed = new EmployeeDao();
		List<Employee> list = ed.fetchByCity("Delhi");
		for(Employee e : list)
			System.out.println(e.getPsno()+" "+e.getName()+" "+e.getDateOfJoining()+" "+e.getSalary()+" "+e.getAddress().getCity());
	}
	
	@Test
	public void update() {
		EmployeeDao ed = new EmployeeDao();
		Employee e = ed.fetchByPsno(103);
		e.setName("NewName");
		ed.update(e);
	}
}
