package br.com.duosdevelop.apibankddd.application.controller;

import br.com.duosdevelop.apibankddd.application.Util;
import br.com.duosdevelop.apibankddd.application.dto.ClientDTO;
import br.com.duosdevelop.apibankddd.application.exceptions.ParamException;
import br.com.duosdevelop.apibankddd.domain.Client;
import br.com.duosdevelop.apibankddd.domain.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/bank")
public class BankController {

    private ClientService clientService;

    @Autowired
    public BankController(ClientService clientService) {
        this.clientService = clientService;
    }

    @RequestMapping(path = "/clients", method = RequestMethod.POST)
    public ResponseEntity<ClientDTO> insertClient(@RequestBody ClientDTO clientDTO){
        if (clientDTO.id != null) {
            throw new IllegalArgumentException("Id invalído!");
        }
        LocalDate birthLocalDate = LocalDate.parse(clientDTO.birthDate, Util.MEDIUM_DATE_FORMATTER);
        Client client = clientService.create(clientDTO.username, birthLocalDate);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ClientDTO(client));
    }

    @RequestMapping(path = "/clients", method = RequestMethod.GET)
    public ResponseEntity<List<ClientDTO>> findAllClients(
            @RequestParam(name = "fromBirth", defaultValue = "") String birthDate,
            @RequestParam(name = "minBalance", defaultValue = "") String minBalance){
        List<Client> clients;
        if(!"".equals(birthDate) && !"".equals(minBalance))
            throw new ParamException("Must not provide both parameters: fromBirth and minBalance!");

        if(!"".equals(birthDate))
            clients = clientService.findAllBornFrom(LocalDate.parse(birthDate, Util.MEDIUM_DATE_FORMATTER));
        else if(!"".equals(minBalance)){
            clients = new ArrayList<>();
        } else {
            clients = clientService.findAllClients();
        }

        return ResponseEntity.ok(clients.stream().map(ClientDTO::new).collect(Collectors.toList()));

    }

    @RequestMapping(path = "/clients/{id}", method = RequestMethod.GET)
    public ResponseEntity<ClientDTO> findClient(@PathVariable Long id){
        return ResponseEntity.ok(new ClientDTO(clientService.find(id)));
    }

    @RequestMapping(path = "/clients/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteClient(@PathVariable Long id){
        clientService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
