package br.com.api.error.exception;

public class UsuarioEncontratoException extends DadosNaoEncontradosException{

    public UsuarioEncontratoException(String email) {
        super(String.format("Não foi encontrado usuario para o email informado - {%s}", email));
    }

    public UsuarioEncontratoException(Long usuarioId) {
        super(String.format("Não foi encontrado usuario para o id informado - {%s}", usuarioId));
    }
}
