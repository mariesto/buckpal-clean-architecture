package account.adapter.persistence;

import static account.domain.Account.AccountId;
import static account.domain.Account.withId;
import static account.domain.Activity.ActivityId;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import account.domain.Account;
import account.domain.Activity;
import account.domain.ActivityWindow;
import account.domain.Money;

@Component
public class AccountMapper {

    Account mapToDomainEntity(AccountJpaEntity account, List<ActivityJpaEntity> activities, Long withdrawalBalance, Long depositBalance) {
        Money baselineBalance = Money.subtract(Money.of(depositBalance), Money.of(withdrawalBalance));
        return withId(new AccountId(account.getId()), baselineBalance, mapToActivityWindow(activities));
    }

    ActivityWindow mapToActivityWindow(List<ActivityJpaEntity> activities) {
        List<Activity> mappedActivities = new ArrayList<>();

        for (ActivityJpaEntity activity : activities) {
            mappedActivities.add(new Activity(new ActivityId(activity.getId()), new AccountId(activity.getOwnerAccountId()),
                    new AccountId(activity.getSourceAccountId()), new AccountId(activity.getDestinationAccountId()),
                    activity.getTimeStamp(), Money.of(activity.getAmount())));
        }

        return new ActivityWindow(mappedActivities);
    }

    ActivityJpaEntity mapToJpaEntity(Activity activity) {
        return new ActivityJpaEntity(activity.getId() == null ? null : activity.getId().getValue(), activity.getTimestamp(),
                activity.getOwnerAccountId().getValue(), activity.getSourceAccountId().getValue(), activity.getDestinationAccountId().getValue(),
                activity.getMoney().getAmount().longValue());
    }
}
