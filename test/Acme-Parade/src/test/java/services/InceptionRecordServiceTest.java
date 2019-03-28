
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
import domain.InceptionRecord;
import domain.InceptionRecord;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@Transactional
public class InceptionRecordServiceTest extends AbstractTest {

	//SUT
	@Autowired
	private InceptionRecordService	lrs;
	
	
	/**
	 * THIS TEST IS FOR TESTING THE REQUIREMENT #1
	 * THERE ARE 2 GOOD TESTING CASES, AND 6 BAD TESTING CASES
	 * COVERED INSTRUCTIONS IN THIS TEST: 100%
	 * COVERED INSTRUCTIONS IN LegalRecordService: 70.9%
	 * COVERED DATA IN THIS TEST: 93%
	 * */
	
	
	
	@Test
	public void editRecord() {
		final Object testingData[][] = {
			
				/**
				 * TESTING REQUIREMENT #1
				 * POSITIVE TEST
				 * COVERED INSTRUCTIONS: 100%
				 * COVERED DATA: 33%
				 * */
				{
				"brotherhood1", "inceptionRecord1", null
			}, 
			
			/**
			 * TESTING REQUIREMENT #1
			 * NEGATIVE TEST: YOU CANNOT EDIT A LEGAL RECORD IF YOU ARE NOT THE OWNER
			 * (Expected IllegalArgumentException)
				 * COVERED INSTRUCTIONS: 100%
				 * COVERED DATA: 33%
			 * */
			{
				"brotherhood2", "inceptionRecord1", IllegalArgumentException.class
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
			final InceptionRecord lr = this.lrs.findOne(id);
			this.lrs.save(lr);

			this.unauthenticate();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		super.checkExceptions(expected, caught);
	}
	
	@Test
	public void createRecord() {
		final InceptionRecord savetest = this.lrs.create();
		savetest.setDescription("Sample");
		savetest.setTitle("SAMPLE");
		savetest.setPhotos(new ArrayList<String>());
		
		final InceptionRecord savetest2 = this.lrs.create();
		savetest2.setDescription("");
		savetest2.setTitle("SAMPLE");
		savetest2.setPhotos(new ArrayList<String>());
		
		final InceptionRecord savetest3 = this.lrs.create();
		savetest3.setDescription("Sample");
		savetest3.setTitle("");
		savetest3.setPhotos(new ArrayList<String>());
		
		final InceptionRecord savetest4 = this.lrs.create();
		savetest4.setDescription(null);
		savetest4.setTitle("SAMPLE");
		savetest4.setPhotos(new ArrayList<String>());
		
		final InceptionRecord savetest5 = this.lrs.create();
		savetest5.setDescription("Sample");
		savetest5.setTitle(null);
		savetest5.setPhotos(new ArrayList<String>());
		
		final InceptionRecord savetest6 = this.lrs.create();
		savetest6.setDescription("A very Long Text A very Long Text A very Long Text A very Long Text A very Long Text A very Long Text A very Long Text A very Long Text A very Long Text A very Long Text A very Long Text A very Long Text A very Long Text A very Long Text A very Long Text A very Long Text A very Long Text A very Long Text ");
		savetest6.setTitle("SAMPLE");
		savetest6.setPhotos(new ArrayList<String>());
		
		final InceptionRecord savetest7 = this.lrs.create();
		savetest7.setDescription("Sample");
		savetest7.setTitle("A very Long Text A very Long Text A very Long Text A very Long Text A very Long Text A very Long Text A very Long Text A very Long Text A very Long Text A very Long Text A very Long Text A very Long Text A very Long Text A very Long Text A very Long Text A very Long Text A very Long Text A very Long Text A very Long Text A very Long Text ");
		savetest7.setPhotos(new ArrayList<String>());
		
		final InceptionRecord savetest8 = this.lrs.create();
		savetest8.setDescription("Sample");
		savetest8.setTitle("SAMPLE");
		savetest8.setPhotos(null);
		
		final Object testingData[][] = {
			
				/**
				 * TESTING REQUIREMENT #1
				 * POSITIVE TEST
				 * COVERED INSTRUCTIONS: 100%
				 * COVERED DATA: 33%
				 * */
				{
				"brotherhood1", savetest, null
			}, 
			
			/**
			 * TESTING REQUIREMENT #1
			 * NEGATIVE TEST: YOU CANNOT EDIT A LINK RECORD WITH EMPTY OR NULL VALUES
			 * (Expected ConstraintViolationException)
				 * COVERED INSTRUCTIONS: 100%
				 * COVERED DATA: 33%
			 * */
			{
				"brotherhood1", savetest2, ConstraintViolationException.class
			},
			/**
			 * TESTING REQUIREMENT #1
			 * NEGATIVE TEST: YOU CANNOT EDIT A LINK RECORD WITH EMPTY OR NULL VALUES
			 * (Expected ConstraintViolationException)
				 * COVERED INSTRUCTIONS: 100%
				 * COVERED DATA: 33%
			 * */
			
			{
				"brotherhood1", savetest3, ConstraintViolationException.class
			},
			/**
			 * TESTING REQUIREMENT #1
			 * NEGATIVE TEST: YOU CANNOT EDIT A LINK RECORD WITH EMPTY OR NULL VALUES
			 * (Expected ConstraintViolationException)
				 * COVERED INSTRUCTIONS: 100%
				 * COVERED DATA: 33%
			 * */
			
			{
				"brotherhood1", savetest4, ConstraintViolationException.class
			},		
			/**
			 * TESTING REQUIREMENT #1
			 * NEGATIVE TEST: YOU CANNOT EDIT A LINK RECORD WITH EMPTY OR NULL VALUES
			 * (Expected ConstraintViolationException)
				 * COVERED INSTRUCTIONS: 100%
				 * COVERED DATA: 33%
			 * */
			{
				"brotherhood1", savetest5, ConstraintViolationException.class
			},
			/**
			 * TESTING REQUIREMENT #1
			 * NEGATIVE TEST: YOU CANNOT EDIT A LINK RECORD WITH EMPTY OR NULL VALUES
			 * (Expected ConstraintViolationException)
				 * COVERED INSTRUCTIONS: 100%
				 * COVERED DATA: 33%
			 * */
			{
				"brotherhood1", savetest6, ConstraintViolationException.class
			},
			/**
			 * TESTING REQUIREMENT #1
			 * NEGATIVE TEST: YOU CANNOT EDIT A LINK RECORD WITH EMPTY OR NULL VALUES
			 * (Expected ConstraintViolationException)
				 * COVERED INSTRUCTIONS: 100%
				 * COVERED DATA: 33%
			 * */
			{
				"brotherhood1", savetest7, ConstraintViolationException.class
			},
			/**
			 * TESTING REQUIREMENT #1
			 * NEGATIVE TEST: YOU CANNOT EDIT A LINK RECORD WITH EMPTY OR NULL VALUES
			 * (Expected ConstraintViolationException)
				 * COVERED INSTRUCTIONS: 100%
				 * COVERED DATA: 33%
			 * */
			{
				"brotherhood1", savetest8, ConstraintViolationException.class
			}
			
		};

		for (int i = 0; i < testingData.length; i++)
			this.template2((String) testingData[i][0], (InceptionRecord) testingData[i][1], (Class<?>) testingData[i][2]);
	}

	protected void template2(final String username, final InceptionRecord lr, final Class<?> expected) {

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
