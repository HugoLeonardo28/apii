package br.com.api.error.exception;

public class ClienteNaoEncontratoException extends DadosNaoEncontradosException{

    public ClienteNaoEncontratoException(Long clienteId) {
        super(String.format("Cliente não existe ou está inativo id - {%s}", clienteId));
    }

    public ClienteNaoEncontratoException() {
        super(String.format("Não foram encontrados clientes para listagem"));
    }
}
