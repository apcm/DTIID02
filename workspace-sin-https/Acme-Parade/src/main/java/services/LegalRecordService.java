
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.LegalRecordRepository;
import domain.Brotherhood;
import domain.LegalRecord;

@Service
@Transactional
public class LegalRecordService {

	@Autowired
	private LegalRecordRepository	legalRecordRepository;

	@Autowired
	private BrotherhoodService		brotherhoodService;


	public LegalRecord create() {
		final LegalRecord res = new LegalRecord();

		return res;
	}

	public LegalRecord save(final LegalRecord leg) {
		Assert.notNull(leg);
		final Brotherhood b = this.brotherhoodService.findByPrincipal();

		if (leg.getId() == 0) {
			final Collection<LegalRecord> legsOld = b.getLegalRecords();
			legsOld.add(leg);
			b.setLegalRecords(legsOld);
			this.brotherhoodService.save(b);
		}

		return this.legalRecordRepository.save(leg);
	}

	public void delete(final LegalRecord leg) {
		Assert.notNull(leg);
		final Brotherhood b = this.brotherhoodService.findByPrincipal();

		final Collection<LegalRecord> legsOld = b.getLegalRecords();
		legsOld.remove(leg);
		b.setLegalRecords(legsOld);
		this.brotherhoodService.save(b);

		this.legalRecordRepository.delete(leg);
	}

}
