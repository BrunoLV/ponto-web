package br.com.valhala.ponto.dominio.funcionario.validacao;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.time.LocalDate;
import java.time.Month;
import java.util.Collections;
import java.util.HashSet;

import javax.validation.ConstraintValidatorContext;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.spring.api.DBRider;

import br.com.valhala.ponto.dominio.funcionario.modelo.CEP;
import br.com.valhala.ponto.dominio.funcionario.modelo.CPF;
import br.com.valhala.ponto.dominio.funcionario.modelo.CarteiraTrabalho;
import br.com.valhala.ponto.dominio.funcionario.modelo.Endereco;
import br.com.valhala.ponto.dominio.funcionario.modelo.Funcionario;
import br.com.valhala.ponto.dominio.funcionario.modelo.Nome;
import br.com.valhala.ponto.dominio.funcionario.modelo.Telefone;
import br.com.valhala.ponto.dominio.funcionario.modelo.enums.Estado;
import br.com.valhala.ponto.dominio.funcionario.modelo.enums.TipoLogradouro;
import br.com.valhala.ponto.dominio.funcionario.modelo.enums.TipoTelefone;
import br.com.valhala.ponto.infraestrutura.app.configuracao.PontoJPAConfig;
import br.com.valhala.ponto.infraestrutura.app.configuracao.PontoWebConfig;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(classes = {PontoJPAConfig.class, PontoWebConfig.class})
@DBRider
@DataSet(value = "datasets/funcionarios.yml", cleanBefore = true)
class ValidaRegraCPFUnicoTest {

    @Autowired
    ValidadorCpfUnico validadorRegraCpfUnico;

    ConstraintValidatorContext validationContext;

    @BeforeEach
    void setup() {
        validationContext = Mockito.mock(ConstraintValidatorContext.class);
    }

    @DisplayName("Dado que já existe um funcionário cadastrado com o CPF, a validação resultar em inválido.")
    @Test
    void validacaoNaInclusaoDeveRetornarInvalido() {

        final Funcionario funcionario = Funcionario.criaNovo(null,
                new Nome("Jennifer Sueli", "Pires"),
                LocalDate.of(1948, Month.JULY, 25),
                new CPF(311864103, 70),
                new Nome("Marlene Louise", "Pires"),
                new Nome("Luan Calebe Henrique", "Pires"),
                new Endereco(TipoLogradouro.RUA,
                        "Jaçanã",
                        "400",
                        null,
                        "Jardim Primavera",
                        "Boa Vista",
                        new CEP(69314, 182),
                        Estado.RR),
                LocalDate.of(1990, Month.APRIL, 06),
                new CarteiraTrabalho(4887716, 352),
                new HashSet<>(Collections.singletonList(new Telefone(TipoTelefone.CELULAR, 95, 984067504))),
                "jennifersuelipires..jennifersuelipires@teravida.com.br");

        final boolean isValido = validadorRegraCpfUnico.isValid(funcionario, validationContext);

        assertThat(isValido, is(false));

    }

    @DisplayName("Dado que não existe cadastrado com o CPF, a validação deve resultar em válido.")
    @Test
    void validacaoNaInclusaoDeveFalhar() {

        final Funcionario funcionario = Funcionario.criaNovo(null,
                new Nome("Jennifer Sueli", "Pires"),
                LocalDate.of(1948, Month.JULY, 25),
                new CPF(488771643, 52),
                new Nome("Marlene Louise", "Pires"),
                new Nome("Luan Calebe Henrique", "Pires"),
                new Endereco(TipoLogradouro.RUA,
                        "Jaçanã",
                        "400",
                        null,
                        "Jardim Primavera",
                        "Boa Vista",
                        new CEP(69314, 182),
                        Estado.RR),
                LocalDate.of(1990, Month.APRIL, 06),
                new CarteiraTrabalho(4887716, 352),
                new HashSet<>(Collections.singletonList(new Telefone(TipoTelefone.CELULAR, 95, 984067504))),
                "jennifersuelipires..jennifersuelipires@teravida.com.br");

        final boolean isValido = validadorRegraCpfUnico.isValid(funcionario, validationContext);

        assertThat(isValido, is(true));

    }

    @DisplayName("Dado que já existe um funcionário diferente cadastrado com o CPF, a validação deve falhar na edição.")
    @Test
    void validacaoNaEdicaoDeveFalhar() {

        final Funcionario funcionario = Funcionario.criaNovo("7bc7afec067811ebadc10242ac130003",
                new Nome("Jennifer Sueli", "Pires"),
                LocalDate.of(1948, Month.JULY, 25),
                new CPF(311864103, 70),
                new Nome("Marlene Louise", "Pires"),
                new Nome("Luan Calebe Henrique", "Pires"),
                new Endereco(TipoLogradouro.RUA,
                        "Jaçanã",
                        "400",
                        null,
                        "Jardim Primavera",
                        "Boa Vista",
                        new CEP(69314, 182),
                        Estado.RR),
                LocalDate.of(1990, Month.APRIL, 06),
                new CarteiraTrabalho(4887716, 352),
                new HashSet<>(Collections.singletonList(new Telefone(TipoTelefone.CELULAR, 95, 984067504))),
                "jennifersuelipires..jennifersuelipires@teravida.com.br");

        final boolean isValido = validadorRegraCpfUnico.isValid(funcionario, validationContext);

        assertThat(isValido, is(false));

    }

}
