package br.com.duosdevelop.apibankddd.domain.contracts;

import br.com.duosdevelop.apibankddd.domain.Account;
import br.com.duosdevelop.apibankddd.domain.AccountAccess;
import br.com.duosdevelop.apibankddd.domain.Amount;
import br.com.duosdevelop.apibankddd.domain.Client;

import java.util.List;
import java.util.Optional;

public interface AccountAccessRepository {

    AccountAccess insert(AccountAccess accountAccess);

    void deleteAll();

    void delete(AccountAccess accountAccess);

    List<AccountAccess> findManagedAccountsOf(Client client, boolean asOwner);

    List<AccountAccess> findFullAccounts(Amount minBalance);

    Optional<AccountAccess> find(Client client, Account account);
}
