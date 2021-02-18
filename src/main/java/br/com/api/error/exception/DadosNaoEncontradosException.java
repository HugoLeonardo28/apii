package br.com.api.error.exception;

import org.springframework.http.HttpStatus;

public class DadosNaoEncontradosException extends ExceptionCustomizada{

    public DadosNaoEncontradosException(String mensagem, HttpStatus status) {
        super(mensagem, status);
    }
    public DadosNaoEncontradosException(String mensagem) {
        this(mensagem, HttpStatus.NOT_FOUND);
    }

}
