package com.mariesto.buckpal.account.adapter.in.web;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import com.mariesto.buckpal.account.application.port.in.sendmoney.SendMoneyCommand;
import com.mariesto.buckpal.account.application.port.in.sendmoney.SendMoneyUseCase;
import com.mariesto.buckpal.account.domain.Account;
import com.mariesto.buckpal.account.domain.Money;

@SpringBootTest (classes = { SendMoneyController.class })
@AutoConfigureMockMvc
class SendMoneyControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SendMoneyUseCase sendMoneyUseCase;

    @Test
    void sendMoney() throws Exception {
        mockMvc.perform(post("/accounts/money/send/{source_account_id}/{destination_account_id}/{amount}", 1001, 1002, 500).header("Content-Type",
                "application/json")).andExpect(status().isOk());

        then(sendMoneyUseCase).should()
                              .sendMoney(eq(new SendMoneyCommand(new Account.AccountId(1001), new Account.AccountId(1002), Money.of(500))));
    }
}