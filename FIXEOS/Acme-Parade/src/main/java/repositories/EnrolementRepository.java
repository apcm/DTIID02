
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Enrolement;

@Repository
public interface EnrolementRepository extends JpaRepository<Enrolement, Integer> {

	@Query("select e from Member m join m.enrolements e where e.brotherhood.id=?1 and m.id=?2")
	public Enrolement findEnrolementByIds(Integer brotherhoodId, Integer memberId);

	@Query("select e from Member m join m.enrolements e where m.id=?1")
	public Collection<Enrolement> findEnrolementsByMemberId(Integer memberId);

	@Query("select e1 from Member m join m.enrolements e1 where m.id=?1 and e1.brotherhood.id=?2")
	public Enrolement existEnrolement(Integer memberId, Integer brotherhoodId);

	@Query("select e from Enrolement e where e.brotherhood.id=?1 and e.status='PENDING'")
	public Collection<Enrolement> enrolementsPending(Integer brotherhoodId);

}
