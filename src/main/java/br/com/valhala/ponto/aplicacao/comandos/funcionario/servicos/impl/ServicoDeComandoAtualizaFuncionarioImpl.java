package br.com.valhala.ponto.aplicacao.comandos.funcionario.servicos.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.valhala.ponto.aplicacao.comandos.funcionario.comandos.ComandoAtualizaFuncionario;
import br.com.valhala.ponto.aplicacao.comandos.funcionario.servicos.ServicoDeComandoAtualizaFuncionario;
import br.com.valhala.ponto.dominio.funcionario.modelo.Funcionario;
import br.com.valhala.ponto.dominio.funcionario.repositorio.RepositorioFuncionario;
import br.com.valhala.ponto.dominio.notificacao.NofiticacaoDominio;

@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ServicoDeComandoAtualizaFuncionarioImpl implements ServicoDeComandoAtualizaFuncionario {

    private RepositorioFuncionario repositorioFuncionario;

    public ServicoDeComandoAtualizaFuncionarioImpl(final RepositorioFuncionario repositorioFuncionario) {
        this.repositorioFuncionario = repositorioFuncionario;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void executa(ComandoAtualizaFuncionario comando) {

        Funcionario funcionario = repositorioFuncionario.buscaPorId(comando.getUuid());

        Funcionario dadosAtualizacao = new Funcionario(
                comando.getUuid(),
                comando.getNome(),
                comando.getDataNascimento(),
                comando.getCpf(),
                comando.getNomeMae(),
                comando.getNomePai(),
                comando.getEndereco(),
                comando.getDataAdmissao(),
                comando.getCarteiraTrabalho(),
                comando.getTelefones(),
                comando.getEmail());

        Funcionario novoEstado = funcionario.atualiza(dadosAtualizacao);

        final NofiticacaoDominio notificacaoDominio = novoEstado.valida();
        if (!notificacaoDominio.temErros()) {
            repositorioFuncionario.salva(novoEstado);
        } else {
            notificacaoDominio.getErros().stream().forEach(comando.getNotificacao()::adicionaErro);
        }

    }

}
