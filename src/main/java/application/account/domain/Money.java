package application.account.domain;

import java.math.BigInteger;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.NonNull;
import lombok.Value;

@Value
public class Money {

    @NonNull
    private final BigInteger amount;

    public static Money ZERO = Money.of(0L);

    @JsonIgnore
    public boolean isPositiveOrZero(){
        return this.amount.compareTo(BigInteger.ZERO) >= 0;
    }

    @JsonIgnore
    public boolean isNegative(){
        return this.amount.compareTo(BigInteger.ZERO) < 0;
    }

    @JsonIgnore
    public boolean isPositive(){
        return this.amount.compareTo(BigInteger.ZERO) > 0;
    }

    @JsonIgnore
    public boolean isGreaterThanOrEqualTo(Money money){
        return this.amount.compareTo(money.amount) >= 0;
    }

    @JsonIgnore
    public boolean isGreaterThan(Money money){
        return this.amount.compareTo(money.amount) >= 1;
    }

    @JsonIgnore
    public static Money of(long value) {
        return new Money(BigInteger.valueOf(value));
    }

    @JsonIgnore
    public static Money add(Money a, Money b) {
        return new Money(a.amount.add(b.amount));
    }

    @JsonIgnore
    public Money minus(Money money){
        return new Money(this.amount.subtract(money.amount));
    }

    @JsonIgnore
    public Money plus(Money money){
        return new Money(this.amount.add(money.amount));
    }

    @JsonIgnore
    public static Money subtract(Money a, Money b) {
        return new Money(a.amount.subtract(b.amount));
    }

    @JsonIgnore
    public Money negate(){
        return new Money(this.amount.negate());
    }
}
