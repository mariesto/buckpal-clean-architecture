package account.domain;

import static lombok.AccessLevel.PRIVATE;
import java.time.LocalDateTime;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.Value;

@AllArgsConstructor (access = PRIVATE)
public class Account {
    private AccountId id;

    private Money baselineBalance;

    private ActivityWindow activityWindow;

    public static Account withoutId(Money baselineBalance, ActivityWindow activityWindow) {
        return new Account(null, baselineBalance, activityWindow);
    }

    public Optional<AccountId> getId() {
        return Optional.ofNullable(this.id);
    }

    public Money calculateBalance() {
        return Money.add(this.baselineBalance, this.activityWindow.calculateBalance(this.id));
    }

    public boolean withdraw(Money money, AccountId destinationAccountId) {
        if (!isWithdrawAllowed(money)) {
            return false;
        }

        Activity withdrawal = new Activity(this.id, this.id, destinationAccountId, LocalDateTime.now(), money);

        this.activityWindow.addActivity(withdrawal);
        return true;
    }

    private boolean isWithdrawAllowed(Money money) {
        return Money.add(this.baselineBalance, money.negate()).isPositive();
    }

    public boolean deposit(Money money, AccountId accountId) {
        Activity activity = new Activity(this.id, accountId, this.id, LocalDateTime.now(), money);
        this.activityWindow.addActivity(activity);
        return true;
    }

    @Value
    public static class AccountId {
        private Long value;
    }
}
