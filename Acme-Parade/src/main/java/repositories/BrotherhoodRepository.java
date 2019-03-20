
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Brotherhood;

@Repository
public interface BrotherhoodRepository extends JpaRepository<Brotherhood, Integer> {

	@Query("select c from Brotherhood c where c.userAccount.id = ?1")
	Brotherhood findByUserAccountId(int userAccount);

	@Query("select a1 from Brotherhood a1 join a1.userAccount user where user.username = ?1")
	Brotherhood getBrotherhoodByUserAccount(String username);

	@Query("select m1.brotherhood from Member m join m.enrolements m1 where m.id =?1 and m1.status ='APPROVED'")
	Collection<Brotherhood> findAllNotApproved(int id);
}
