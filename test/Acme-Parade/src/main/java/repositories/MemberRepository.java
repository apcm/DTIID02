
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {

	@Query("select m from Member m join m.enrolements m1 where m1.brotherhood.id=?1 and m1.status='APPROVED'")
	public Collection<Member> membersByBrotherhood(int id);

	@Query("select m from Member m join m.enrolements m1 where m1.id=?1")
	public Member memberByEnrolementId(int id);

	@Query("select m from Member m where m.userAccount.id = ?1")
	Member findByUserAccountId(int userAccount);

	@Query("select m from Member m join m.requests r where r.id = ?1")
	Member findByRequestId(int requestId);
}
