package br.com.api.error.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class ExceptionCustomizada extends RuntimeException{

    private String mensagem;
    private HttpStatus httpStatus;

    public ExceptionCustomizada(String mensagem) {
        super(mensagem);
        this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    public ExceptionCustomizada() {
        super("Ocorreu um erro inesperado");
        this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
