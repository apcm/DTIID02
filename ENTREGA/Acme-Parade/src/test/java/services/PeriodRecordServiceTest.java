
package services;

import java.util.ArrayList;

import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.LegalRecord;
import domain.PeriodRecord;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@Transactional
public class PeriodRecordServiceTest extends AbstractTest {

	//SUT
	@Autowired
	private PeriodRecordService	prs;

	/**
	 * THIS TEST IS FOR TESTING THE REQUIREMENT #1
	 * THERE ARE 2 GOOD TESTING CASES, AND 9 BAD TESTING CASES
	 * COVERED INSTRUCTIONS IN THIS TEST: 100%
	 * COVERED INSTRUCTIONS IN LegalRecordService: 73.7%
	 * COVERED DATA IN THIS TEST: 75%
	 * */
	
	@Test
	public void editRecord() {
		final Object testingData[][] = {
			
				/**
				 * TESTING REQUIREMENT #1
				 * POSITIVE TEST
				 * COVERED INSTRUCTIONS: 100%
				 * COVERED DATA: 20%
				 * */
				{
				"brotherhood1", "periodRecord1", null
			}, 
			
			/**
			 * TESTING REQUIREMENT #1
			 * NEGATIVE TEST: YOU CANNOT EDIT A LEGAL RECORD IF YOU ARE NOT THE OWNER
			 * (Expected IllegalArgumentException)
				 * COVERED INSTRUCTIONS: 100%
				 * COVERED DATA: 20%
			 * */
			{
				"brotherhood2", "periodRecord1", IllegalArgumentException.class
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
			final PeriodRecord lr = this.prs.findOne(id);
			this.prs.save(lr);

			this.unauthenticate();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		super.checkExceptions(expected, caught);
	}
	
	@Test
	public void createRecord() {
		final PeriodRecord savetest = this.prs.create();
		savetest.setDescription("Sample");
		savetest.setTitle("SAMPLE");
		savetest.setEndYear(2021);
		savetest.setPhotos(new ArrayList<String>());
		savetest.setStartYear(2020);
		
		final PeriodRecord savetest2 = this.prs.create();
		savetest2.setDescription("");
		savetest2.setTitle("SAMPLE");
		savetest2.setEndYear(2021);
		savetest2.setPhotos(new ArrayList<String>());
		savetest2.setStartYear(2020);
		
		final PeriodRecord savetest3 = this.prs.create();
		savetest3.setDescription("Sample");
		savetest3.setTitle("");
		savetest3.setEndYear(2021);
		savetest3.setPhotos(new ArrayList<String>());
		savetest3.setStartYear(2020);
		
		final PeriodRecord savetest4 = this.prs.create();
		savetest4.setDescription("Sample");
		savetest4.setTitle("SAMPLE");
		savetest4.setEndYear(null);
		savetest4.setPhotos(new ArrayList<String>());
		savetest4.setStartYear(2020);
		
		final PeriodRecord savetest5 = this.prs.create();
		savetest5.setDescription("Sample");
		savetest5.setTitle("SAMPLE");
		savetest5.setEndYear(2021);
		savetest5.setPhotos(new ArrayList<String>());
		savetest5.setStartYear(null);
		
		final PeriodRecord savetest6 = this.prs.create();
		savetest6.setDescription(null);
		savetest6.setTitle("SAMPLE");
		savetest6.setEndYear(2021);
		savetest6.setPhotos(new ArrayList<String>());
		savetest6.setStartYear(2020);
		
		final PeriodRecord savetest7 = this.prs.create();
		savetest7.setDescription("Sample");
		savetest7.setTitle(null);
		savetest7.setEndYear(2021);
		savetest7.setPhotos(new ArrayList<String>());
		savetest7.setStartYear(2020);
		
		final PeriodRecord savetest8 = this.prs.create();
		savetest8.setDescription("Sample");
		savetest8.setTitle("SAMPLE");
		savetest8.setEndYear(2021);
		savetest8.setPhotos(new ArrayList<String>());
		savetest8.setStartYear(2020);
		
		final PeriodRecord savetest9 = this.prs.create();
		savetest9.setDescription("A very long textA very long textA very long textA very long textA very long textA very long textA very long textA very long textA very long textA very long textA very long textA very long textA very long textA very long textA very long textA very long textA very long textA very long textA very long textA very long text");
		savetest9.setTitle("A very long textA very long textA very long textA very long textA very long textA very long textA very long textA very long textA very long textA very long textA very long textA very long textA very long textA very long textA very long textA very long textA very long textA very long textA very long textA very long textA very long text");
		savetest9.setEndYear(2021);
		savetest9.setPhotos(new ArrayList<String>());
		savetest9.setStartYear(2020);
		
		
		final Object testingData[][] = {
			
				/**
				 * TESTING REQUIREMENT #1
				 * POSITIVE TEST
				 * COVERED INSTRUCTIONS: 100%
				 * COVERED DATA: 20%
				 * */
				{
				"brotherhood1", savetest, null
			}, 
			
			/**
			 * TESTING REQUIREMENT #1
			 * NEGATIVE TEST: YOU CANNOT EDIT A LINK RECORD WITH EMPTY OR NULL VALUES
			 * (Expected ConstraintViolationException)
				 * COVERED INSTRUCTIONS: 100%
				 * COVERED DATA: 20%
			 * */
			{
				"brotherhood1", savetest2, ConstraintViolationException.class
			},
			/**
			 * TESTING REQUIREMENT #1
			 * NEGATIVE TEST: YOU CANNOT EDIT A LINK RECORD WITH EMPTY OR NULL VALUES
			 * (Expected ConstraintViolationException)
				 * COVERED INSTRUCTIONS: 100%
				 * COVERED DATA: 20%
			 * */
			
			{
				"brotherhood1", savetest3, ConstraintViolationException.class
			},
			/**
			 * TESTING REQUIREMENT #1
			 * NEGATIVE TEST: YOU CANNOT EDIT A LINK RECORD WITH EMPTY OR NULL VALUES
			 * (Expected ConstraintViolationException)
				 * COVERED INSTRUCTIONS: 100%
				 * COVERED DATA: 20%
			 * */
			
			{
				"brotherhood1", savetest4, ConstraintViolationException.class
			},		
			/**
			 * TESTING REQUIREMENT #1
			 * NEGATIVE TEST: YOU CANNOT EDIT A LINK RECORD WITH EMPTY OR NULL VALUES
			 * (Expected ConstraintViolationException)
				 * COVERED INSTRUCTIONS: 100%
				 * COVERED DATA: 20%
			 * */
			{
				"brotherhood1", savetest5, ConstraintViolationException.class
			},
			/**
			 * TESTING REQUIREMENT #1
			 * NEGATIVE TEST: YOU CANNOT EDIT A LINK RECORD WITH EMPTY OR NULL VALUES
			 * (Expected ConstraintViolationException)
				 * COVERED INSTRUCTIONS: 100%
				 * COVERED DATA: 20%
			 * */
			{
				"brotherhood1", savetest6, ConstraintViolationException.class
			},
			/**
			 * TESTING REQUIREMENT #1
			 * NEGATIVE TEST: YOU CANNOT EDIT A LINK RECORD WITH EMPTY OR NULL VALUES
			 * (Expected ConstraintViolationException)
				 * COVERED INSTRUCTIONS: 100%
				 * COVERED DATA: 20%
			 * */
			{
				"brotherhood1", savetest7, ConstraintViolationException.class
			},
			/**
			 * TESTING REQUIREMENT #1
			 * NEGATIVE TEST: YOU CANNOT EDIT A LINK RECORD WITH EMPTY OR NULL VALUES
			 * (Expected ConstraintViolationException)
				 * COVERED INSTRUCTIONS: 100%
				 * COVERED DATA: 20%
			 * */
			{
				"brotherhood1", savetest8, ConstraintViolationException.class
			},
			/**
			 * TESTING REQUIREMENT #1
			 * NEGATIVE TEST: YOU CANNOT EDIT A LINK RECORD WITH EMPTY OR NULL VALUES
			 * (Expected ConstraintViolationException)
				 * COVERED INSTRUCTIONS: 100%
				 * COVERED DATA: 20%
			 * */
			{
				"brotherhood1", savetest9, ConstraintViolationException.class
			},
			
		};

		for (int i = 0; i < testingData.length; i++)
			this.template2((String) testingData[i][0], (PeriodRecord) testingData[i][1], (Class<?>) testingData[i][2]);
	}

	protected void template2(final String username, final PeriodRecord lr, final Class<?> expected) {

		Class<?> caught;

		caught = null;

		try {
			this.authenticate(username);
			this.prs.save(lr);

			this.unauthenticate();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		super.checkExceptions(expected, caught);
	}
	
	
}
