package br.com.valhala.ponto.aplicacao.consultas.funcionario;

import java.util.List;

import br.com.valhala.ponto.aplicacao.dtos.funcionario.FiltroGridDTO;
import br.com.valhala.ponto.aplicacao.dtos.funcionario.FuncionarioDataGridDTO;

public interface ServicoDeConsultaFuncionario {
	
	List<FuncionarioDataGridDTO> obtemDadosDataGrid(FiltroGridDTO filtro);

}
