
package services;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

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


	@Test
	public void testSaveLegalRecord() {
		super.authenticate("brotherhood1");
		final LegalRecord savetest = this.lrs.create();
		savetest.setLegalName("Sample");
		savetest.setDescription("Sample");
		savetest.setTitle("SAMPLE");
		savetest.setApplicableLaws(new ArrayList<String>());
		savetest.setVatNumber(7);

		final LegalRecord saved = this.lrs.save(savetest);
		Assert.isTrue(saved.getId() != 0);
		Assert.isTrue(saved.getLegalName() == "Sample" && saved.getDescription() == "Sample" && saved.getTitle() == "SAMPLE" && saved.getApplicableLaws().isEmpty() && saved.getVatNumber() == 7);
		super.unauthenticate();
	}

	@Test
	public void badTestSaveLegalRecord() {
		super.authenticate("brotherhood1");
		final LegalRecord savetest = this.lrs.create();
		savetest.setLegalName("");
		savetest.setDescription("");
		savetest.setTitle("SAMPLE");
		savetest.setApplicableLaws(new ArrayList<String>());
		savetest.setVatNumber(7);

		this.lrs.save(savetest);
		super.unauthenticate();

	}
	@Test
	public void testEditLegalRecord() {
		super.authenticate("brotherhood1");
		final int id = super.getEntityId("legalRecord1");
		final LegalRecord r1 = this.lrs.findOne(id);
		r1.setDescription("new sample description");

		final LegalRecord saved = this.lrs.save(r1);
		Assert.isTrue(saved.getDescription() == "new sample description");
		super.unauthenticate();
	}

	@Test
	public void badTestEditLegalRecord() {
		super.authenticate("brotherhood2");
		final int id = super.getEntityId("legalRecord1");
		final LegalRecord r1 = this.lrs.findOne(id);
		r1.setDescription("new sample description");

		final LegalRecord saved = this.lrs.save(r1);
		Assert.isTrue(saved.getDescription() == "new sample description");
		super.unauthenticate();
	}
}
