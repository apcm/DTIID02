
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import security.UserAccount;
import domain.Actor;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Integer> {

	@Query("select a from Actor a where a.userAccount.id = ?1")
	Actor findByUserAccountId(int userAccount);

	//38.2
	@Query("select s from Message m join m.sender s where m.flagSpam=true")
	Collection<Actor> suspiciousActors();

	@Query("select a1 from Actor a1 where a1.userAccount = ?1")
	public Actor getActor(UserAccount ua);
}
