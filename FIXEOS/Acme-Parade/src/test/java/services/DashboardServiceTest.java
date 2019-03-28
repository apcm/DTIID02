
package services;

import java.util.ArrayList;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Brotherhood;
import domain.Chapter;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@Transactional
public class DashboardServiceTest extends AbstractTest {

	@Autowired
	private DashboardService		dashboardService;

	@Autowired
	private ChapterService			chapterService;
	@Rule
	public final ExpectedException	exception	= ExpectedException.none();


	/**
	 * THIS TEST IS FOR TESTING THE REQUIREMENT #4 AND #8
	 * THERE ARE 16 GOOD TESTING CASES, AND 13 BAD TESTING CASES
	 * COVERED INSTRUCTIONS IN THIS TEST: 84.5%
	 * COVERED DATA IN THIS TEST: ?%
	 * */

	@Test
	public void testNewFunctions1() {

		/**
		 * TESTING REQUIREMENT #4
		 * POSITIVE TEST
		 * COVERED INSTRUCTIONS: 100.0%
		 * COVERED DATA: ?%
		 * */

		super.authenticate("admin1");
		final String valorEsperado = "La Lanzada";
		final List<Brotherhood> res = new ArrayList<>(this.dashboardService.largerThanAvgHistoryBrotherhood());

		Assert.isTrue(res.get(0).getTitle().contains(valorEsperado));
		super.unauthenticate();
	}

	@Test
	public void testNewFunctions1Fail() {
		/**
		 * TESTING REQUIREMENT #4
		 * NEGATIVE TEST: THE EXPECTED VALUE IS NOT THE CORRECT ONE
		 * (Expected IllegalArgumentException)
		 * COVERED INSTRUCTIONS: 83.9%
		 * COVERED DATA: ?%
		 * */
		super.authenticate("admin1");
		this.exception.expect(IllegalArgumentException.class);
		final String valorEsperado = "La Lan";
		final List<Brotherhood> res = new ArrayList<>(this.dashboardService.largerThanAvgHistoryBrotherhood());

		Assert.isTrue(!res.get(0).getTitle().contains(valorEsperado));
		super.unauthenticate();

	}

	@Test
	public void testNewFunctions2() {

		/**
		 * TESTING REQUIREMENT #4
		 * POSITIVE TEST
		 * COVERED INSTRUCTIONS: 100.0%
		 * COVERED DATA: ?%
		 * */

		super.authenticate("admin1");
		final String valorEsperado = "La Lanzada";
		final List<Brotherhood> res = new ArrayList<>(this.dashboardService.largestHistoryBrotherhood());
		Assert.isTrue(res.get(0).getTitle().contains(valorEsperado));
		super.unauthenticate();
	}

	@Test
	public void testNewFunctions2Fail() {

		/**
		 * TESTING REQUIREMENT #4
		 * NEGATIVE TEST: THE EXPECTED VALUE IS NOT THE CORRECT ONE
		 * (Expected IllegalArgumentException)
		 * COVERED INSTRUCTIONS: 83.9%
		 * COVERED DATA: ?%
		 * */

		super.authenticate("admin1");
		this.exception.expect(IllegalArgumentException.class);
		final String valorEsperado = "La Lan";
		final List<Brotherhood> res = new ArrayList<>(this.dashboardService.largestHistoryBrotherhood());
		Assert.isTrue(!res.get(0).getTitle().contains(valorEsperado));
		super.unauthenticate();
	}
	@Test
	public void testNewFunctions3() {

		/**
		 * TESTING REQUIREMENT #4
		 * POSITIVE TEST
		 * COVERED INSTRUCTIONS: 94.4%
		 * COVERED DATA: ?%
		 * */

		super.authenticate("admin1");
		final double valorEsperado = 5.0;
		Assert.isTrue(this.dashboardService.maxNumRecordsPerHistory() == valorEsperado);

		super.unauthenticate();
	}

	@Test
	public void testNewFunctions3Fail() {

		/**
		 * TESTING REQUIREMENT #4
		 * NEGATIVE TEST: THE EXPECTED VALUE IS NOT THE CORRECT ONE
		 * (Expected IllegalArgumentException)
		 * COVERED INSTRUCTIONS: 72.7%
		 * COVERED DATA: ?%
		 * */

		super.authenticate("admin1");
		this.exception.expect(IllegalArgumentException.class);
		final double valorEsperado = 4.0;
		Assert.isTrue(this.dashboardService.maxNumRecordsPerHistory() == valorEsperado);

		super.unauthenticate();
	}

