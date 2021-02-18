package br.com.api.service.validacao;

import br.com.api.error.exception.ParametrosInvalidosException;
import org.apache.commons.lang3.StringUtils;

public class ParametrosRequestValidador {

    static final String MENSAGEM = "Parametro informado deve ser diferente de null e maior que 0 , parametro - {%s}";

    public static void validar(Object param) {
        if (param == null) {
            throw new ParametrosInvalidosException(String.format(MENSAGEM, "null"));
        } else {
            validarString(param);
        }
    }

    private static void validarString(final Object param) {
        if (param instanceof String) {

            final String value = (String) param;

            if (StringUtils.isEmpty(value)) {
                throw new ParametrosInvalidosException(String.format(MENSAGEM, value));
            }
        } else {
            validarLong(param);
        }
    }

    private static void validarLong(Object param) {
        if (param instanceof Long) {
            final Long value = (Long) param;

            if (value <= 0) {
                throw new ParametrosInvalidosException(String.format(MENSAGEM, value));
            }
        }
    }

}
