
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.AreaRepository;
import repositories.ChapterRepository;
import domain.Area;
import domain.Brotherhood;
import domain.Chapter;

@Service
@Transactional
public class AreaService {

	//Managed repository
	@Autowired
	private AreaRepository		areaRepository;

	//Services
	@Autowired
	private BrotherhoodService	brotherhoodService;

	@Autowired
	private ChapterRepository	chapterRepository;


	//Simple CRUD Methods
	public Area create() {
		final Area res = new Area();

		res.setPictures(new ArrayList<String>());
		res.setBrotherhoods(new ArrayList<Brotherhood>());

		return res;
	}

	public Area save(final Area a) {
		Assert.notNull(a);
		return this.areaRepository.save(a);
	}

	public Area findOne(final int areaId) {
		Assert.isTrue(areaId != 0);
		return this.areaRepository.findOne(areaId);
	}

	public Collection<Area> findAll() {
		return this.areaRepository.findAll();
	}

	public void delete(final Area a) {
		Assert.notNull(a);
		for (final Brotherhood b : this.brotherhoodService.findAll())
			if (!b.getArea().equals(a))
				this.areaRepository.delete(a);
	}

	public Area getParadeArea(final int id) {
		Assert.isTrue(id != 0);
		return this.areaRepository.getParadeArea(id);

	}

	public Collection<Area> findNotAssigned() {
		final List<Area> allAreas = new ArrayList<>(this.areaRepository.findAll());
		final List<Area> assignedAreas = new ArrayList<>();
		final List<Chapter> allChapters = this.chapterRepository.findAll();
		for (final Chapter c : allChapters)
			if (c.getArea() != null)
				assignedAreas.add(c.getArea());
		allAreas.removeAll(assignedAreas);
		return allAreas;
	}

}
