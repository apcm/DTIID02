
package repositories;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import domain.Parade;

public interface ParadeRepository extends JpaRepository<Parade, Integer> {

	@Query("select p from Parade p where p.brotherhood.id = ?1")
	Collection<Parade> findByBrotherhoodId(int brotherhoodId);

	@Query("select p from Request r join r.parade p where r.id=?1")
	Parade findByRequestId(Integer requestId);

	@Query("select p from Parade p where p.finalMode='1'")
	Collection<Parade> findAllFinalMode();

	//Finder
	@Query("select p from Parade p where p.title like %?1% or p.description like %?1%")
	Collection<Parade> findParadesByKeyword(String keyword);

	@Query("select p from Parade p where p.brotherhood.area.id=?1")
	Collection<Parade> findParadesByAreaId(int id);

	@Query("select p from Parade p where p.departureDate > ?1")
	Collection<Parade> findParadesByMinimumDate(Date minDate);

	@Query("select p from Parade p where p.departureDate < ?1")
	Collection<Parade> findParadesByMaximumDate(Date maxDate);

	@Query("select p from Parade p where p.departureDate between ?1 and ?2")
	Collection<Parade> findParadesByDateRange(Date min, Date max);

	@Query("select p from Parade p join p.brotherhood.enrolements e where e.id=?1")
	Collection<Parade> findByEnrolementId(int id);

	@Query("select p from Parade p join p.brotherhood.enrolements e where e.id=?1 and e.status='APPROVED'")
	Collection<Parade> findByEnrolementIdApproved(int id);

	@Query("select p from Parade p where p.finalMode='1' and p.departureDate>=?1")
	Collection<Parade> findAllFinalModeRequests(Date today);

}
