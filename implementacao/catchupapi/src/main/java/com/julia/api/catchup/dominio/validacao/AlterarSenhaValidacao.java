package com.julia.api.catchup.dominio.validacao;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.julia.api.catchup.dominio.dto.FuncionarioEditarSenhaDto;
import com.julia.api.catchup.dominio.dto.FuncionarioNovoDto;
import com.julia.api.catchup.excessao.dto.FieldMessage;

public class AlterarSenhaValidacao implements ConstraintValidator<Senha, FuncionarioEditarSenhaDto> {

	@Override
	public boolean isValid(FuncionarioEditarSenhaDto value, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();		
		if(value.getSenhaConfirmacao().trim().isEmpty()) {
			list.add(new FieldMessage("senha"," senha de confirmação vazia"));
		}
		if(value.getSenha().trim().isEmpty()) {
			list.add(new FieldMessage("senha"," senha vazia"));
		}
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
