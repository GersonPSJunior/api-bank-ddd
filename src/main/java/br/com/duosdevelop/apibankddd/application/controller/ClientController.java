package br.com.duosdevelop.apibankddd.application.controller;

import br.com.duosdevelop.apibankddd.application.Util;
import br.com.duosdevelop.apibankddd.application.dto.ClientDTO;
import br.com.duosdevelop.apibankddd.domain.BankService;
import br.com.duosdevelop.apibankddd.domain.Client;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDate;

@RestController
@RequestMapping("/client")
public class ClientController {

    private BankService service;

    @Autowired
    public ClientController(BankService service) {
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<ClientDTO> insertClient(@RequestBody ClientDTO clientDTO,
                                                  @ApiParam(hidden = true) final HttpMethod method,
                                                  final WebRequest request){
        if (clientDTO.id != null) {
            throw new IllegalArgumentException("Id invalído!");
        }
        final LocalDate birthLocalDate = LocalDate.parse(clientDTO.birthDate, Util.MEDIUM_DATE_FORMATTER);
        final Client client = service.create(clientDTO.username, birthLocalDate);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ClientDTO(client));
    }}
