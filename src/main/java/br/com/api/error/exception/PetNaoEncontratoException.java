package br.com.api.error.exception;

public class PetNaoEncontratoException extends DadosNaoEncontradosException{

    public PetNaoEncontratoException(Long petId) {
        super(String.format("Pet não existe ou não esta ativo id - {%s}", petId));
    }

    public PetNaoEncontratoException() {
        super("Não foi encontrado pet para listagem");
    }
}
