
package repositories;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Administrator;
import domain.Brotherhood;
import domain.Member;

@Repository
public interface DashboardRepository extends JpaRepository<Administrator, Integer> {

	//12.3.1 
	@Query("select min(e.size) from Brotherhood b join b.enrolements e where e.status ='APPROVED'")
	double minMembers();

	@Query("select max(e.size) from Brotherhood b join b.enrolements e where e.status ='APPROVED'")
	double maxMembers();

	@Query("select avg(e.size) from Brotherhood b join b.enrolements e where e.status ='APPROVED'")
	double avgMembers();

	@Query("select stddev(e.size) from Brotherhood b join b.enrolements e where e.status ='APPROVED'")
	double stddevMembers();

	//12.3.2
	@Query("select b from Brotherhood b join b.enrolements e where e.status='APPROVED' and e.dropOutMoment is null order by count(e) desc")
	Collection<Brotherhood> largestBrotherhoods();

	//12.3.3
	@Query("select b from Brotherhood b join b.enrolements e where e.status='APPROVED' and e.dropOutMoment is null order by count(e) asc")
	Collection<Brotherhood> smallestBrotherhoods();

	//12.3.4
	@Query("select 1.0 * count(r)/(select count(r1) from Request r1), r.parade.title, r.status from Request r group by r.status, r.parade")
	List<Object[]> requestRatioByParade();

	//12.3.5 in service
	//12.3.6
	@Query("select 1.0 * count(r1)/(select count(r) from Request r) from Request r1 group by r1.status")
	List<Double> requestRatio();

	//12.3.7 
	@Query("select distinct m from Member m join m.requests r1 where r1.status='APPROVED' group by m.id having count(r1)> 0.1 * m.requests.size")
	Collection<Member> membersWith10PercentRequestsApproved();

	//12.3.8
	@Query("select e.position.positionEng, count(e) from Enrolement e group by e.position")
	List<Object[]> positionHistogram();
	//22.2.1
	@Query("select 1.0 * count(b)/(select count(b1) from Brotherhood b1) from Area a join a.brotherhoods b")
	double ratioBrotherhoodsPerArea();

	@Query("select a.brotherhoods.size from Area a")
	Collection<Double> countBrotherhoodsPerArea();

	@Query("select min(a.brotherhoods.size) from Area a")
	double minBrotherhoodsPerArea();

	@Query("select max(a.brotherhoods.size) from Area a")
	double maxBrotherhoodsPerArea();

	@Query("select avg(a.brotherhoods.size) from Area a")
	double avgBrotherhoodsPerArea();

	@Query("select stddev(a.brotherhoods.size) from Area a")
	double stddevBrotherhoodsPerArea();
	//22.2.2
	@Query("select min(f.parades.size) from Finder f")
	double minFinderResults();

	@Query("select max(f.parades.size) from Finder f")
	double maxFinderResults();

	@Query("select avg(f.parades.size) from Finder f")
	double avgFinderResults();

	@Query("select stddev(f.parades.size) from Finder f")
	double stddevFinderResults();

	//22.2.3
	@Query("select 1.0 * count(f)/(select count(f1) from Finder f1) from Finder f where f.keyword is null and f.startDate is null and f.endDate is null and f.area is null")
	double ratioEmptyFinders();

}
