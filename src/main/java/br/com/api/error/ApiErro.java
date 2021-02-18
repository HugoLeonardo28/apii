package br.com.api.error;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;


@Getter
@Builder
public class ApiErro {

    @JsonIgnore
    private HttpStatus statusCode;

    private String status;

    private String titulo;
    private String detalhe;

    private LocalDateTime data;


    private List<ErroDeConstraint> camposInvalidos;

    public String getStatus() {

        final Integer codigo = statusCode.value();
        final String detalhe = statusCode.getReasonPhrase();

        return codigo + " - " + detalhe;
    }

    public LocalDateTime getData() {
        if(data == null) {
            return LocalDateTime.now();
        } else {
            return data;
        }
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public List<ErroDeConstraint> getCamposInvalidos() {

        if(camposInvalidos != null && camposInvalidos.size() == 0) {
            return null;
        }

        return camposInvalidos;
    }

    @Getter
    @Builder
    public static class ErroDeConstraint {

        private String campo;
        private String detalhe;
    }
}
