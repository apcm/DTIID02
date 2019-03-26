
package services;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import utilities.AbstractTest;

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

		this.dashboardService.largerThanAvgHistoryBrotherhood();

		super.unauthenticate();
	}

	@Test
	public void testNewFunctions2() {
		super.authenticate("admin1");

		this.dashboardService.largestHistoryBrotherhood();

		super.unauthenticate();
	}

	@Test
	public void testNewFunctions3() {
		super.authenticate("admin1");

		this.dashboardService.maxNumRecordsPerHistory();

		super.unauthenticate();
	}

	@Test
	public void testNewFunctions4() {
		super.authenticate("admin1");

		this.dashboardService.minNumRecordsPerHistory();

		super.unauthenticate();
	}

	@Test
	public void testNewFunctions5() {
		super.authenticate("admin1");

		this.dashboardService.stddevNumRecordsPerHistory();

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
}
