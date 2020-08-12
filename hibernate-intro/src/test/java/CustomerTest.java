import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.lti.dao.CustomerDao;
import com.lti.entity.Customer;

public class CustomerTest {
	
	@Test
	public void addCustomer() {
		Customer cust = new Customer();
		//cust.setId();
		cust.setName("Anchal5");
		cust.setEmail("anchal5@gmail");
		
		CustomerDao dao = new CustomerDao();
		dao.add(cust);
		
		//asset missing right now
	}
	
	@Test
	public void fetchCustomer() {
		CustomerDao dao = new CustomerDao();
		Customer cust = dao.fetch(1);
		System.out.println(cust.getName()+" "+cust.getEmail());
	}
	
	@Test
	public void fetchAll() {
		CustomerDao dao = new CustomerDao();
		List<Customer> list = dao.fetchAll();
		for(Customer c : list)
			System.out.println(c.getId()+" "+c.getName()+" "+c.getEmail());
	}
	
	@Test
	public void fetchNames() {
		CustomerDao dao = new CustomerDao();
		List<String> list = dao.fetchNames();
		for(String s : list)
			System.out.println(s);
	}
	
	@Test
	public void fetchNamesEmail() {
		CustomerDao dao = new CustomerDao();
		List<String[]> list = dao.fetchNamesEmail();
		System.out.println(Arrays.toString(list.get(0)));	
	}
	
	
	@Test
	public void fetchOnEmail() {
		CustomerDao dao = new CustomerDao();
		List<Customer> list = dao.fetchOnEmail("hotmail");
		for(Customer c : list)
			System.out.println(c.getId()+" "+c.getName()+" "+c.getEmail());
	}
	
	@Test
	public void updateCustomer() {
		CustomerDao dao = new CustomerDao();
		Customer cust = dao.fetch(2);
		cust.setName("Batman");
		cust.setEmail("batman@lti");
		dao.update(cust);
	}
	
}
