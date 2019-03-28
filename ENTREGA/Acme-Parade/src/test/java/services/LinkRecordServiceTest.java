
package services;

import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.LinkRecord;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@Transactional
public class LinkRecordServiceTest extends AbstractTest {

	//SUT
	@Autowired
	private LinkRecordService	lrs;
	/**
	 * THIS TEST IS FOR TESTING THE REQUIREMENT #1
	 * THERE ARE TWO GOOD TESTING CASES, AND 8 BAD TESTING CASES
	 * COVERED INSTRUCTIONS IN THIS TEST: 100%
	 * COVERED INSTRUCTIONS IN LinkRecordService: 70.9%
	 * COVERED DATA IN THIS TEST: 70%
	 * */

	@Test
	public void editRecord() {
		final Object testingData[][] = {
			
				/**
				 * TESTING REQUIREMENT #1
				 * POSITIVE TEST
				 * COVERED INSTRUCTIONS: 100%
				 * COVERED DATA: 25%
				 * */
				{
				"brotherhood1", "linkRecord1", null
			}, 
			
			/**
			 * TESTING REQUIREMENT #1
			 * NEGATIVE TEST: YOU CANNOT EDIT A LINK RECORD IF YOU ARE NOT THE OWNER
			 * (Expected IllegalArgumentException)
			 *	COVERED INSTRUCTIONS: 100%
			 * COVERED DATA: 25%
			 * */
			{
				"brotherhood2", "linkRecord1", IllegalArgumentException.class
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.template((String) testingData[i][0], super.getEntityId((String) testingData[i][1]), (Class<?>) testingData[i][2]);
	}

	protected void template(final String username, final int id, final Class<?> expected) {

		Class<?> caught;

		caught = null;

		try {
			this.authenticate(username);
			final LinkRecord lr = this.lrs.findOne(id);
			this.lrs.save(lr);

			this.unauthenticate();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		super.checkExceptions(expected, caught);
	}
	
	
	
	@Test
	public void createRecord() {
		final LinkRecord savetest = this.lrs.create();
		savetest.setDescription("Sample");
		savetest.setTitle("SAMPLE");
		savetest.setLink("http://www.sample.com");
		
		final LinkRecord savetest2 = this.lrs.create();
		savetest2.setDescription("");
		savetest2.setTitle("SAMPLE");
		savetest2.setLink("http://www.sample.com");
		
		final LinkRecord savetest3 = this.lrs.create();
		savetest3.setDescription("Sample");
		savetest3.setTitle("");
		savetest3.setLink("http://www.sample.com");
		
		final LinkRecord savetest4 = this.lrs.create();
		savetest4.setDescription("Sample");
		savetest4.setTitle("SAMPLE");
		savetest4.setLink("");
		
		final LinkRecord savetest5 = this.lrs.create();
		savetest5.setDescription("Sample");
		savetest5.setTitle("SAMPLE");
		savetest5.setLink(null);
		
		
		final LinkRecord savetest6 = this.lrs.create();
		savetest6.setDescription(null);
		savetest6.setTitle("SAMPLE");
		savetest6.setLink("http://www.sample.com");
		
		final LinkRecord savetest7 = this.lrs.create();
		savetest7.setDescription("Sample");
		savetest7.setTitle(null);
		savetest7.setLink("http://www.sample.com");
		
		final Object testingData[][] = {
			
				/**
				 * TESTING REQUIREMENT #1
				 * POSITIVE TEST
				 * COVERED INSTRUCTIONS: 100%
				 * COVERED DATA: 25%
				 * */
				{
				"brotherhood1", savetest, null
			}, 
			
			/**
			 * TESTING REQUIREMENT #1
			 * NEGATIVE TEST: YOU CANNOT EDIT A LINK RECORD WITH EMPTY OR NULL VALUES
			 * (Expected ConstraintViolationException)
			 * COVERED INSTRUCTIONS: 100%
			 * COVERED DATA: 25%
			 * */
			{
				"brotherhood1", savetest2, ConstraintViolationException.class
			},
			/**
			 * TESTING REQUIREMENT #1
			 * NEGATIVE TEST: YOU CANNOT EDIT A LINK RECORD WITH EMPTY OR NULL VALUES
			 * (Expected ConstraintViolationException)
			 * COVERED INSTRUCTIONS: 100%
			 * COVERED DATA: 25%
			 * */
			
			{
				"brotherhood1", savetest3, ConstraintViolationException.class
			},
			/**
			 * TESTING REQUIREMENT #1
			 * NEGATIVE TEST: YOU CANNOT EDIT A LINK RECORD WITH EMPTY OR NULL VALUES
			 * (Expected ConstraintViolationException)
			 * COVERED INSTRUCTIONS: 100%
			 * COVERED DATA: 25%
			 * */
			
			{
				"brotherhood1", savetest4, ConstraintViolationException.class
			},		
			/**
			 * TESTING REQUIREMENT #1
			 * NEGATIVE TEST: YOU CANNOT EDIT A LINK RECORD WITH EMPTY OR NULL VALUES
			 * (Expected ConstraintViolationException)
			 * COVERED INSTRUCTIONS: 100%
			 * COVERED DATA: 25%
			 * */
			{
				"brotherhood1", savetest5, ConstraintViolationException.class
			},		
			/**
			 * TESTING REQUIREMENT #1
			 * NEGATIVE TEST: YOU CANNOT EDIT A LINK RECORD WITH EMPTY OR NULL VALUES
			 * (Expected ConstraintViolationException)
			 * COVERED INSTRUCTIONS: 100%
			 * COVERED DATA: 25%
			 * */
			{
				"brotherhood1", savetest6, ConstraintViolationException.class
			},		
			/**
			 * TESTING REQUIREMENT #1
			 * NEGATIVE TEST: YOU CANNOT EDIT A LINK RECORD WITH EMPTY OR NULL VALUES
			 * (Expected ConstraintViolationException)
			 * COVERED INSTRUCTIONS: 100%
			 * COVERED DATA: 25%
			 * */
			{
				"brotherhood1", savetest7, ConstraintViolationException.class
			}	
			
		};

		for (int i = 0; i < testingData.length; i++)
			this.template2((String) testingData[i][0], (LinkRecord) testingData[i][1], (Class<?>) testingData[i][2]);
	}

	protected void template2(final String username, final LinkRecord lr, final Class<?> expected) {

		Class<?> caught;

		caught = null;

		try {
			this.authenticate(username);
			this.lrs.save(lr);

			this.unauthenticate();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		super.checkExceptions(expected, caught);
	}
}
