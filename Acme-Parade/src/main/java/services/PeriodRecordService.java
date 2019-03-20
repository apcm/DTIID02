
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.PeriodRecordRepository;
import domain.Brotherhood;
import domain.PeriodRecord;

@Service
@Transactional
public class PeriodRecordService {

	@Autowired
	private PeriodRecordRepository	periodRecordRepository;

	@Autowired
	private BrotherhoodService		brotherhoodService;


	public PeriodRecord create() {
		final PeriodRecord res = new PeriodRecord();

		return res;
	}

	public PeriodRecord save(final PeriodRecord leg) {
		Assert.notNull(leg);
		final Brotherhood b = this.brotherhoodService.findByPrincipal();
		boolean test = false;

		if (leg.getId() != 0) {
			for (final PeriodRecord lr : b.getPeriodRecords())
				if (lr.getId() == leg.getId())
					test = true;
			Assert.isTrue(test, "You are not the owner of this period record");
		}

		if (leg.getId() == 0) {
			final Collection<PeriodRecord> legsOld = b.getPeriodRecords();
			legsOld.add(leg);
			b.setPeriodRecords(legsOld);
			this.brotherhoodService.save(b);
		}

		return this.periodRecordRepository.save(leg);
	}

	public void delete(final PeriodRecord leg) {
		Assert.notNull(leg);
		final Brotherhood b = this.brotherhoodService.findByPrincipal();

		final Collection<PeriodRecord> legsOld = b.getPeriodRecords();
		legsOld.remove(leg);
		b.setPeriodRecords(legsOld);
		this.brotherhoodService.save(b);

		this.periodRecordRepository.delete(leg);
	}

	public PeriodRecord findOne(final int id) {
		return this.periodRecordRepository.findOne(id);
	}

}
