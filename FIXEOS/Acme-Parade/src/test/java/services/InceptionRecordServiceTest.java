
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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@Transactional
public class InceptionRecordServiceTest extends AbstractTest {

	//SUT
	@Autowired
	private InceptionRecordService	lrs;


	@Test
	public void testSaveInceptionRecord() {
		super.authenticate("brotherhood1");
		final InceptionRecord savetest = this.lrs.create();
		savetest.setDescription("Sample");
		savetest.setTitle("SAMPLE");
		savetest.setPhotos(new ArrayList<String>());
		final InceptionRecord saved = this.lrs.save(savetest);
		Assert.isTrue(saved.getId() != 0);
		Assert.isTrue(saved.getDescription() == "Sample" && saved.getTitle() == "SAMPLE" && saved.getPhotos().isEmpty());
		super.unauthenticate();
	}
	@Test(expected = ConstraintViolationException.class)
	public void badTestSaveInceptionRecord() {
		super.authenticate("brotherhood1");
		final InceptionRecord savetest = this.lrs.create();
		savetest.setDescription("");
		savetest.setTitle("SAMPLE");

		this.lrs.save(savetest);
		super.unauthenticate();

	}
	@Test
	public void testEditInceptionRecord() {
		super.authenticate("brotherhood1");
		final int id = super.getEntityId("inceptionRecord1");
		final InceptionRecord r1 = this.lrs.findOne(id);
		r1.setDescription("new sample description");

		final InceptionRecord saved = this.lrs.save(r1);
		Assert.isTrue(saved.getDescription() == "new sample description");
		super.unauthenticate();
	}

	@Test(expected = IllegalArgumentException.class)
	public void badTestEditInceptionRecord() {
		super.authenticate("brotherhood2");
		final int id = super.getEntityId("inceptionRecord1");
		final InceptionRecord r1 = this.lrs.findOne(id);
		r1.setDescription("new sample description");

		final InceptionRecord saved = this.lrs.save(r1);
		Assert.isTrue(saved.getDescription() == "new sample description");
		super.unauthenticate();
	}
}
