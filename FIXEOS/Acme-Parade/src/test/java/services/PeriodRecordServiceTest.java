
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


	@Test
	public void testSavePeriodRecord() {
		super.authenticate("brotherhood1");
		final PeriodRecord savetest = this.prs.create();
		savetest.setDescription("Sample");
		savetest.setTitle("SAMPLE");
		savetest.setEndYear(2021);
		savetest.setPhotos(new ArrayList<String>());
		savetest.setStartYear(2020);

		final PeriodRecord saved = this.prs.save(savetest);
		Assert.isTrue(saved.getId() != 0);
		Assert.isTrue(saved.getDescription() == "Sample");
		super.unauthenticate();
	}

	@Test(expected = ConstraintViolationException.class)
	public void badTestSavePeriodRecord() {
		super.authenticate("brotherhood1");
		final PeriodRecord savetest = this.prs.create();

		this.prs.save(savetest);
		super.unauthenticate();

	}
	@Test
	public void testEditPeriodRecord() {
		super.authenticate("brotherhood1");
		final int id = super.getEntityId("periodRecord1");
		final PeriodRecord r1 = this.prs.findOne(id);
		r1.setDescription("new sample description");

		final PeriodRecord saved = this.prs.save(r1);
		Assert.isTrue(saved.getDescription() == "new sample description");
		super.unauthenticate();
	}

	@Test(expected = IllegalArgumentException.class)
	public void badTestEditPeriodRecord() {
		super.authenticate("brotherhood2");
		final int id = super.getEntityId("periodRecord1");
		final PeriodRecord r1 = this.prs.findOne(id);
		r1.setDescription("new sample description");

		final PeriodRecord saved = this.prs.save(r1);
		Assert.isTrue(saved.getDescription() == "new sample description");
		super.unauthenticate();
	}
}
