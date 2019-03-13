
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Request;

@Repository
public interface RequestRepository extends JpaRepository<Request, Integer> {

	@Query("select r from Request r where r.procession.id = ?1")
	Collection<Request> findByProcessionId(int processionId);

	@Query("select r from Member m join m.requests r where r.procession.id=?1 and m.id=?2")
	public Request findRequestByIds(Integer processionId, Integer memberId);

	@Query("select r from Member m join m.requests r where m.id=?1")
	public Collection<Request> findRequestByMemberId(Integer memberId);

	@Query("select r from Request r join r.procession p where p.id=?1")
	public Collection<Request> findRequestByProcessionId(Integer proccesionId);

	@Query("select r from Member m join m.requests r where m.id=?1 and r.procession.id=?2")
	public Request existRequest(Integer memberId, Integer processionId);

	@Query("select r from Request r where r.procession.id=?1 and r.status='PENDING'")
	public Collection<Request> requestPending(Integer processionId);

	@Query("select r from Request r where r.procession.id=?1 and r.status='APPROVED'")
	public Collection<Request> findAllApproved(Integer processionId);

	@Query("select r from Request r join r.procession p where r.rowPosition=?1 and r.columnPosition=?2 and p.id=?3")
	public Request checkPosition(Integer row, Integer column, Integer processionId);

}
