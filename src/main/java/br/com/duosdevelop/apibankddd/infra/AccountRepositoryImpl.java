package br.com.duosdevelop.apibankddd.infra;

import br.com.duosdevelop.apibankddd.domain.Account;
import br.com.duosdevelop.apibankddd.domain.AccountNum;
import br.com.duosdevelop.apibankddd.domain.contracts.AccountRepository;
import br.com.duosdevelop.apibankddd.infra.repositories.AccountJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class AccountRepositoryImpl implements AccountRepository {

    private AccountJpaRepository repository;

    @Autowired
    public AccountRepositoryImpl(AccountJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Account> find(AccountNum accountNum) {
        return repository.findById(accountNum.toLong());
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }

    @Override
    public Account insert(Account account) {
        return repository.save(account);
    }
}
