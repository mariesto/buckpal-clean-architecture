package com.mariesto.buckpal.account.adapter.out.persistence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table (name = "account")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AccountJpaEntity {
    @Id
    @GeneratedValue
    @Column (name = "account_id")
    private Integer accountId;
}
