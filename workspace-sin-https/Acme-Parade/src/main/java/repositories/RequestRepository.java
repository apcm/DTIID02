
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Request;

@Repository
public interface RequestRepository extends JpaRepository<Request, Integer> {

	@Query("select r from Request r where r.parade.id = ?1")
	Collection<Request> findByParadeId(int paradeId);

	@Query("select r from Member m join m.requests r where r.parade.id=?1 and m.id=?2")
	public Request findRequestByIds(Integer paradeId, Integer memberId);

	@Query("select r from Member m join m.requests r where m.id=?1")
	public Collection<Request> findRequestByMemberId(Integer memberId);

	@Query("select r from Request r join r.parade p where p.id=?1")
	public Collection<Request> findRequestByParadeId(Integer paradeId);

	@Query("select r from Member m join m.requests r where m.id=?1 and r.parade.id=?2")
	public Request existRequest(Integer memberId, Integer paradeId);

	@Query("select r from Request r where r.parade.id=?1 and r.status='PENDING'")
	public Collection<Request> requestPending(Integer paradeId);

	@Query("select r from Request r where r.parade.id=?1 and r.status='APPROVED'")
	public Collection<Request> findAllApproved(Integer paradeId);

	@Query("select r from Request r join r.parade p where r.rowPosition=?1 and r.columnPosition=?2 and p.id=?3")
	public Request checkPosition(Integer row, Integer column, Integer paradeId);

}
