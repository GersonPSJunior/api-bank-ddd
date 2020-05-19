package br.com.duosdevelop.apibankddd.infra.repositories;

import br.com.duosdevelop.apibankddd.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountJpaRepository extends JpaRepository<Account, Long> {
}
