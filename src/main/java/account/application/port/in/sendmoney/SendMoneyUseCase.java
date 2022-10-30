package account.application.port.in.sendmoney;

public interface SendMoneyUseCase {

    boolean sendMoney(SendMoneyCommand command);

}
