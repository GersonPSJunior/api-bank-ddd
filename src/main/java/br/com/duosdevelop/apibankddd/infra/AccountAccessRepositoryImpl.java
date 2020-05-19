package br.com.duosdevelop.apibankddd.infra;

import br.com.duosdevelop.apibankddd.domain.Account;
import br.com.duosdevelop.apibankddd.domain.AccountAccess;
import br.com.duosdevelop.apibankddd.domain.Amount;
import br.com.duosdevelop.apibankddd.domain.Client;
import br.com.duosdevelop.apibankddd.domain.contracts.AccountAccessRepository;
import br.com.duosdevelop.apibankddd.infra.repositories.AccountAccessJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AccountAccessRepositoryImpl implements AccountAccessRepository {

    private AccountAccessJpaRepository repository;

    @Autowired
    public AccountAccessRepositoryImpl(AccountAccessJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public AccountAccess insert(AccountAccess accountAccess) {
        return repository.save(accountAccess);
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }

    @Override
    public void delete(AccountAccess accountAccess) {
        repository.delete(accountAccess);
    }

    @Override
    public List<AccountAccess> findManagedAccountsOf(Client client, boolean asOwner) {
        return repository.findAllByClientAndIsOwnerGreaterThanEqualOrderByIdDesc(client, asOwner);
    }

    @Override
    public List<AccountAccess> findFullAccounts(Amount minBalance) {
        return repository.findAllByAccountBalanceCentsGreaterThanEqualOrderByAccountBalanceCentsDescClientIdDesc(minBalance.getCents());
    }

    @Override
    public Optional<AccountAccess> find(Client client, Account account) {
        return repository.findByClientAndAccount(client, account);
    }
}
