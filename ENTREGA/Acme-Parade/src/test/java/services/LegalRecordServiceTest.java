
package services;

import java.util.ArrayList;

import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import utilities.AbstractTest;
import domain.LegalRecord;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@Transactional
public class LegalRecordServiceTest extends AbstractTest {

	//SUT
	@Autowired
	private LegalRecordService	lrs;

	/**
	 * THIS TEST IS FOR TESTING THE REQUIREMENT #1
	 * THERE ARE 2 GOOD TESTING CASES, AND 12 BAD TESTING CASES
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
				 * COVERED DATA: 20%
				 * */
				{
				"brotherhood1", "legalRecord1", null
			}, 
			
			/**
			 * TESTING REQUIREMENT #1
			 * NEGATIVE TEST: YOU CANNOT EDIT A LEGAL RECORD IF YOU ARE NOT THE OWNER
			 * (Expected IllegalArgumentException)
			 * COVERED INSTRUCTIONS: 100%
			 * COVERED DATA: 20%
			 * */
			{
				"brotherhood2", "legalRecord1", IllegalArgumentException.class
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
			final LegalRecord lr = this.lrs.findOne(id);
			this.lrs.save(lr);

			this.unauthenticate();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		super.checkExceptions(expected, caught);
	}
	
	@Test
	public void createRecord() {
		final LegalRecord savetest = this.lrs.create();
		savetest.setLegalName("Sample");
		savetest.setDescription("Sample");
		savetest.setTitle("SAMPLE");
		savetest.setApplicableLaws(new ArrayList<String>());
		savetest.setVatNumber(7);
		
		final LegalRecord savetest2 = this.lrs.create();
		savetest2.setLegalName("");
		savetest2.setDescription("Sample");
		savetest2.setTitle("SAMPLE");
		savetest2.setApplicableLaws(new ArrayList<String>());
		savetest2.setVatNumber(7);
		
		final LegalRecord savetest3 = this.lrs.create();
		savetest3.setLegalName("Sample");
		savetest3.setDescription("");
		savetest3.setTitle("SAMPLE");
		savetest3.setApplicableLaws(new ArrayList<String>());
		savetest3.setVatNumber(7);
		
		final LegalRecord savetest4 = this.lrs.create();
		savetest4.setLegalName("Sample");
		savetest4.setDescription("Sample");
		savetest4.setTitle("");
		savetest4.setApplicableLaws(new ArrayList<String>());
		savetest4.setVatNumber(7);
		
		final LegalRecord savetest5 = this.lrs.create();
		savetest5.setLegalName("Sample");
		savetest5.setDescription("Sample");
		savetest5.setTitle("SAMPLE");
		savetest5.setApplicableLaws(null);
		savetest5.setVatNumber(-700);
		
		
		final LegalRecord savetest6 = this.lrs.create();
		savetest6.setLegalName("Sample");
		savetest6.setDescription("Sample");
		savetest6.setTitle("SAMPLE");
		savetest6.setApplicableLaws(new ArrayList<String>());
		savetest6.setVatNumber(null);
		
		final LegalRecord savetest7 = this.lrs.create();
		savetest7.setLegalName(null);
		savetest7.setDescription("Sample");
		savetest7.setTitle("SAMPLE");
		savetest7.setApplicableLaws(new ArrayList<String>());
		savetest7.setVatNumber(7);
		
		final LegalRecord savetest8 = this.lrs.create();
		savetest8.setLegalName("Sample");
		savetest8.setDescription(null);
		savetest8.setTitle("SAMPLE");
		savetest8.setApplicableLaws(new ArrayList<String>());
		savetest8.setVatNumber(7);
		
		final LegalRecord savetest9 = this.lrs.create();
		savetest9.setLegalName("Sample");
		savetest9.setDescription("Sample");
		savetest9.setTitle(null);
		savetest9.setApplicableLaws(new ArrayList<String>());
		savetest9.setVatNumber(7);
		
		final LegalRecord savetestA = this.lrs.create();
		savetestA.setLegalName("This is a not-fair long text that will bug my database.This is a not-fair long text that will bug my database.This is a not-fair long text that will bug my database.This is a not-fair long text that will bug my database.This is a not-fair long text that will bug my database.This is a not-fair long text that will bug my database.");
		savetestA.setDescription("Sample");
		savetestA.setTitle("SAMPLE");
		savetestA.setApplicableLaws(new ArrayList<String>());
		savetestA.setVatNumber(7);
		
		final LegalRecord savetestB = this.lrs.create();
		savetestB.setLegalName("Sample");
		savetestB.setDescription("Sample");
		savetestB.setTitle("This is a not-fair long text that will bug my database.This is a not-fair long text that will bug my database.This is a not-fair long text that will bug my database.This is a not-fair long text that will bug my database.This is a not-fair long text that will bug my database.This is a not-fair long text that will bug my database.");
		savetestB.setApplicableLaws(new ArrayList<String>());
		savetestB.setVatNumber(7);
		
		final LegalRecord savetestC = this.lrs.create();
		savetestC.setLegalName("This is a not-fair long text that will bug my database.This is a not-fair long text that will bug my database.This is a not-fair long text that will bug my database.This is a not-fair long text that will bug my database.This is a not-fair long text that will bug my database.This is a not-fair long text that will bug my database.");
		savetestC.setDescription("Sample");
		savetestC.setTitle("SAMPLE");
		savetestC.setApplicableLaws(new ArrayList<String>());
		savetestC.setVatNumber(7);
		
		
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
			 * NEGATIVE TEST: YOU CANNOT EDIT A LEGAL RECORD WITH EMPTY OR NULL VALUES
			 * (Expected ConstraintViolationException)
			 * COVERED INSTRUCTIONS: 100%
			 * COVERED DATA: 20%
			 * */
			{
				"brotherhood1", savetest2, ConstraintViolationException.class
			},
			/**
			 * TESTING REQUIREMENT #1
			 * NEGATIVE TEST: YOU CANNOT EDIT A LEGAL RECORD WITH EMPTY OR NULL VALUES
			 * (Expected ConstraintViolationException)
			 * COVERED INSTRUCTIONS: 100%
			 * COVERED DATA: 20%
			 * */
			
			{
				"brotherhood1", savetest3, ConstraintViolationException.class
			},
			/**
			 * TESTING REQUIREMENT #1
			 * NEGATIVE TEST: YOU CANNOT EDIT A LEGAL RECORD WITH EMPTY OR NULL VALUES
			 * (Expected ConstraintViolationException)
			 * COVERED INSTRUCTIONS: 100%
			 * COVERED DATA: 20%
			 * */
			
			{
				"brotherhood1", savetest4, ConstraintViolationException.class
			},		
			/**
			 * TESTING REQUIREMENT #1
			 * NEGATIVE TEST: YOU CANNOT EDIT A LEGAL RECORD WITH EMPTY OR NULL VALUES
			 * (Expected ConstraintViolationException)
			 * COVERED INSTRUCTIONS: 100%
			 * COVERED DATA: 20%
			 * */
			{
				"brotherhood1", savetest5, ConstraintViolationException.class
			},		
			/**
			 * TESTING REQUIREMENT #1
			 * NEGATIVE TEST: YOU CANNOT EDIT A LEGAL RECORD WITH EMPTY OR NULL VALUES
			 * (Expected ConstraintViolationException)
			 * COVERED INSTRUCTIONS: 100%
			 * COVERED DATA: 20%
			 * */
			{
				"brotherhood1", savetest6, ConstraintViolationException.class
			},		
			/**
			 * TESTING REQUIREMENT #1
			 * NEGATIVE TEST: YOU CANNOT EDIT A LEGAL RECORD WITH EMPTY OR NULL VALUES
			 * (Expected ConstraintViolationException)
			 * COVERED INSTRUCTIONS: 100%
			 * COVERED DATA: 20%
			 * */
			{
				"brotherhood1", savetest7, ConstraintViolationException.class
			},	
			/**
			 * TESTING REQUIREMENT #1
			 * NEGATIVE TEST: YOU CANNOT EDIT A LEGAL RECORD WITH EMPTY OR NULL VALUES
			 * (Expected ConstraintViolationException)
			 * COVERED INSTRUCTIONS: 100%
			 * COVERED DATA: 20%
			 * */
			{
				"brotherhood1", savetest8, ConstraintViolationException.class
			},	/**
			 * TESTING REQUIREMENT #1
			 * NEGATIVE TEST: YOU CANNOT EDIT A LEGAL RECORD WITH EMPTY OR NULL VALUES
			 * (Expected ConstraintViolationException)
			 * COVERED INSTRUCTIONS: 100%
			 * COVERED DATA: 20%
			 * */
			{
				"brotherhood1", savetest9, ConstraintViolationException.class
			},	/**
			 * TESTING REQUIREMENT #1
			 * NEGATIVE TEST: YOU CANNOT EDIT A LEGAL RECORD WITH NOT VALID VALUES
			 * (Expected ConstraintViolationException)
			 * COVERED INSTRUCTIONS: 100%
			 * COVERED DATA: 20%
			 * */
			{
				"brotherhood1", savetestA, ConstraintViolationException.class
			},	/**
			 * TESTING REQUIREMENT #1
			 * NEGATIVE TEST: YOU CANNOT EDIT A LEGAL RECORD WITH NOT VALID VALUES
			 * (Expected ConstraintViolationException)
			 * COVERED INSTRUCTIONS: 100%
			 * COVERED DATA: 20%
			 * */
			{
				"brotherhood1", savetestB, ConstraintViolationException.class
			},	/**
			 * TESTING REQUIREMENT #1
			 * NEGATIVE TEST: YOU CANNOT EDIT A LEGAL RECORD WITH NOT VALID VALUES
			 * (Expected ConstraintViolationException)
			 * COVERED INSTRUCTIONS: 100%
			 * COVERED DATA: 20%
			 * */
			{
				"brotherhood1", savetestC, ConstraintViolationException.class
			},	
			
		};

		for (int i = 0; i < testingData.length; i++)
			this.template2((String) testingData[i][0], (LegalRecord) testingData[i][1], (Class<?>) testingData[i][2]);
	}

	protected void template2(final String username, final LegalRecord lr, final Class<?> expected) {

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
