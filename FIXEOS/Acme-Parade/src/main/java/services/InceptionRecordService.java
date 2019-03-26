
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.InceptionRecordRepository;
import domain.Brotherhood;
import domain.InceptionRecord;

@Service
@Transactional
public class InceptionRecordService {

	@Autowired
	private InceptionRecordRepository	inceptionRecordRepository;

	@Autowired
	private BrotherhoodService			brotherhoodService;


	public InceptionRecord create() {
		final InceptionRecord res = new InceptionRecord();

		return res;
	}

	public InceptionRecord save(final InceptionRecord inc) {
		Assert.notNull(inc);
		final Brotherhood b = this.brotherhoodService.findByPrincipal();

		boolean test = false;

		if (inc.getId() != 0) {
			if (b.getInceptionRecord().getId() == inc.getId())
				test = true;
			Assert.isTrue(test, "You are not the owner of this inception record");
		}

		if (inc.getId() == 0) {
			b.setInceptionRecord(inc);
			this.brotherhoodService.save(b);
		}

		return this.inceptionRecordRepository.save(inc);
	}

	public void delete(final InceptionRecord inc) {
		Assert.notNull(inc);
		final Brotherhood b = this.brotherhoodService.findByPrincipal();

		b.setInceptionRecord(null);
		this.brotherhoodService.save(b);

		this.inceptionRecordRepository.delete(inc);
	}

	public InceptionRecord findOne(final int id) {
		return this.inceptionRecordRepository.findOne(id);
	}

	public Collection<InceptionRecord> findAll() {
		return this.inceptionRecordRepository.findAll();
	}

}
