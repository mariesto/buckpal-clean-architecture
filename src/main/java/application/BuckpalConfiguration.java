package application;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import application.account.application.port.in.sendmoney.SendMoneyUseCase;
import application.account.application.port.out.AccountLock;
import application.account.application.port.out.LoadAccountPort;
import application.account.application.port.out.UpdateAccountStatePort;
import application.account.application.service.SendMoneyService;

@EnableJpaRepositories (basePackages = "application.account.adapter.out.persistence")
@Configuration
public class BuckpalConfiguration {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public SendMoneyUseCase sendMoneyUseCase(AccountLock accountLock, LoadAccountPort loadAccountPort, UpdateAccountStatePort updateAccountStatePort){
        return new SendMoneyService(accountLock, loadAccountPort, updateAccountStatePort);
    }

}
