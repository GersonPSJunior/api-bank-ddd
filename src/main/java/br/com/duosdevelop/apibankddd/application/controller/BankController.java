package br.com.duosdevelop.apibankddd.application.controller;

import br.com.duosdevelop.apibankddd.application.Util;
import br.com.duosdevelop.apibankddd.application.dto.ClientDTO;
import br.com.duosdevelop.apibankddd.domain.Client;
import br.com.duosdevelop.apibankddd.domain.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/bank")
public class BankController {

    private ClientService clientService;

    @Autowired
    public BankController(ClientService clientService) {
        this.clientService = clientService;
    }

    @RequestMapping(path = "/client", method = RequestMethod.POST)
    public ResponseEntity<ClientDTO> insertClient(@RequestBody ClientDTO clientDTO){
        if (clientDTO.id != null) {
            throw new IllegalArgumentException("Id invalído!");
        }
        LocalDate birthLocalDate = LocalDate.parse(clientDTO.birthDate, Util.MEDIUM_DATE_FORMATTER);
        Client client = clientService.create(clientDTO.username, birthLocalDate);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ClientDTO(client));
    }

    @RequestMapping(path = "/client/{id}", method = RequestMethod.GET)
    public ResponseEntity<ClientDTO> findClient(@PathVariable Long id){
        return ResponseEntity.ok(new ClientDTO(clientService.find(id)));
    }

}
