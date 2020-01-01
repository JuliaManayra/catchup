package com.julia.api.catchup.dominio.validacao;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;


@Constraint(validatedBy = SenhaValidacao.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface Senha {

	String message() default "Senhas diferentes";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
