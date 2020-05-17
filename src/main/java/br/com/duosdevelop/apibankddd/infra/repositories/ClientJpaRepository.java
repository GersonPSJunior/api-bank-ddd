package br.com.duosdevelop.apibankddd.infra.repositories;

import br.com.duosdevelop.apibankddd.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ClientJpaRepository extends JpaRepository<Client, Long> {

    Optional<Client> findByUsername(String username);

    List<Client> findAllByBirthDateGreaterThanEqualOrderByBirthDateDescIdDesc(LocalDate minDate);
}
