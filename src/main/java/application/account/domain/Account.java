package application.account.domain;

import static lombok.AccessLevel.PRIVATE;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

@AllArgsConstructor (access = PRIVATE)
public class Account {
    @Getter
    private AccountId id;

    @Getter
    private Money baselineBalance;

    @Getter
    private ActivityWindow activityWindow;

    public static Account withId(AccountId accountId, Money baselineBalance, ActivityWindow activityWindow) {
        return new Account(accountId, baselineBalance, activityWindow);
    }

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

        Activity withdrawal = new Activity(this.id, this.id, destinationAccountId, Timestamp.valueOf(LocalDateTime.now()), money);

        this.activityWindow.addActivity(withdrawal);
        return true;
    }

    private boolean isWithdrawAllowed(Money money) {
        return Money.add(this.baselineBalance, money.negate()).isPositive();
    }

    public boolean deposit(Money money, AccountId accountId) {
        Activity activity = new Activity(this.id, accountId, this.id, Timestamp.valueOf(LocalDateTime.now()), money);
        this.activityWindow.addActivity(activity);
        return true;
    }

    @Value
    public static class AccountId {
        Integer value;
    }
}
