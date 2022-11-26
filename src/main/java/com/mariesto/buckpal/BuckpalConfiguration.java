package com.mariesto.buckpal;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import com.mariesto.buckpal.account.adapter.out.persistence.AccountRepository;
import com.mariesto.buckpal.account.application.port.in.sendmoney.SendMoneyUseCase;
import com.mariesto.buckpal.account.adapter.out.persistence.AccountMapper;
import com.mariesto.buckpal.account.adapter.out.persistence.AccountPersistenceAdapter;
import com.mariesto.buckpal.account.adapter.out.persistence.ActivityRepository;
import com.mariesto.buckpal.account.application.port.out.AccountLock;
import com.mariesto.buckpal.account.application.port.out.LoadAccountPort;
import com.mariesto.buckpal.account.application.port.out.UpdateAccountStatePort;
import com.mariesto.buckpal.account.application.service.SendMoneyService;

@EnableJpaRepositories (basePackages = "application.account.adapter.out.persistence")
@Configuration
public class BuckpalConfiguration {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public AccountMapper accountMapper(){
        return new AccountMapper();
    }

    @Bean
    public SendMoneyUseCase sendMoneyUseCase(AccountLock accountLock, LoadAccountPort loadAccountPort, UpdateAccountStatePort updateAccountStatePort){
        return new SendMoneyService(accountLock, loadAccountPort, updateAccountStatePort);
    }

    @Bean
    public AccountPersistenceAdapter accountPersistenceAdapter(AccountRepository accountRepository, ActivityRepository activityRepository, AccountMapper accountMapper){
        return new AccountPersistenceAdapter(accountRepository, activityRepository, accountMapper);
    }

}
