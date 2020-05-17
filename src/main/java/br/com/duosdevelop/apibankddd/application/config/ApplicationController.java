package br.com.duosdevelop.apibankddd.application.config;

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
import java.time.format.DateTimeFormatter;

@RestController
public class ApplicationController {

    private BankService service;

    @Autowired
    public ApplicationController(BankService service) {
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/")
    public ResponseEntity<String> home(HttpMethod method, WebRequest request) {
        final String htmlContent = "<!DOCTYPE html><html><body>"
                + "<h1>Welcome to the Spring DDD Bank REST Webservice.</h1>"
                + "<p style='font-size: large;'>Click here for <a href='swagger-ui.html'>REST API documentation</a> powered by <a href='https://swagger.io/'>Swagger</a></p>"
                + "</body></html>";
        final ResponseEntity<String> responseEntity = new ResponseEntity<>(htmlContent, HttpStatus.OK);
        return responseEntity;
    }

    @RequestMapping(value = "/bank/client", method = RequestMethod.POST)
    public ResponseEntity<ClientDTO> insertClient(@RequestBody ClientDTO clientDTO,
                                                  @ApiParam(hidden = true) final HttpMethod method,
                                                  final WebRequest request){
        if (clientDTO.id != null) {
            throw new IllegalArgumentException("Id invalído!");
        }
        final LocalDate birthLocalDate = LocalDate.parse(clientDTO.birthDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        final Client client = service.create(clientDTO.username, birthLocalDate);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ClientDTO(client));
    }
}
