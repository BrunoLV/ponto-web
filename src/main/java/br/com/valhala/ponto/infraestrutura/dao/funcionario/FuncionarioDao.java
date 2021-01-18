package br.com.valhala.ponto.infraestrutura.dao.funcionario;

import br.com.valhala.ponto.aplicacao.dtos.funcionario.FiltroGridDTO;
import br.com.valhala.ponto.aplicacao.dtos.funcionario.FuncionarioDataGridDTO;
import br.com.valhala.ponto.dominio.funcionario.modelo.CPF;

import java.util.Collection;

public interface FuncionarioDao {

    Collection<FuncionarioDataGridDTO> obtemDadosDataGrid(FiltroGridDTO filtro);

    Boolean verificaExistenciaFuncionarioComUuid(String uuid);

    Boolean verificaExistenciaFuncionarioComCpf(CPF cpf);

    Boolean verificaExistenciaFuncionarioComCpfEUuidDiferente(CPF cpf, String uuid);

}
