package br.com.duosdevelop.apibankddd.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.regex.Pattern;

@Service
public class BankService {

    private ClientRepository clientRepository;

    @Autowired
    public BankService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client create(String username, LocalDate birthDate){
        Pattern pattern = Pattern.compile("[a-z_A-Z][a-z_A-Z0-9]{0,30}");
        if(!pattern.matcher(username).matches()){
            throw new IllegalArgumentException("Usuário inválido!");
        }
        return clientRepository.save(new Client(username, birthDate));
    }
}
