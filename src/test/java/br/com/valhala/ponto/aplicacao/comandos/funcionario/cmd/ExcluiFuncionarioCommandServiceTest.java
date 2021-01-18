package br.com.valhala.ponto.aplicacao.comandos.funcionario.cmd;

import br.com.valhala.ponto.aplicacao.comandos.funcionario.comandos.ComandoExcluiFuncionario;
import br.com.valhala.ponto.aplicacao.comandos.funcionario.servicos.impl.ServicoDeComandoExcluiFuncionarioImpl;
import br.com.valhala.ponto.dominio.excecoes.ModeloNaoEncontradoException;
import br.com.valhala.ponto.dominio.funcionario.repositorio.RepositorioFuncionario;
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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(classes = {PontoJPAConfig.class, PontoWebConfig.class})
@DBRider
@DataSet(value = "datasets/funcionarios.yml", cleanBefore = true)
class ExcluiFuncionarioCommandServiceTest {

    @Autowired
    ServicoDeComandoExcluiFuncionarioImpl service;

    @Autowired
    RepositorioFuncionario repositorioFuncionario;

    @DisplayName("Dado um UUID existente, sem nenhuma condição que não permita exclusão, deve conseguir excluir o funcionario com sucesso")
    @Test
    void excluiFuncionarioComSucessoTest() {
        final var uuid = "7bc7afec067811ebadc10242ac120002";

        final var comando = new ComandoExcluiFuncionario(uuid);
        service.executa(comando);
        assertThat(comando.getNotificacao().temErros(), is(false));
        assertThrows(ModeloNaoEncontradoException.class, () -> repositorioFuncionario.buscaPorId(uuid));
    }

    @DisplayName("Dado um UUID que não existe, deve lançar uma exceção de registro não existente")
    @Test
    void excluiFuncionarioFalhaParaFuncionarioNaoExistenteTest() {
        final var uuidNaoExistente = "7bc7afec067811ebadc10242ac140004";
        final var comando = new ComandoExcluiFuncionario(uuidNaoExistente);
        assertThrows(ModeloNaoEncontradoException.class, () -> service.executa(comando));
    }

}
