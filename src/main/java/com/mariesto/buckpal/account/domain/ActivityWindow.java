package com.mariesto.buckpal.account.domain;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import com.mariesto.buckpal.account.domain.Account.AccountId;
import lombok.NonNull;

public class ActivityWindow {
    private List<Activity> activities;

    public ActivityWindow(@NonNull List<Activity> activities) {
        this.activities = activities;
    }

    public ActivityWindow(@NonNull Activity... activities) {
        this.activities = new ArrayList<>(Arrays.asList(activities));
    }

    public List<Activity> getActivities() {
        return Collections.unmodifiableList(this.activities);
    }

    public void addActivity(Activity activity) {
        this.activities.add(activity);
    }

    public Timestamp getStartTimestamp() {
        return activities.stream().min(Comparator.comparing(Activity::getTimestamp)).orElseThrow(IllegalStateException::new).getTimestamp();
    }

    public Timestamp getEndTimestamp() {
        return activities.stream().max(Comparator.comparing(Activity::getTimestamp)).orElseThrow(IllegalStateException::new).getTimestamp();
    }

    public Money calculateBalance(AccountId id) {
        Money depositBalance = activities.stream().filter(activity -> activity.getDestinationAccountId().equals(id)).map(Activity::getMoney)
                                         .reduce(Money.ZERO, Money::add);
        Money withdrawalBalance = activities.stream().filter(activity -> activity.getSourceAccountId().equals(id)).map(Activity::getMoney)
                                            .reduce(Money.ZERO, Money::add);
        return Money.add(depositBalance, withdrawalBalance.negate());
    }

}
