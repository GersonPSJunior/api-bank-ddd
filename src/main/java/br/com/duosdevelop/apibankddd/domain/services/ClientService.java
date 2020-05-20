package br.com.duosdevelop.apibankddd.domain.services;

import br.com.duosdevelop.apibankddd.domain.Client;
import br.com.duosdevelop.apibankddd.domain.contracts.ClientRepository;
import br.com.duosdevelop.apibankddd.domain.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class ClientService {

    private ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client create(String username, LocalDate birthDate){
        Pattern pattern = Pattern.compile("[a-z_A-Z][a-z_A-Z0-9]{0,30}");
        if(!pattern.matcher(username).matches()){
            throw new IllegalArgumentException("Usuário inválido!");
        }
        return clientRepository.save(new Client(username, birthDate));
    }

    public Client find(Long id) {
        return clientRepository.find(id).orElseThrow(() -> new ObjectNotFoundException("Object not found! Id:"+ id
        +", Type:"+ Client.class.getName()));
    }

    public void delete(Long id) {
        Client client = find(id);
        clientRepository.delete(client);
    }

    public List<Client> findAllClients() {
        return clientRepository.findAll();
    }

    public List<Client> findAllBornFrom(LocalDate localDate) {
        return clientRepository.findAllBornFrom(localDate);
    }
}
