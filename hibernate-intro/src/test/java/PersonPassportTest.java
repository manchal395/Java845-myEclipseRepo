import java.time.LocalDate;
import java.util.List;

import org.junit.Test;

import com.lti.dao.GenericDao;
import com.lti.dao.PersonDao;
import com.lti.entity.Passport;
import com.lti.entity.Person;

public class PersonPassportTest {

	@Test
	public void addPerson() {
		Person person = new Person();
		person.setName("Uday");
		person.setDateOfBirth(LocalDate.of(1997, 8, 06));
		GenericDao dao = new GenericDao();
		dao.add(person);
	}
	
	@Test
	public void addPassportForExistingPerson() {
		Passport passport = new Passport();
		passport.setPassportNo(1013);
		passport.setIssueDate(LocalDate.of(2021, 06, 23));
		passport.setExpiryDate(LocalDate.of(2031, 06, 27));
		passport.setCity("Mumbai");
		
		GenericDao dao = new GenericDao();
		Person person = (Person) dao.fetchByPK(Person.class, 9);
		passport.setPerson(person);
		dao.add(passport);
	}
	
	@Test
	public void addPersonAlongWithPassport() {
		//a passport will exist iff a person exists
		//so no need of separate add method only for passport
		Person person = new Person();
		person.setName("DEF");
		person.setDateOfBirth(LocalDate.of(1997, 06, 11));
		
		Passport passport = new Passport();
		passport.setPassportNo(1026);
		passport.setIssueDate(LocalDate.of(2010, 03, 28));
		passport.setExpiryDate(LocalDate.of(2020, 03, 27));
		passport.setCity("Delhi");

		//should keep cascade in person and not passport
		//because, passport's existance depends on person
		
		//with cascade merge on Person: adding person will add both person and passport
		//without cascade this will give error, because
		//we need to save data for both passport and person separately
		
		//below scenario with cascade merge on Person
		passport.setPerson(person);
		person.setPassport(passport);
		GenericDao dao = new GenericDao();
		dao.add(person);
		
	}
	
	
	@Test
	public void updatePerson() {
		GenericDao dao = new GenericDao();
		Person person = (Person) dao.fetchByPK(Person.class, 10);
		person.setName("Rashii");
		dao.add(person);
	}
	
	@Test
	public void updatePassport() {
		GenericDao dao = new GenericDao();
		Passport passport = (Passport) dao.fetchByPK(Passport.class, 1012);
		passport.setExpiryDate(LocalDate.of(2040, 07, 23));
		dao.add(passport);
	}
	
	@Test
	public void fetchPersonById() {
		GenericDao dao = new GenericDao();
		Person person = (Person) dao.fetchByPK(Person.class, 10);
		//if passport exists
		//check is required here
		System.out.println(person.getId()+" "+person.getName()+" "+person.getDateOfBirth()+" "+person.getPassport().getPassportNo());
	}
	
	@Test
	public void fetchPassportByNo() {
		GenericDao dao = new GenericDao();
		Passport passport = (Passport) dao.fetchByPK(Passport.class, 1012);
		System.out.println(passport.getPassportNo()+" "+passport.getIssueDate()+" "+passport.getExpiryDate()+" "+passport.getCity()+" "+passport.getPerson().getName());
	}
	
	@Test
	public void fetchPersonsByExpiredPassport() {
		PersonDao dao = new PersonDao();
		List<Person> persons = dao.fetchByExpiredPassport(LocalDate.of(2031, 06, 27));
		//List<Person> persons = dao.fetchByExpiredPassport();
		for(Person p : persons)
			System.out.println(p.getId()+" "+p.getName()+" "+p.getDateOfBirth()+" "+p.getPassport().getExpiryDate());
	}
	
	@Test
	public void fetchPassportOfPerson() {
		PersonDao dao = new PersonDao();
		Passport pass = dao.fetchPassportOfPerson("Anchal");
		System.out.println(pass.getPassportNo()+" "+pass.getIssueDate()+" "+pass.getExpiryDate()+" "+pass.getCity()+" "+pass.getPerson().getName());
	}
	
	@Test
	public void fetchPersonNamePassportNo() {
		PersonDao dao = new PersonDao();
		List<Object[]> namePass = dao.fetchNamePassportNo();
		for(Object[] o : namePass)
			System.out.println("NAME: "+o[0]+", PASSPORT NO: "+o[1]);
	}
}