	@Test
	public void testNewFunctions4() {

		/**
		 * TESTING REQUIREMENT #4
		 * POSITIVE TEST
		 * COVERED INSTRUCTIONS: 94.4%
		 * COVERED DATA: ?%
		 * */

		super.authenticate("admin1");
		final double valorEsperado = 1.0;
		Assert.isTrue(this.dashboardService.minNumRecordsPerHistory() == valorEsperado);

		super.unauthenticate();
	}

	@Test
	public void testNewFunctions4Fail() {

		/**
		 * TESTING REQUIREMENT #4
		 * NEGATIVE TEST: THE EXPECTED VALUE IS NOT THE CORRECT ONE
		 * (Expected IllegalArgumentException)
		 * COVERED INSTRUCTIONS: 72.7%
		 * COVERED DATA: ?%
		 * */

		super.authenticate("admin1");
		this.exception.expect(IllegalArgumentException.class);
		final double valorEsperado = 3.0;
		Assert.isTrue(this.dashboardService.minNumRecordsPerHistory() == valorEsperado);

		super.unauthenticate();
	}

	@Test
	public void testNewFunctions5() {

		/**
		 * TESTING REQUIREMENT #4
		 * POSITIVE TEST
		 * COVERED INSTRUCTIONS: 94.4%
		 * COVERED DATA: ?%
		 * */

		super.authenticate("admin1");
		final double valorEsperado = 1.6997;
		Assert.isTrue(this.dashboardService.stddevNumRecordsPerHistory() == valorEsperado);

		super.unauthenticate();
	}

	@Test
	public void testNewFunctions5Fail() {

		/**
		 * TESTING REQUIREMENT #4
		 * NEGATIVE TEST: THE EXPECTED VALUE IS NOT THE CORRECT ONE
		 * (Expected IllegalArgumentException)
		 * COVERED INSTRUCTIONS: 72.7%
		 * COVERED DATA: ?%
		 * */

		super.authenticate("admin1");
		this.exception.expect(IllegalArgumentException.class);
		final double valorEsperado = 0.6;
		Assert.isTrue(this.dashboardService.stddevNumRecordsPerHistory() == valorEsperado);

		super.unauthenticate();
	}

	@Test
	public void testNewFunctions6() {

		/**
		 * TESTING REQUIREMENT #4
		 * POSITIVE TEST
		 * COVERED INSTRUCTIONS: 94.4%
		 * COVERED DATA: ?%
		 * */

		super.authenticate("admin1");
		final double valorEsperado = 3.3333;
		Assert.isTrue(this.dashboardService.avgNumRecordsPerHistory() == valorEsperado);
		super.unauthenticate();
	}

	@Test
	public void testNewFunctions6Fail() {

		/**
		 * TESTING REQUIREMENT #4
		 * NEGATIVE TEST: THE EXPECTED VALUE IS NOT THE CORRECT ONE
		 * (Expected IllegalArgumentException)
		 * COVERED INSTRUCTIONS: 72.7%
		 * COVERED DATA: ?%
		 * */

		super.authenticate("admin1");
		this.exception.expect(IllegalArgumentException.class);
		final double valorEsperado = 3.2;
		Assert.isTrue(this.dashboardService.avgNumRecordsPerHistory() == valorEsperado);

		super.unauthenticate();
	}

	@Test
	public void testNewFunctions7() {

		/**
		 * TESTING REQUIREMENT #8
		 * POSITIVE TEST
		 * COVERED INSTRUCTIONS: 94.4%
		 * COVERED DATA: ?%
		 * */

		super.authenticate("admin1");
		final double valorEsperado = 0.33333;
		Assert.isTrue(this.dashboardService.ratioAreasNoChapter() == valorEsperado);

		super.unauthenticate();
	}

	@Test
	public void testNewFunctions7Fail() {

		/**
		 * TESTING REQUIREMENT #8
		 * NEGATIVE TEST: THE EXPECTED VALUE IS NOT THE CORRECT ONE
		 * (Expected IllegalArgumentException)
		 * COVERED INSTRUCTIONS: 72.7%
		 * COVERED DATA: ?%
		 * */

		super.authenticate("admin1");
		this.exception.expect(IllegalArgumentException.class);
		final double valorEsperado = 0.2;
		Assert.isTrue(this.dashboardService.ratioAreasNoChapter() == valorEsperado);

		super.unauthenticate();
	}

