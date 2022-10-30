package account.domain;

import lombok.Value;

@Value
public class AccountRequest {

    private Account.AccountId accountId;

    private Money money;

}
