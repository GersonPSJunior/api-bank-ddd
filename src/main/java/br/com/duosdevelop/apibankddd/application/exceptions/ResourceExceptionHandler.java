package br.com.duosdevelop.apibankddd.application.exceptions;

import br.com.duosdevelop.apibankddd.domain.services.exceptions.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> handleResourceNotFoundException(ObjectNotFoundException objectNotFoundException){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                StandardError.Builder
                        .newBuilder()
                        .timestamp(new Date().getTime())
                        .status(HttpStatus.NOT_FOUND.value())
                        .title("Resource not found")
                        .detail(objectNotFoundException.getMessage())
                        .developerMessage(objectNotFoundException.getClass().getName())
                        .build()
        );
    }

    @ExceptionHandler(ParamException.class)
    public ResponseEntity<StandardError> handleResourceParamException(ParamException paramException){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                StandardError.Builder
                        .newBuilder()
                        .timestamp(new Date().getTime())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .title("Resource parameters incorrect!")
                        .detail(paramException.getMessage())
                        .developerMessage(paramException.getClass().getName())
                        .build()
        );
    }

}
