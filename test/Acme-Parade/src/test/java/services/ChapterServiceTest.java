
package services;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import security.Authority;
import security.UserAccount;
import utilities.AbstractTest;
import domain.Chapter;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@Transactional
public class ChapterServiceTest extends AbstractTest {

	//SUT
	@Autowired
	private ChapterService	cs;


	/**
	 * TESTING REQUIREMENT #5 (There's a new kind of actor in the system: chapters.)
	 * POSITIVE TEST
	 * COVERED INSTRUCTIONS IN THIS TEST: 100%
	 * COVERED INSTRUCTIONS IN LinkRecordService: 22.9%
	 * COVERED DATA IN THIS TEST: 12%
	 * */

	@Test
	public void createRecord() {
		final UserAccount ua = new UserAccount();
		ua.setPassword("chapter1");
		ua.setUsername("chapter1");
		final Collection<Authority> coll = new ArrayList<Authority>();
		final Authority a = new Authority();
		a.setAuthority(Authority.CHAPTER);
		coll.add(a);
		ua.setAuthorities(coll);
		final Chapter nc = this.cs.create();
		nc.setAddress("Sample address");
		nc.setBan(false);
		nc.setEmail("newChapter@gmail.com");
		nc.setFlagSpam(false);
		nc.setMiddleName("Sample middle name");
		nc.setName("Sample");
		nc.setPhoneNumber("+34 1231456789");
		nc.setPhoto("http://www.sample.com");
		nc.setSurname("Sample surname");
		nc.setTitle("NEW CHAPTER");
		nc.setUserAccount(ua);
		
		final UserAccount ua2 = new UserAccount();
		ua2.setPassword("chapter1");
		ua2.setUsername("chapter1");
		final Collection<Authority> coll2 = new ArrayList<Authority>();
		final Authority a2 = new Authority();
		a.setAuthority(Authority.CHAPTER);
		coll2.add(a2);
		ua2.setAuthorities(coll2);
		final Chapter nc2 = this.cs.create();
		nc2.setAddress("Sample address");
		nc2.setBan(false);
		nc2.setEmail("");
		nc2.setFlagSpam(false);
		nc2.setMiddleName("Sample middle name");
		nc2.setName("Sample");
		nc2.setPhoneNumber("+34 1231456789");
		nc2.setPhoto("http://www.sample.com");
		nc2.setSurname("Sample surname");
		nc2.setTitle("NEW CHAPTER");
		nc2.setUserAccount(ua2);
		
	final Object testingData[][] = {
			
			/**
			 * TESTING REQUIREMENT #1
			 * POSITIVE TEST
				 * COVERED INSTRUCTIONS: 100%
				 * COVERED DATA: 10%
			 * */
			{
			"brotherhood1", nc, null
		}, 
		
		/**
		 * TESTING REQUIREMENT #1
		 * NEGATIVE TEST: YOU CANNOT EDIT A LINK RECORD WITH EMPTY OR NULL VALUES
		 * (Expected ConstraintViolationException)
				 * COVERED INSTRUCTIONS: 100%
				 * COVERED DATA: 10%
		 * */
		{
			"brotherhood1", nc2, DataIntegrityViolationException.class
		}
		
	};

	for (int i = 0; i < testingData.length; i++)
		this.template2((String) testingData[i][0], (Chapter) testingData[i][1], (Class<?>) testingData[i][2]);
}

protected void template2(final String username, final Chapter lr, final Class<?> expected) {

	Class<?> caught;

	caught = null;

	try {
		this.authenticate(username);
		this.cs.save(lr);

		this.unauthenticate();

	} catch (final Throwable oops) {
		caught = oops.getClass();
	}

	super.checkExceptions(expected, caught);
}
}
