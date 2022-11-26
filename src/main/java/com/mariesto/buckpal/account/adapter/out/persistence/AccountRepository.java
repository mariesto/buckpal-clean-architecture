package com.mariesto.buckpal.account.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<AccountJpaEntity, Integer> {
    @Query (value = "select distinct(a.account_id) from account a where a.account_id = :id", nativeQuery = true)
    AccountJpaEntity findAccountJpaEntitiesByAccountId(Integer id);

}
