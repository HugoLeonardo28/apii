package br.com.api.error.exception;

import org.springframework.http.HttpStatus;

public class ParametrosInvalidosException extends ExceptionCustomizada{

    public ParametrosInvalidosException(String mensagem) {
        super(mensagem, HttpStatus.BAD_REQUEST);
    }

}
