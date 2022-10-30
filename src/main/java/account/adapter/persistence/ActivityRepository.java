package account.adapter.persistence;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ActivityRepository extends JpaRepository<ActivityJpaEntity, Long> {

    @Query ("select a from ActivityJpaEntity a where a.ownerAccountId = ?1 and a.timeStamp > ?2")
    List<ActivityJpaEntity> findByOwnerSince(Long ownerAccountId, LocalDateTime timeStamp);

    @Query ("select sum(a.amount) from ActivityJpaEntity a where a.destinationAccountId = ?1 and a.ownerAccountId = ?2 and timestamp < ?3")
    Long getDepositBalanceUntil(Long accountId, LocalDateTime until);

    @Query ("select sum(a.amount) from ActivityJpaEntity a where a.sourceAccountId = ?1 and a.ownerAccountId = ?2 and timestamp < ?3")
    Long getWithdrawalBalanceUntil(Long accountId, LocalDateTime until);
}
