package br.com.duosdevelop.apibankddd.infra.repositories;

import br.com.duosdevelop.apibankddd.domain.Account;
import br.com.duosdevelop.apibankddd.domain.AccountAccess;
import br.com.duosdevelop.apibankddd.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccountAccessJpaRepository extends JpaRepository<AccountAccess, Long> {

    List<AccountAccess> findAllByClientAndIsOwnerGreaterThanEqualOrderByIdDesc(Client client, boolean asOwner);

    Optional<AccountAccess> findByClientAndAccount(Client client, Account account);

    List<AccountAccess> findAllByAccountBalanceCentsGreaterThanEqualOrderByAccountBalanceCentsDescClientIdDesc(long minCents);
}
