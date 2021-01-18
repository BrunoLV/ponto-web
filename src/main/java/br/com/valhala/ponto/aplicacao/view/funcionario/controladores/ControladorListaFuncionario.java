package br.com.valhala.ponto.aplicacao.view.funcionario.controladores;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.valhala.ponto.aplicacao.consultas.funcionario.ServicoDeConsultaFuncionario;
import br.com.valhala.ponto.aplicacao.dtos.funcionario.FiltroGridDTO;
import br.com.valhala.ponto.aplicacao.dtos.funcionario.FuncionarioDataGridDTO;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Component("controladorListaFuncionario")
@Scope("view")
@RequiredArgsConstructor
public class ControladorListaFuncionario implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final String NOVO_OUTCOME = "cadastro.xhtml?faces-redirect=true";
	
	@Autowired
    private final ServicoDeConsultaFuncionario funcionarioQueryService;

	@Getter
	@Setter
    private List<FuncionarioDataGridDTO> funcionarios = new ArrayList<>();
	
    @PostConstruct
    public void init() {
    	carregaFuncionarios();
    }

    private void carregaFuncionarios() {
    	this.funcionarios = funcionarioQueryService.obtemDadosDataGrid(new FiltroGridDTO());
    }
    
    public String novo() {
    	return NOVO_OUTCOME;
    }

}