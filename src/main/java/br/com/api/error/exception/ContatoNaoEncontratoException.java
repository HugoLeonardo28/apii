package br.com.api.error.exception;

public class ContatoNaoEncontratoException extends DadosNaoEncontradosException{

    public ContatoNaoEncontratoException(Long contatoId) {
        super(String.format("Contato com id {%s} não encontrado", contatoId));
    }

    public ContatoNaoEncontratoException() {
        super("Não foram encontrados contatos para listagem");
    }
}
