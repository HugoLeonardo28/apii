package br.com.api.dto.validacao;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CepValidador implements ConstraintValidator<Cep, String> {

    @Override
    public boolean isValid(String cep, ConstraintValidatorContext constraintValidatorContext) {

        if (cep.length() == 9)
        {
            String[] cepCortado = cep.split("-");
            return cepCortado[0].length() == 5 && cepCortado[1].length() == 3;
        }

        return false;
    }
}
