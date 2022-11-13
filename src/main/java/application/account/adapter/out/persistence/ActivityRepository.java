package application.account.adapter.out.persistence;

import java.sql.Timestamp;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityRepository extends JpaRepository<ActivityJpaEntity, Integer> {
    @Query (value = "select * from activity a where a.owner_account_id = :ownerAccountId and a.time_stamp > :timeStamp", nativeQuery = true)
    List<ActivityJpaEntity> findByOwnerSince(@Param ("ownerAccountId") Integer ownerAccountId, @Param ("timeStamp") Timestamp timeStamp);

    @Query (value = "select sum(a.amount) from activity a where a.destination_account_id = :accountId and a.owner_account_id = :accountId and a.time_stamp < :timeStamp", nativeQuery = true)
    Long getDepositBalanceUntil(@Param ("accountId") Integer accountId, @Param ("timeStamp") Timestamp timeStamp);

    @Query (value = "select sum(a.amount) from activity a where a.source_account_id = :accountId and a.owner_account_id = :accountId and a.time_stamp < :timeStamp", nativeQuery = true)
    Long getWithdrawalBalanceUntil(@Param ("accountId") Integer accountId, @Param ("timeStamp") Timestamp timeStamp);

}
