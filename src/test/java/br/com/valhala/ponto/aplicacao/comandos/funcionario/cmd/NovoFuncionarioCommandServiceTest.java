package br.com.valhala.ponto.aplicacao.comandos.funcionario.cmd;

import br.com.valhala.ponto.aplicacao.comandos.funcionario.comandos.ComandoCriaNovoFuncionario;
import br.com.valhala.ponto.aplicacao.comandos.funcionario.servicos.impl.ServicoDeComandoCriaNovoFuncionarioImpl;
import br.com.valhala.ponto.dominio.funcionario.modelo.*;
import br.com.valhala.ponto.dominio.funcionario.modelo.enums.Estado;
import br.com.valhala.ponto.dominio.funcionario.modelo.enums.TipoLogradouro;
import br.com.valhala.ponto.dominio.funcionario.modelo.enums.TipoTelefone;
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

import java.time.LocalDate;
import java.time.Month;
import java.util.Collections;
import java.util.HashSet;

import static br.com.valhala.ponto.infraestrutura.app.fabrica.FabricaUUID.getUUID;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(classes = {PontoJPAConfig.class, PontoWebConfig.class})
@DBRider
@DataSet(value = "datasets/funcionarios.yml", cleanBefore = true)
class NovoFuncionarioCommandServiceTest {

    @Autowired
    ServicoDeComandoCriaNovoFuncionarioImpl service;

    @Autowired
    RepositorioFuncionario repository;

    @DisplayName("Dado um comando com informações válidas, deve cadastrar um funcionário com sucesso.")
    @Test
    void cadastroNovoFuncionarioSucessoTest() {

        final var uuid = getUUID();

        final ComandoCriaNovoFuncionario comando = ComandoCriaNovoFuncionario
                .builder()
                .uuid(uuid)
                .nome(new Nome("Marina Rayssa", "Rezende"))
                .dataNascimento(LocalDate.of(1967, Month.FEBRUARY, 3))
                .cpf(new CPF(666554719, 15))
                .nomeMae(new Nome("Priscila Tatiane", "Rezende"))
                .nomePai(new Nome("Filipe Thomas Kaique", "Rezende"))
                .endereco(new Endereco(TipoLogradouro.RUA,
                        "Jaçanã",
                        "400",
                        null,
                        "Jardim Primavera",
                        "Boa Vista",
                        new CEP(69314, 182),
                        Estado.RR))
                .dataAdmissao(LocalDate.of(1990, Month.APRIL, 06))
                .carteiraTrabalho(new CarteiraTrabalho(6665547, 915))
                .telefones(new HashSet<>(Collections.singletonList(new Telefone(TipoTelefone.CELULAR, 95, 984067504))))
                .email("marinarayssarezende-98@uolinc.com")
                .build();

        service.executa(comando);
        assertThat(comando.getNotificacao().temErros(), is(false));

        final var funcionario = repository.buscaPorId(uuid);
        assertThat(funcionario, is(notNullValue()));

    }

    @DisplayName("Dado um comando com informações inválidas, deve ser disparada uma exceção de validação de modelo.")
    @Test
    void cadastroNovoFuncionarioErroValidacaoTest() {

        final var uuid = getUUID();

        final ComandoCriaNovoFuncionario comando = ComandoCriaNovoFuncionario
                .builder()
                .uuid(uuid)
                .nome(null)
                .dataNascimento(null)
                .cpf(null)
                .nomeMae(new Nome("Priscila Tatiane", "Rezende"))
                .nomePai(new Nome("Filipe Thomas Kaique", "Rezende"))
                .endereco(new Endereco(TipoLogradouro.RUA,
                        "Jaçanã",
                        "400",
                        null,
                        "Jardim Primavera",
                        "Boa Vista",
                        new CEP(69314, 182),
                        Estado.RR))
                .dataAdmissao(LocalDate.of(1990, Month.APRIL, 06))
                .carteiraTrabalho(new CarteiraTrabalho(6665547, 915))
                .telefones(new HashSet<>(Collections.singletonList(new Telefone(TipoTelefone.CELULAR, 95, 984067504))))
                .email("marinarayssarezende-98@uolinc.com")
                .build();

        service.executa(comando);

        assertThat(comando.getNotificacao().temErros(), is(true));
        assertThat(comando.getNotificacao().getErros().size(), equalTo(3));

    }

}
