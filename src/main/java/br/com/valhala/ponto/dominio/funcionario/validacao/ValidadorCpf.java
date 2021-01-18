package br.com.valhala.ponto.dominio.funcionario.validacao;

import br.com.valhala.ponto.dominio.funcionario.modelo.CPF;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidadorCpf implements ConstraintValidator<Cpf, CPF> {

    @Override
    public void initialize(Cpf constraintAnnotation) {
    }

    @Override
    public boolean isValid(CPF cpf, ConstraintValidatorContext context) {
        if (cpf == null) {
            return false;
        }
        return cpf.valida();
    }

}
