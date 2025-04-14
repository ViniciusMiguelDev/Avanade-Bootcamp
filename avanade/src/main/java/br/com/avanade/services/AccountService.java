package br.com.avanade.services;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.avanade.exceptions.NotFoundException;
import br.com.avanade.models.Account;
import br.com.avanade.repositories.AccountRepository;
import br.com.avanade.repositories.UserRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class AccountService {
    private final AccountRepository accountRepository;
    private final UserRepository userRepository;

    public Account create(Long id, Account account) {

        if (!userRepository.existsById(id)) {
            throw new NotFoundException("Não existe um usuário com esse id: " + id);
        }

        account.setUser(userRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado")));
        account.setId(null);
        return accountRepository.save(account);
    }

    public Account read(Long id) {
        return accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    public List<Account> readAll() {
        return accountRepository.findAll();
    }

    public Account update(Long idUser, Long idAccount, Account account) {

        if (!userRepository.existsById(idUser)) {
            throw new NotFoundException("Não existe um usuário com esse id: " + idUser);
        }

        Account newAccount = accountRepository.findById(idAccount)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        newAccount.setNumber(account.getNumber());
        newAccount.setAgency(account.getAgency());
        newAccount.setBalance(account.getBalance());
        newAccount.setLimit(account.getLimit());
        newAccount.setUser(account.getUser());

        return accountRepository.save(newAccount);
    }

    public void delete(Long id) {
        accountRepository.deleteById(id);
    }
}
