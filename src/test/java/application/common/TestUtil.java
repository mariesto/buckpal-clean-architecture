package application.common;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import application.account.domain.Account;
import application.account.domain.Account.AccountId;
import application.account.domain.Activity;
import application.account.domain.Activity.ActivityId;
import application.account.domain.ActivityWindow;
import application.account.domain.Money;

public class TestUtil {

    public static AccountBuilder defaultAccount() {
        return new AccountBuilder()
                .withAccountId(new AccountId(42))
                .withBaselineBalance(Money.of(999L))
                .withActivityWindow(new ActivityWindow(
                        defaultActivity().build(),
                        defaultActivity().build()));
    }

    public static class AccountBuilder {

        private AccountId accountId;
        private Money baselineBalance;
        private ActivityWindow activityWindow;

        public AccountBuilder withAccountId(AccountId accountId) {
            this.accountId = accountId;
            return this;
        }

        public AccountBuilder withBaselineBalance(Money baselineBalance) {
            this.baselineBalance = baselineBalance;
            return this;
        }

        public AccountBuilder withActivityWindow(ActivityWindow activityWindow) {
            this.activityWindow = activityWindow;
            return this;
        }

        public Account build() {
            return Account.withId(this.accountId, this.baselineBalance, this.activityWindow);
        }
    }

    public static ActivityBuilder defaultActivity(){
        return new ActivityBuilder()
                .withOwnerAccount(new AccountId(42))
                .withSourceAccount(new AccountId(42))
                .withTargetAccount(new AccountId(41))
                .withTimestamp(Timestamp.valueOf(LocalDateTime.now()))
                .withMoney(Money.of(999L));
    }

    public static class ActivityBuilder {
        private ActivityId id;
        private AccountId ownerAccountId;
        private AccountId sourceAccountId;
        private AccountId targetAccountId;
        private Timestamp timestamp;
        private Money money;

        public ActivityBuilder withId(ActivityId id) {
            this.id = id;
            return this;
        }

        public ActivityBuilder withOwnerAccount(AccountId accountId) {
            this.ownerAccountId = accountId;
            return this;
        }

        public ActivityBuilder withSourceAccount(AccountId accountId) {
            this.sourceAccountId = accountId;
            return this;
        }

        public ActivityBuilder withTargetAccount(AccountId accountId) {
            this.targetAccountId = accountId;
            return this;
        }

        public ActivityBuilder withTimestamp(Timestamp timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public ActivityBuilder withMoney(Money money) {
            this.money = money;
            return this;
        }

        public Activity build() {
            return new Activity(
                    this.id,
                    this.ownerAccountId,
                    this.sourceAccountId,
                    this.targetAccountId,
                    this.timestamp,
                    this.money);
        }
    }
}
