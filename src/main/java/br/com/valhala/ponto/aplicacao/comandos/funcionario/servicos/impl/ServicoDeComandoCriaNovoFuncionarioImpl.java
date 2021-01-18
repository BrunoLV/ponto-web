package br.com.valhala.ponto.aplicacao.comandos.funcionario.servicos.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.valhala.ponto.aplicacao.comandos.funcionario.comandos.ComandoCriaNovoFuncionario;
import br.com.valhala.ponto.aplicacao.comandos.funcionario.servicos.ServicoDeComandoCriaNovoFuncionario;
import br.com.valhala.ponto.dominio.funcionario.modelo.Funcionario;
import br.com.valhala.ponto.dominio.funcionario.repositorio.RepositorioFuncionario;
import br.com.valhala.ponto.dominio.notificacao.NofiticacaoDominio;
import lombok.RequiredArgsConstructor;

@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
@RequiredArgsConstructor
public class ServicoDeComandoCriaNovoFuncionarioImpl implements ServicoDeComandoCriaNovoFuncionario {

    private final RepositorioFuncionario repositorioFuncionario;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void executa(ComandoCriaNovoFuncionario comando) {

        final Funcionario funcionario = Funcionario.criaNovo(
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

        final NofiticacaoDominio notificacaoDominio = funcionario.valida();
        if (!notificacaoDominio.temErros()) {
            repositorioFuncionario.salva(funcionario);
        } else {
            notificacaoDominio.getErros().stream().forEach(comando.getNotificacao()::adicionaErro);
        }

    }

}
