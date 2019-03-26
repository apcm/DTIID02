
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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@Transactional
public class DashboardServiceTest extends AbstractTest {

	@Autowired
	private DashboardService		dashboardService;
	@Rule
	public final ExpectedException	exception	= ExpectedException.none();


	@Test
	public void testNewFunctions1() {
		super.authenticate("admin1");
		final String valorEsperado = "La Lanzada";
		final List<Brotherhood> res = new ArrayList<>(this.dashboardService.largerThanAvgHistoryBrotherhood());

		Assert.isTrue(res.get(0).getTitle().contains(valorEsperado));
		super.unauthenticate();
	}

	@Test
	public void testNewFunctions1Fail() {
		super.authenticate("admin1");
		this.exception.expect(IllegalArgumentException.class);
		final String valorEsperado = "La Lan";
		final List<Brotherhood> res = new ArrayList<>(this.dashboardService.largerThanAvgHistoryBrotherhood());

		Assert.isTrue(!res.get(0).getTitle().contains(valorEsperado));
		super.unauthenticate();

	}

	@Test
	public void testNewFunctions2() {
		super.authenticate("admin1");
		final String valorEsperado = "La Lanzada";
		final List<Brotherhood> res = new ArrayList<>(this.dashboardService.largestHistoryBrotherhood());
		Assert.isTrue(res.get(0).getTitle().contains(valorEsperado));
		super.unauthenticate();
	}

	@Test
	public void testNewFunctions2Fail() {
		super.authenticate("admin1");
		this.exception.expect(IllegalArgumentException.class);
		final String valorEsperado = "La Lan";
		final List<Brotherhood> res = new ArrayList<>(this.dashboardService.largestHistoryBrotherhood());
		Assert.isTrue(!res.get(0).getTitle().contains(valorEsperado));
		super.unauthenticate();
	}
	@Test
	public void testNewFunctions3() {
		super.authenticate("admin1");
		final double valorEsperado = 5.0;
		Assert.isTrue(this.dashboardService.maxNumRecordsPerHistory() == valorEsperado);

		super.unauthenticate();
	}

	@Test
	public void testNewFunctions3Fail() {
		super.authenticate("admin1");
		this.exception.expect(IllegalArgumentException.class);
		final double valorEsperado = 4.0;
		Assert.isTrue(this.dashboardService.maxNumRecordsPerHistory() == valorEsperado);

		super.unauthenticate();
	}

	@Test
	public void testNewFunctions4() {
		super.authenticate("admin1");
		final double valorEsperado = 4.0;
		Assert.isTrue(this.dashboardService.minNumRecordsPerHistory() == valorEsperado);

		super.unauthenticate();
	}

	@Test
	public void testNewFunctions4Fail() {
		super.authenticate("admin1");
		this.exception.expect(IllegalArgumentException.class);
		final double valorEsperado = 3.0;
		Assert.isTrue(this.dashboardService.minNumRecordsPerHistory() == valorEsperado);

		super.unauthenticate();
	}

	@Test
	public void testNewFunctions5() {
		super.authenticate("admin1");
		final double valorEsperado = 0.5;
		Assert.isTrue(this.dashboardService.stddevNumRecordsPerHistory() == valorEsperado);

		super.unauthenticate();
	}

	@Test
	public void testNewFunctions5Fail() {
		super.authenticate("admin1");
		this.exception.expect(IllegalArgumentException.class);
		final double valorEsperado = 0.6;
		Assert.isTrue(this.dashboardService.stddevNumRecordsPerHistory() == valorEsperado);

		super.unauthenticate();
	}

	@Test
	public void testNewFunctions6() {
		super.authenticate("admin1");
		final double valorEsperado = 4.5;
		Assert.isTrue(this.dashboardService.avgNumRecordsPerHistory() == valorEsperado);

		super.unauthenticate();
	}

	@Test
	public void testNewFunctions6Fail() {
		super.authenticate("admin1");
		this.exception.expect(IllegalArgumentException.class);
		final double valorEsperado = 3.2;
		Assert.isTrue(this.dashboardService.avgNumRecordsPerHistory() == valorEsperado);

		super.unauthenticate();
	}

	@Test
	public void testNewFunctions7() {
		super.authenticate("admin1");
		final double valorEsperado = 0.;
		Assert.isTrue(this.dashboardService.ratioAreasNoChapter() == valorEsperado);

		super.unauthenticate();
	}

	@Test
	public void testNewFunctions7Fail() {
		super.authenticate("admin1");
		this.exception.expect(IllegalArgumentException.class);
		final double valorEsperado = 0.2;
		Assert.isTrue(this.dashboardService.ratioAreasNoChapter() == valorEsperado);

		super.unauthenticate();
	}

