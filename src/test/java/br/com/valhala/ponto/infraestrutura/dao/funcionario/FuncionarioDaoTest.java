package br.com.valhala.ponto.infraestrutura.dao.funcionario;

import br.com.valhala.ponto.aplicacao.dtos.funcionario.FiltroGridDTO;
import br.com.valhala.ponto.infraestrutura.app.configuracao.PontoJPAConfig;
import br.com.valhala.ponto.infraestrutura.app.configuracao.PontoWebConfig;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.spring.api.DBRider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(classes = {PontoJPAConfig.class, PontoWebConfig.class})
@DBRider
@DataSet(value = "datasets/funcionarios.yml", cleanBefore = true)
class FuncionarioDaoTest {

    @Autowired
    private FuncionarioDao dao;

    @DisplayName("Dado que existam dados, deve retornar a projeção de Data Grid como resultado da consulta")
    @Test
    void deveRetornarProjecaoDataGridTest() {

    	FiltroGridDTO filtro = new FiltroGridDTO();
    	
        final var funcionarios = dao.obtemDadosDataGrid(filtro);

        assertThat(funcionarios, not(nullValue()));
        assertThat(funcionarios.size(), equalTo(2));
    }

}