	@Test
	public void testNewFunctions8() {

		/**
		 * TESTING REQUIREMENT #8
		 * POSITIVE TEST
		 * COVERED INSTRUCTIONS: 94.4%
		 * COVERED DATA: ?%
		 * */

		super.authenticate("admin1");
		final double valorEsperado = 0.66667;
		Assert.isTrue(this.dashboardService.avgParadesPerChapter() == valorEsperado);

		super.unauthenticate();
	}

	@Test
	public void testNewFunctions8Fail() {

		/**
		 * TESTING REQUIREMENT #8
		 * NEGATIVE TEST: THE EXPECTED VALUE IS NOT THE CORRECT ONE
		 * (Expected IllegalArgumentException)
		 * COVERED INSTRUCTIONS: 72.7%
		 * COVERED DATA: ?%
		 * */

		super.authenticate("admin1");
		this.exception.expect(IllegalArgumentException.class);
		final double valorEsperado = 0.2;
		Assert.isTrue(this.dashboardService.avgParadesPerChapter() == valorEsperado);

		super.unauthenticate();
	}

	@Test
	public void testNewFunctions9() {

		/**
		 * TESTING REQUIREMENT #8
		 * POSITIVE TEST
		 * COVERED INSTRUCTIONS: 94.4%
		 * COVERED DATA: ?%
		 * */

		super.authenticate("admin1");
		final double valorEsperado = 1.0;
		Assert.isTrue(this.dashboardService.minParadesPerChapter() == valorEsperado);

		super.unauthenticate();
	}

	@Test
	public void testNewFunctions9Fail() {

		/**
		 * TESTING REQUIREMENT #8
		 * NEGATIVE TEST: THE EXPECTED VALUE IS NOT THE CORRECT ONE
		 * (Expected IllegalArgumentException)
		 * COVERED INSTRUCTIONS: 72.7%
		 * COVERED DATA: ?%
		 * */

		super.authenticate("admin1");
		this.exception.expect(IllegalArgumentException.class);
		final double valorEsperado = 0.2;
		Assert.isTrue(this.dashboardService.minParadesPerChapter() == valorEsperado);

		super.unauthenticate();
	}
	@Test
	public void testNewFunctions10() {

		/**
		 * TESTING REQUIREMENT #4
		 * POSITIVE TEST
		 * COVERED INSTRUCTIONS: 94.4%
		 * COVERED DATA: ?%
		 * */

		super.authenticate("admin1");
		final double valorEsperado = 1.0;
		Assert.isTrue(this.dashboardService.maxParadesPerChapter() == valorEsperado);

		super.unauthenticate();
	}

	@Test
	public void testNewFunctions10Fail() {

		/**
		 * TESTING REQUIREMENT #8
		 * NEGATIVE TEST: THE EXPECTED VALUE IS NOT THE CORRECT ONE
		 * (Expected IllegalArgumentException)
		 * COVERED INSTRUCTIONS: 72.7%
		 * COVERED DATA: ?%
		 * */

		super.authenticate("admin1");
		this.exception.expect(IllegalArgumentException.class);
		final double valorEsperado = 0.2;
		Assert.isTrue(this.dashboardService.minParadesPerChapter() == valorEsperado);

		super.unauthenticate();
	}

	@Test
	public void testNewFunctions11() {

		/**
		 * TESTING REQUIREMENT #4
		 * POSITIVE TEST
		 * COVERED INSTRUCTIONS: 94.4%
		 * COVERED DATA: ?%
		 * */

		super.authenticate("admin1");
		final double valorEsperado = 1.0;
		Assert.isTrue(this.dashboardService.maxParadesPerChapter() == valorEsperado);

		super.unauthenticate();
	}

	@Test
	public void testNewFunctions11Fail() {

		/**
		 * TESTING REQUIREMENT #8
		 * NEGATIVE TEST: THE EXPECTED VALUE IS NOT THE CORRECT ONE
		 * (Expected IllegalArgumentException)
		 * COVERED INSTRUCTIONS: 72.7%
		 * COVERED DATA: ?%
		 * */

		super.authenticate("admin1");
		this.exception.expect(IllegalArgumentException.class);
		final double valorEsperado = 0.2;
		Assert.isTrue(this.dashboardService.maxParadesPerChapter() == valorEsperado);

		super.unauthenticate();
	}

