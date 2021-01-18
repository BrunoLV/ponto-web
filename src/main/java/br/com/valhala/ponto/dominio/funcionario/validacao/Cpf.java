package br.com.valhala.ponto.dominio.funcionario.validacao;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = ValidadorCpf.class)
public @interface Cpf {

    String message() default "O CPF é inválido.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
