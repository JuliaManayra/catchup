package com.julia.api.catchup.dominio.validacao;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.julia.api.catchup.dominio.dto.FuncionarioNovo;
import com.julia.api.catchup.excessao.FieldMessage;

public class SenhaValidacao implements ConstraintValidator<Senha, FuncionarioNovo> {

	@Override
	public boolean isValid(FuncionarioNovo value, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();		
		if(!value.getSenha().equals(value.getSenhaConfirmacao())) {
			list.add(new FieldMessage("senha","Senha e senha de confirmação não conferem!"));
		}
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		
		return list.isEmpty();
	}

}
