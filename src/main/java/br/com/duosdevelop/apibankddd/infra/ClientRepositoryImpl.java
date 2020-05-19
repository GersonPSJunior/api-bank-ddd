package br.com.duosdevelop.apibankddd.infra;

import br.com.duosdevelop.apibankddd.domain.Client;
import br.com.duosdevelop.apibankddd.domain.contracts.ClientRepository;
import br.com.duosdevelop.apibankddd.infra.repositories.ClientJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public class ClientRepositoryImpl implements ClientRepository {

    private final ClientJpaRepository repository;

    @Autowired
    public ClientRepositoryImpl(final ClientJpaRepository repository) {
        this.repository = repository;
    }

    public void deleteAll() {
        repository.deleteAll();
    }

    public Client save(Client client) {
        return repository.save(client);
    }

    public void delete(Client client) {
        repository.delete(client);
    }

    @Override
    public Optional<Client> find(Long id) {
        return repository.findById(id);
    }

    @Override
    public Optional<Client> find(String username) {
        return repository.findByUsername(username);
    }

    public List<Client> findAll() {
        return repository.findAll();
    }

    public List<Client> findAllBornFrom(LocalDate minDate) {
        return repository.findAllByBirthDateGreaterThanEqualOrderByBirthDateDescIdDesc(minDate);
    }
}
