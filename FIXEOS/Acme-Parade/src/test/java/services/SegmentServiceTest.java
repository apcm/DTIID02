package services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;



import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import domain.Parade;
import domain.Segment;

import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@Transactional
public class SegmentServiceTest extends AbstractTest{
	
		//SUT
		@Autowired
		private SegmentService	segmentService;
		
		@Autowired
		private ParadeService	paradeService;
		
		
		@Test
		public void editsegment() {
			final Object testingData[][] = {
					/**
					 * TESTING REQUIREMENT #1
					 * POSITIVE TEST
					 * COVERED INSTRUCTIONS: 100%
					 * COVERED DATA: 20%
					 * */
					{
					"brotherhood3", "segment7", null
				}, 
				
				/**
				 * TESTING REQUIREMENT #1
				 * NEGATIVE TEST: YOU CANNOT EDIT A SEGMENT IF YOU ARE NOT THE OWNER
				 * (Expected IllegalArgumentException)
					 * COVERED INSTRUCTIONS: 100%
					 * COVERED DATA: 20%
				 * */
				{
					"brotherhood2", "segment7", IllegalArgumentException.class
				}
			};

			for (int i = 0; i < testingData.length; i++)
				this.template((String) testingData[i][0], super.getEntityId((String) testingData[i][1]), (Class<?>) testingData[i][2]);
		}

		protected void template(final String username, final int id, final Class<?> expected) {
			Parade parade1 = this.paradeService.findOne(super.getEntityId("parade4"));

			Class<?> caught;

			caught = null;

			try {
				this.authenticate(username);
				final Segment segment = this.segmentService.findOne(id);
				this.paradeService.saveSegmentInParade(segment, parade1);

				this.unauthenticate();

			} catch (final Throwable oops) {
				caught = oops.getClass();
			}

			super.checkExceptions(expected, caught);
		}
		
		@Test
		public void createSegment() throws ParseException {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			
			Parade parade1 = this.paradeService.findOne(super.getEntityId("parade4"));
			
			final Segment savetest1 = this.segmentService.create(parade1.getId());
			savetest1.setOrigLatitude(1.0);			
			savetest1.setOrigLongitude(1.2);
			savetest1.setDestLatitude(1.3);
			savetest1.setDestLongitude(1.4);
			Date d1 = sdf.parse("12/12/2019 12:00:00");
			savetest1.setStartTime(d1);
			Date d2 = sdf.parse("12/12/2019 14:00:00");
			savetest1.setArriveTime(d2);
			savetest1.setSegmentOrder(1);
			
			final Segment savetest2 = this.segmentService.create(parade1.getId());
			savetest2.setOrigLatitude(1.0);			
			savetest2.setOrigLongitude(1.2);
			savetest2.setDestLatitude(1.3);
			savetest2.setDestLongitude(1.4);
			Date d3 = sdf.parse("12/12/2019 12:00:00");
			savetest2.setStartTime(d3);
			savetest2.setArriveTime(null);
			savetest2.setSegmentOrder(2);
			
			final Segment savetest3 = this.segmentService.create(parade1.getId());
			savetest3.setOrigLatitude(1.0);			
			savetest3.setOrigLongitude(1.2);
			savetest3.setDestLatitude(1.3);
			savetest3.setDestLongitude(1.4);
			savetest3.setStartTime(null);
			Date d6 = sdf.parse("12/12/2019 14:00:00");
			savetest3.setArriveTime(d6);
			savetest3.setSegmentOrder(2);
			
			final Segment savetest4 = this.segmentService.create(parade1.getId());
			savetest4.setOrigLatitude(null);			
			savetest4.setOrigLongitude(null);
			savetest4.setDestLatitude(null);
			savetest4.setDestLongitude(null);
			savetest4.setStartTime(null);
			savetest4.setArriveTime(null);
			savetest4.setSegmentOrder(2);
			
			
			final Object testingData[][] = {
				
					/**
					 * TESTING REQUIREMENT #1
					 * POSITIVE TEST
					 * COVERED INSTRUCTIONS: 100%
					 * COVERED DATA: 20%
					 * */
					{
					"brotherhood3", savetest1, null
				}, 
				
				/**
				 * TESTING REQUIREMENT #1
				 * NEGATIVE TEST: NULL VALUES
				 * (Expected IllegalArgumentException)
					 * COVERED INSTRUCTIONS: 100%
					 * COVERED DATA: 20%
				 * */
				{
					"brotherhood3", savetest2, IllegalArgumentException.class
				},
				/**
				 * TESTING REQUIREMENT #1
				 * NEGATIVE TEST: NULL VALUES
				 * (Expected IllegalArgumentException)
					 * COVERED INSTRUCTIONS: 100%
					 * COVERED DATA: 20%
				 * */
				
				{
					"brotherhood3", savetest3, IllegalArgumentException.class
				},
				/**
				 * TESTING REQUIREMENT #1
				 * NEGATIVE TEST: FAIL DATES
				 * (Expected IllegalArgumentException)
					 * COVERED INSTRUCTIONS: 100%
					 * COVERED DATA: 20%
				 * */
				
				{
					"brotherhood3", savetest4, IllegalArgumentException.class
				}
				
			};

			for (int i = 0; i < testingData.length; i++)
				this.template2((String) testingData[i][0], (Segment) testingData[i][1], (Class<?>) testingData[i][2]);
		}

		protected void template2(final String username, final Segment segment, final Class<?> expected) {

			Parade parade1 = this.paradeService.findOne(super.getEntityId("parade4"));
			
			Class<?> caught;

			caught = null;

			try {
				this.authenticate(username);
				this.segmentService.isCorrectDate(segment, parade1);
				this.paradeService.saveSegmentInParade(segment, parade1);

				this.unauthenticate();

			} catch (final Throwable oops) {
				caught = oops.getClass();
			}

			super.checkExceptions(expected, caught);
		}

}
