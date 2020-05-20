package br.com.duosdevelop.apibankddd.application.exceptions;

public class ParamException extends RuntimeException{
    public ParamException(String message) {
        super(message);
    }
}
