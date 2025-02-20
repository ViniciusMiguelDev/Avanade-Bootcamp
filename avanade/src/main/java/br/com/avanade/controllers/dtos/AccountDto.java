package br.com.avanade.controllers.dtos;

import java.math.BigDecimal;

import br.com.avanade.entities.Account;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {
    private Long id;

    private String number;

    private String agency;

    private BigDecimal balance;

    private BigDecimal limit;

    public AccountDto(Account account) {
        this.id = account.getId();
        this.number = account.getNumber();
        this.agency = account.getAgency();
        this.balance = account.getBalance();
        this.limit = account.getLimit();
    }
}
