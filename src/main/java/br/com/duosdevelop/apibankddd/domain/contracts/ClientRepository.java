package br.com.duosdevelop.apibankddd.domain.contracts;

import br.com.duosdevelop.apibankddd.domain.Client;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ClientRepository {

    void deleteAll();

    Client save(Client client);

    void delete(Client client);

    Optional<Client> find(Long id);

    Optional<Client> find(String username);

    List<Client> findAll();

    List<Client> findAllBornFrom(LocalDate minDate);
}
