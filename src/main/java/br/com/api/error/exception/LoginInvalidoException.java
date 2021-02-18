package br.com.api.error.exception;

import org.springframework.http.HttpStatus;

public class LoginInvalidoException extends DadosNaoEncontradosException{

    public LoginInvalidoException() {
        super("Usuario ou senha invalidos", HttpStatus.FORBIDDEN);
    }
}
