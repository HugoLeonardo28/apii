package br.com.api.error.exception;

public class ClienteSemPetException extends DadosNaoEncontradosException{

    public ClienteSemPetException(Long clienteId) {
        super(String.format("Não foi encontrado pet para o clienteId informado - {%s}", clienteId));
    }
}