	@Test
	public void testNewFunctions8() {
		super.authenticate("admin1");
		final double valorEsperado = 1.0;
		Assert.isTrue(this.dashboardService.avgParadesPerChapter() == valorEsperado);

		super.unauthenticate();
	}

	@Test
	public void testNewFunctions8Fail() {
		super.authenticate("admin1");
		this.exception.expect(IllegalArgumentException.class);
		final double valorEsperado = 0.2;
		Assert.isTrue(this.dashboardService.avgParadesPerChapter() == valorEsperado);

		super.unauthenticate();
	}

	@Test
	public void testNewFunctions9() {
		super.authenticate("admin1");
		final double valorEsperado = 1.0;
		Assert.isTrue(this.dashboardService.minParadesPerChapter() == valorEsperado);

		super.unauthenticate();
	}

	@Test
	public void testNewFunctions9Fail() {
		super.authenticate("admin1");
		this.exception.expect(IllegalArgumentException.class);
		final double valorEsperado = 0.2;
		Assert.isTrue(this.dashboardService.minParadesPerChapter() == valorEsperado);

		super.unauthenticate();
	}
	@Test
	public void testNewFunctions10() {
		super.authenticate("admin1");
		final double valorEsperado = 1.0;
		Assert.isTrue(this.dashboardService.maxParadesPerChapter() == valorEsperado);

		super.unauthenticate();
	}

	@Test
	public void testNewFunctions10Fail() {
		super.authenticate("admin1");
		this.exception.expect(IllegalArgumentException.class);
		final double valorEsperado = 0.2;
		Assert.isTrue(this.dashboardService.minParadesPerChapter() == valorEsperado);

		super.unauthenticate();
	}

	@Test
	public void testNewFunctions11() {
		super.authenticate("admin1");
		final double valorEsperado = 1.0;
		Assert.isTrue(this.dashboardService.maxParadesPerChapter() == valorEsperado);

		super.unauthenticate();
	}

	@Test
	public void testNewFunctions11Fail() {
		super.authenticate("admin1");
		this.exception.expect(IllegalArgumentException.class);
		final double valorEsperado = 0.2;
		Assert.isTrue(this.dashboardService.maxParadesPerChapter() == valorEsperado);

		super.unauthenticate();
	}

	@Test
	public void testNewFunctions12() {
		super.authenticate("admin1");
		final double valorEsperado = 0.;
		Assert.isTrue(this.dashboardService.stddevParadesPerChapter() == valorEsperado);

		super.unauthenticate();
	}

	@Test
	public void testNewFunctions12Fail() {
		super.authenticate("admin1");
		this.exception.expect(IllegalArgumentException.class);
		final double valorEsperado = 0.2;
		Assert.isTrue(this.dashboardService.stddevParadesPerChapter() == valorEsperado);

		super.unauthenticate();
	}

	@Test
	public void testNewFunctions13() {
		super.authenticate("admin1");
		Assert.isTrue(this.dashboardService.chapterMoreParadesThanAvg().isEmpty());

		super.unauthenticate();
	}

	@Test
	public void testNewFunctions13Fail() {
		super.authenticate("admin1");
		this.exception.expect(IllegalArgumentException.class);
		Assert.isTrue(!this.dashboardService.chapterMoreParadesThanAvg().isEmpty());

		super.unauthenticate();
	}

	@Test
	public void testNewFunctions14() {
		super.authenticate("admin1");
		final double valorEsperado = 0.;
		Assert.isTrue(this.dashboardService.ratioParadesDraftModeVsFinalMode() == valorEsperado);

		super.unauthenticate();
	}

	public void testNewFunctions14Fail() {
		super.authenticate("admin1");
		this.exception.expect(IllegalArgumentException.class);
		final double valorEsperado = 0.2;
		Assert.isTrue(this.dashboardService.ratioParadesDraftModeVsFinalMode() == valorEsperado);

		super.unauthenticate();
	}

	@Test
	public void testNewFunctions15() {
		super.authenticate("admin1");
		final double valorEsperado = 0.;
		Assert.isTrue(this.dashboardService.ratioParadesDraftModeVsFinalMode() == valorEsperado);

		super.unauthenticate();
	}

	public void testNewFunctions15Fail() {
		super.authenticate("admin1");
		this.exception.expect(IllegalArgumentException.class);
		final double valorEsperado = 0.2;
		Assert.isTrue(this.dashboardService.ratioParadesDraftModeVsFinalMode() == valorEsperado);

		super.unauthenticate();
	}

