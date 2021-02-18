package br.com.api.error.exception;

public class ClienteSemEnderecoException extends DadosNaoEncontradosException{

    public ClienteSemEnderecoException(Long clienteId) {
        super(String.format("Não foram encontrados endereços para o clienteId informado - {%s}", clienteId));
    }
}
