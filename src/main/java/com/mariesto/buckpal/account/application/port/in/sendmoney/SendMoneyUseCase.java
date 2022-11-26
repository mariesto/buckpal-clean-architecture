package com.mariesto.buckpal.account.application.port.in.sendmoney;

import org.springframework.stereotype.Service;

@Service
public interface SendMoneyUseCase {

    boolean sendMoney(SendMoneyCommand command);

}
