package br.com.api.error.exception;

public class ConstraintVioladaException extends DadosNaoEncontradosException{

    public ConstraintVioladaException() {
        super("Constraint de banco de dados violada");
    }
}
