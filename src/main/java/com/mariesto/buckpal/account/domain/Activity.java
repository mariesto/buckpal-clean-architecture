package com.mariesto.buckpal.account.domain;

import java.sql.Timestamp;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@JsonIgnoreProperties (ignoreUnknown = true)
@Value
@Getter
@RequiredArgsConstructor
public class Activity {
    @Getter
    private ActivityId id;

    @Getter
    @NonNull
    private final Account.AccountId ownerAccountId;

    @Getter
    @NonNull
    private final Account.AccountId sourceAccountId;

    @Getter
    @NonNull
    private final Account.AccountId destinationAccountId;

    @Getter
    @NonNull
    private final Timestamp timestamp;

    @Getter
    @NonNull
    private final Money money;

    public Activity(@NonNull Account.AccountId ownerAccountId, @NonNull Account.AccountId sourceAccountId,
            @NonNull Account.AccountId destinationAccountId, @NonNull Timestamp timestamp, @NonNull Money money) {
        this.id = null;
        this.ownerAccountId = ownerAccountId;
        this.sourceAccountId = sourceAccountId;
        this.destinationAccountId = destinationAccountId;
        this.timestamp = timestamp;
        this.money = money;
    }

    @Value
    public static class ActivityId {
        Integer value;
    }

}
