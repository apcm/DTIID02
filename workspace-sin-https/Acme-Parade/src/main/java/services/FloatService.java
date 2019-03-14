
package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.FloatRepository;
import repositories.ProcessionRepository;
import security.Authority;
import security.UserAccount;
import domain.Brotherhood;
import domain.Float;
import domain.Parade;

@Service
@Transactional
public class FloatService {

	//Managed repository
	@Autowired
	private FloatRepository			floatRepository;

	@Autowired
	private BrotherhoodService		brotherhoodService;

	@Autowired
	private ProcessionRepository	processionRepository;

	@Autowired
	private ProcessionService		processionService;


	//Simple CRUD Methods
	public Float create() {
		final Float res = new Float();

		final Brotherhood logBro = this.brotherhoodService.findByPrincipal();
		res.setBrotherhood(logBro);

		res.setPictures(new ArrayList<String>());

		return res;
	}

	public Float save(final Float f) {
		Assert.notNull(f);
		final Brotherhood b = this.brotherhoodService.findByPrincipal();

		if (f.getId() == 0) {
			final Brotherhood logBro = this.brotherhoodService.findByPrincipal();
			f.setBrotherhood(logBro);
			Assert.isTrue(f.getBrotherhood().getId() == b.getId());
		}

		return this.floatRepository.save(f);
	}
	public Float findOne(final Integer id) {
		Assert.isTrue(id != 0);
		return this.floatRepository.findOne(id);
	}

	public Collection<Float> findAll() {
		return this.floatRepository.findAll();
	}

	public void delete(final Float f) {
		Assert.notNull(f);
		//Si alguna procesión en draftMode saca el paso, se le quita.
		final Collection<Parade> process = this.processionService.findAllFinalMode();

		for (final Parade pro : process)
			if (pro.getFloats().contains(f)) {
				final Collection<domain.Float> floats = pro.getFloats();
				floats.remove(f);
				pro.setFloats(floats);
				this.processionService.save(pro);
			}

		//		for (final Procession p : procs) {
		//			final Collection<domain.Float> floats = p.getFloats();
		//			floats.remove(f);
		//			p.setFloats(floats);
		//			this.processionService.save(p);
		//		}

		this.floatRepository.delete(f);
	}

	public Collection<domain.Float> findByBrotherhoodId(final int brotherhoodId) {
		return this.floatRepository.findByBrotherhoodId(brotherhoodId);
	}


	@Autowired
	private Validator	validator;


	public domain.Float reconstruct(final domain.Float flo, final BindingResult binding) {
		domain.Float res;

		//Check authority
		final Authority a = new Authority();
		final Brotherhood bro = this.brotherhoodService.findByPrincipal();
		final UserAccount user = bro.getUserAccount();
		a.setAuthority(Authority.BROTHERHOOD);
		Assert.isTrue(user.getAuthorities().contains(a) && user.getAuthorities().size() == 1);

		if (flo.getId() == 0) {
			res = flo;
			res.setBrotherhood(bro);
		} else {
			final Float originCopy = this.floatRepository.findOne(flo.getId());
			res = flo;
			res.setBrotherhood(originCopy.getBrotherhood());
			//			res.setDescription(flo.getDescription());
			//			res.setTitle(flo.getTitle());
			//			res.setPictures(flo.getPictures());
			
		}
		this.validator.validate(res, binding);
		return res;
	}
}
