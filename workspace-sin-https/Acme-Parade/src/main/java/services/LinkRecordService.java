
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.LinkRecordRepository;
import domain.Brotherhood;
import domain.LinkRecord;

@Service
@Transactional
public class LinkRecordService {

	@Autowired
	private LinkRecordRepository	linkRecordRepository;

	@Autowired
	private BrotherhoodService		brotherhoodService;


	public LinkRecord create() {
		final LinkRecord res = new LinkRecord();

		return res;
	}

	public LinkRecord save(final LinkRecord leg) {
		Assert.notNull(leg);
		final Brotherhood b = this.brotherhoodService.findByPrincipal();

		boolean test = false;

		if (leg.getId() != 0) {
			for (final LinkRecord lr : b.getLinkRecords())
				if (lr.getId() == leg.getId())
					test = true;
			Assert.isTrue(test, "You are not the owner of this link record");
		}

		if (leg.getId() == 0) {
			final Collection<LinkRecord> legsOld = b.getLinkRecords();
			legsOld.add(leg);
			b.setLinkRecords(legsOld);
			this.brotherhoodService.save(b);
		}

		return this.linkRecordRepository.save(leg);
	}

	public void delete(final LinkRecord leg) {
		Assert.notNull(leg);
		final Brotherhood b = this.brotherhoodService.findByPrincipal();

		final Collection<LinkRecord> legsOld = b.getLinkRecords();
		legsOld.remove(leg);
		b.setLinkRecords(legsOld);
		this.brotherhoodService.save(b);

		this.linkRecordRepository.delete(leg);
	}

	public LinkRecord findOne(final int id) {
		return this.linkRecordRepository.findOne(id);
	}

	public Collection<LinkRecord> findAll() {
		return this.linkRecordRepository.findAll();
	}

}
