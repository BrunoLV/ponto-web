package br.com.valhala.ponto.aplicacao.comandos.funcionario.servicos.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.valhala.ponto.aplicacao.comandos.funcionario.comandos.ComandoExcluiFuncionario;
import br.com.valhala.ponto.aplicacao.comandos.funcionario.servicos.ServicoDeComandoExcluiFuncionario;
import br.com.valhala.ponto.dominio.funcionario.repositorio.RepositorioFuncionario;

@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ServicoDeComandoExcluiFuncionarioImpl implements ServicoDeComandoExcluiFuncionario {

    private RepositorioFuncionario repositorioFuncionario;

    public ServicoDeComandoExcluiFuncionarioImpl(final RepositorioFuncionario repositorioFuncionario) {
        this.repositorioFuncionario = repositorioFuncionario;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void executa(ComandoExcluiFuncionario comando) {
        repositorioFuncionario.exclui(comando.getUuid());
    }
}
