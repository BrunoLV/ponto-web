package br.com.valhala.ponto.aplicacao.consultas.funcionario.impl;

import br.com.valhala.ponto.aplicacao.consultas.funcionario.ServicoDeConsultaFuncionario;
import br.com.valhala.ponto.aplicacao.dtos.funcionario.FiltroGridDTO;
import br.com.valhala.ponto.aplicacao.dtos.funcionario.FuncionarioDataGridDTO;
import br.com.valhala.ponto.infraestrutura.dao.funcionario.FuncionarioDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ServicoDeConsultaFuncionarioImpĺ implements ServicoDeConsultaFuncionario {

    private final FuncionarioDao funcionarioDao;

    @Override
    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public List<FuncionarioDataGridDTO> obtemDadosDataGrid(FiltroGridDTO filtro) {
        return new ArrayList<FuncionarioDataGridDTO>(funcionarioDao.obtemDadosDataGrid(filtro));
    }

}
