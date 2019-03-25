
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


	@Test
	public void testSaveLinkRecord() {
		super.authenticate("brotherhood1");
		final LinkRecord savetest = this.lrs.create();
		savetest.setDescription("Sample");
		savetest.setTitle("SAMPLE");
		savetest.setLink("http://www.sample.com");

		final LinkRecord saved = this.lrs.save(savetest);
		Assert.isTrue(saved.getId() != 0);
		Assert.isTrue(saved.getLink() == "http://www.sample.com" && saved.getDescription() == "Sample" && saved.getTitle() == "SAMPLE");
		super.unauthenticate();
	}
	@Test(expected = ConstraintViolationException.class)
	public void badTestSaveLinkRecord() {
		super.authenticate("brotherhood1");
		final LinkRecord savetest = this.lrs.create();
		savetest.setDescription("");
		savetest.setTitle("SAMPLE");

		this.lrs.save(savetest);
		super.unauthenticate();

	}
	@Test
	public void testEditLinkRecord() {
		super.authenticate("brotherhood1");
		final int id = super.getEntityId("linkRecord1");
		final LinkRecord r1 = this.lrs.findOne(id);
		r1.setDescription("new sample description");

		final LinkRecord saved = this.lrs.save(r1);
		Assert.isTrue(saved.getDescription() == "new sample description");
		super.unauthenticate();
	}

	@Test(expected = IllegalArgumentException.class)
	public void badTestEditLinkRecord() {
		super.authenticate("brotherhood2");
		final int id = super.getEntityId("linkRecord1");
		final LinkRecord r1 = this.lrs.findOne(id);
		r1.setDescription("new sample description");

		final LinkRecord saved = this.lrs.save(r1);
		Assert.isTrue(saved.getDescription() == "new sample description");
		super.unauthenticate();
	}
}
