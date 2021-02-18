package br.com.api.error.exception;

public class EnderecoNaoEncontratoException extends DadosNaoEncontradosException{

    public EnderecoNaoEncontratoException(Long enderecoId) {
        super(String.format("Endereco com id {%s} não encontrado", enderecoId));
    }

    public EnderecoNaoEncontratoException() {
        super("Não foram encontrados enderecos para listagem");
    }
}