	@Test
	public void testNewFunctions16() {
		super.authenticate("admin1");
		final double valorEsperado = 1.0;
		final List<Double> lista = new ArrayList<Double>(this.dashboardService.ratioParadesFinalModeGroupedByStatus());
		Assert.isTrue(lista.get(0) == valorEsperado);

		super.unauthenticate();
	}

	public void testNewFunctions16Fail() {
		super.authenticate("admin1");
		this.exception.expect(IllegalArgumentException.class);
		final double valorEsperado = 0.0;
		final List<Double> lista = new ArrayList<Double>(this.dashboardService.ratioParadesFinalModeGroupedByStatus());
		Assert.isTrue(lista.get(0) == valorEsperado);

		super.unauthenticate();
	}

	@Test
	public void badTestNewFunctions1() {
		super.authenticate("brotherhood1");
		this.exception.expect(IllegalArgumentException.class);
		this.dashboardService.largerThanAvgHistoryBrotherhood();

		super.unauthenticate();

	}

	@Test
	public void badTestNewFunctions2() {
		super.authenticate("brotherhood1");
		this.exception.expect(IllegalArgumentException.class);
		this.dashboardService.largestHistoryBrotherhood();
		super.unauthenticate();
	}

	@Test
	public void badTestNewFunctions3() {
		super.authenticate("brotherhood1");
		this.exception.expect(IllegalArgumentException.class);
		this.dashboardService.maxNumRecordsPerHistory();
		super.unauthenticate();
	}

	@Test
	public void badTestNewFunctions4() {
		super.authenticate("brotherhood1");
		this.exception.expect(IllegalArgumentException.class);
		this.dashboardService.minNumRecordsPerHistory();

		super.unauthenticate();

	}

	@Test
	public void badTestNewFunctions5() {
		super.authenticate("brotherhood1");
		this.exception.expect(IllegalArgumentException.class);
		this.dashboardService.stddevNumRecordsPerHistory();

		super.unauthenticate();

	}

	@Test
	public void badtestNewFunctions6() {
		super.authenticate("brotherhood1");
		this.exception.expect(IllegalArgumentException.class);
		this.dashboardService.avgNumRecordsPerHistory();

		super.unauthenticate();
	}

	@Test
	public void badtestNewFunctions7() {
		super.authenticate("brotherhood1");
		this.exception.expect(IllegalArgumentException.class);
		this.dashboardService.ratioAreasNoChapter();

		super.unauthenticate();
	}

	@Test
	public void badtestNewFunctions8() {
		super.authenticate("brotherhood1");
		this.exception.expect(IllegalArgumentException.class);
		this.dashboardService.avgParadesPerChapter();

		super.unauthenticate();
	}

	@Test
	public void badtestNewFunctions9() {
		super.authenticate("brotherhood1");
		this.exception.expect(IllegalArgumentException.class);
		this.dashboardService.minParadesPerChapter();

		super.unauthenticate();
	}

	@Test
	public void badtestNewFunctions10() {
		super.authenticate("brotherhood1");
		this.exception.expect(IllegalArgumentException.class);
		this.dashboardService.maxParadesPerChapter();

		super.unauthenticate();
	}

	@Test
	public void badtestNewFunctions11() {
		super.authenticate("brotherhood1");
		this.exception.expect(IllegalArgumentException.class);
		this.dashboardService.maxParadesPerChapter();

		super.unauthenticate();
	}

	@Test
	public void badtestNewFunctions12() {
		super.authenticate("brotherhood1");
		this.exception.expect(IllegalArgumentException.class);
		this.dashboardService.stddevParadesPerChapter();

		super.unauthenticate();
	}

	@Test
	public void badtestNewFunctions13() {
		super.authenticate("brotherhood1");
		this.exception.expect(IllegalArgumentException.class);
		this.dashboardService.chapterMoreParadesThanAvg();

		super.unauthenticate();
	}

	@Test
	public void badtestNewFunctions14() {
		super.authenticate("brotherhood1");
		this.exception.expect(IllegalArgumentException.class);
		this.dashboardService.ratioParadesDraftModeVsFinalMode();

		super.unauthenticate();
	}

	@Test
	public void badtestNewFunctions15() {
		super.authenticate("brotherhood1");
		this.exception.expect(IllegalArgumentException.class);
		this.dashboardService.ratioParadesDraftModeVsFinalMode();

		super.unauthenticate();
	}

	@Test
	public void badtestNewFunctions16() {
		super.authenticate("brotherhood1");
		this.exception.expect(IllegalArgumentException.class);
		this.dashboardService.ratioParadesFinalModeGroupedByStatus();

		super.unauthenticate();
	}
}
