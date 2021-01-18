package br.com.valhala.ponto.dominio.funcionario.validacao;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.valhala.ponto.dominio.funcionario.modelo.Funcionario;
import br.com.valhala.ponto.infraestrutura.dao.funcionario.FuncionarioDao;

@Component
public class ValidadorCpfUnico implements ConstraintValidator<CpfUnico, Funcionario> {

	@Autowired
	private FuncionarioDao dao;

	@Override
	public void initialize(CpfUnico constraintAnnotation) {
	}

	@Override
	public boolean isValid(Funcionario funcionario, ConstraintValidatorContext context) {

		boolean valido = true;
		
		if (funcionario.getUuid() != null) {
			final Boolean existeFuncionarioComUuid = dao.verificaExistenciaFuncionarioComUuid(funcionario.getUuid());
			if (existeFuncionarioComUuid) {
				final Boolean existeOutroFuncionarioComCpf = dao.verificaExistenciaFuncionarioComCpfEUuidDiferente(funcionario.getCpf(), funcionario.getUuid());
				valido = !existeOutroFuncionarioComCpf;
			} else {
				final Boolean existeFuncionarioComCpf = dao.verificaExistenciaFuncionarioComCpf(funcionario.getCpf());
				valido = !existeFuncionarioComCpf;
			}
		} else {
			final Boolean existeFuncionarioComCpf = dao.verificaExistenciaFuncionarioComCpf(funcionario.getCpf());
			valido = !existeFuncionarioComCpf;
		}
		
		return valido;
	}

}