	@Test
	public void testNewFunctions12() {

		/**
		 * TESTING REQUIREMENT #8
		 * POSITIVE TEST
		 * COVERED INSTRUCTIONS: 94.4%
		 * COVERED DATA: ?%
		 * */

		super.authenticate("admin1");
		final double valorEsperado = 0.;
		Assert.isTrue(this.dashboardService.stddevParadesPerChapter() == valorEsperado);

		super.unauthenticate();
	}

	@Test
	public void testNewFunctions12Fail() {

		/**
		 * TESTING REQUIREMENT #8
		 * NEGATIVE TEST: THE EXPECTED VALUE IS NOT THE CORRECT ONE
		 * (Expected IllegalArgumentException)
		 * COVERED INSTRUCTIONS: 72.7%
		 * COVERED DATA: ?%
		 * */

		super.authenticate("admin1");
		this.exception.expect(IllegalArgumentException.class);
		final double valorEsperado = 0.2;
		Assert.isTrue(this.dashboardService.stddevParadesPerChapter() == valorEsperado);

		super.unauthenticate();
	}

	@Test
	public void testNewFunctions13() {

		/**
		 * TESTING REQUIREMENT #8
		 * POSITIVE TEST
		 * COVERED INSTRUCTIONS: 100.0%
		 * COVERED DATA: ?%
		 * */

		super.authenticate("admin1");
		final Chapter c1 = new Chapter();
		c1.setId(859);
		final Chapter c2 = new Chapter();
		c2.setId(860);
		final Chapter cEsperado1 = this.chapterService.findOne(c1);
		final Chapter cEsperado2 = this.chapterService.findOne(c2);

		Assert.isTrue(this.dashboardService.chapterMoreParadesThanAvg().contains(cEsperado1));
		Assert.isTrue(this.dashboardService.chapterMoreParadesThanAvg().contains(cEsperado2));
		super.unauthenticate();
	}

	@Test
	public void testNewFunctions14() {

		/**
		 * TESTING REQUIREMENT #8
		 * POSITIVE TEST
		 * COVERED INSTRUCTIONS: 94.4%
		 * COVERED DATA: ?%
		 * */

		super.authenticate("admin1");
		final double valorEsperado = 0.;
		Assert.isTrue(this.dashboardService.ratioParadesDraftModeVsFinalMode() == valorEsperado);

		super.unauthenticate();
	}

	@Test
	public void testNewFunctions15() {

		/**
		 * TESTING REQUIREMENT #8
		 * POSITIVE TEST
		 * COVERED INSTRUCTIONS: 94.4%
		 * COVERED DATA: ?%
		 * */

		super.authenticate("admin1");
		final double valorEsperado = 0.;
		Assert.isTrue(this.dashboardService.ratioParadesDraftModeVsFinalMode() == valorEsperado);

		super.unauthenticate();
	}

	@Test
	public void testNewFunctions16() {

		/**
		 * TESTING REQUIREMENT #8
		 * POSITIVE TEST
		 * COVERED INSTRUCTIONS: 95.1%
		 * COVERED DATA: ?%
		 * */

		super.authenticate("admin1");
		final double valorEsperado1 = 0.66667;
		final double valorEsperado2 = 0.33333;
		final List<Double> lista = new ArrayList<Double>(this.dashboardService.ratioParadesFinalModeGroupedByStatus());
		Assert.isTrue(lista.get(0) == valorEsperado1);
		Assert.isTrue(lista.get(1) == valorEsperado2);

		super.unauthenticate();
	}

	@Test
	public void badTestNewFunctions1() {

		/**
		 * TESTING REQUIREMENT #8
		 * NEGATIVE TEST: A BROTHERHOOD USER CAN'T USE THIS METHOD
		 * (Expected IllegalArgumentException)
		 * COVERED INSTRUCTIONS: ?%
		 * COVERED DATA: ?%
		 * */

		super.authenticate("brotherhood1");
		this.exception.expect(IllegalArgumentException.class);
		this.dashboardService.largerThanAvgHistoryBrotherhood();

		super.unauthenticate();

	}

}
