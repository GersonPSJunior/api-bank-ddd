package br.com.duosdevelop.apibankddd.application.controller;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

@RestController
public class ApplicationController {

    @RequestMapping(method = RequestMethod.GET, value = "/")
    public ResponseEntity<String> home(HttpMethod method, WebRequest request) {
        final String htmlContent = "<!DOCTYPE html><html><body>"
                + "<h1>Bem vindo ao Spring DDD Bank REST Webservice.</h1>"
                + "<p style='font-size: large;'>Click aqui para acessar <a href='swagger-ui.html'>documentação da API REST</a> distribuído por <a href='https://swagger.io/'>Swagger</a></p>"
                + "</body></html>";
        final ResponseEntity<String> responseEntity = new ResponseEntity<>(htmlContent, HttpStatus.OK);
        return responseEntity;
    }
}
