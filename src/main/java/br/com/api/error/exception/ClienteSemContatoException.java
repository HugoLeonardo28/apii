package br.com.api.error.exception;

public class ClienteSemContatoException extends DadosNaoEncontradosException{

    public ClienteSemContatoException(Long clienteId) {
        super(String.format("Não foram encontrados contatos para o clienteId informado - {%s}", clienteId));
    }
}
