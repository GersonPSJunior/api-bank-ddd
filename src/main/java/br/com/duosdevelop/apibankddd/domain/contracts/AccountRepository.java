package br.com.duosdevelop.apibankddd.domain.contracts;

import br.com.duosdevelop.apibankddd.domain.Account;
import br.com.duosdevelop.apibankddd.domain.AccountNum;

import java.util.Optional;

public interface AccountRepository {

    Optional<Account> find(AccountNum accountNum);

    void deleteAll();

    Account insert(Account account);
}
