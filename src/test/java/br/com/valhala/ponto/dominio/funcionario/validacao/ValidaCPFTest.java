package br.com.valhala.ponto.dominio.funcionario.validacao;

import br.com.valhala.ponto.dominio.funcionario.modelo.CPF;
import br.com.valhala.ponto.teste.conversor.ConversorStringParaInteger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class ValidaCPFTest {

    @DisplayName(value = "Dados CPF's válidos, a validação deve passar.")
    @ParameterizedTest
    @CsvFileSource(files = "src/test/resources/csv/cpf_valido.csv")
    void valicaoCPFValidoDevePassar(@ConvertWith(ConversorStringParaInteger.class) Integer numero, @ConvertWith(ConversorStringParaInteger.class) Integer digito) {
        CPF cpf = new CPF(numero, digito);
        assertThat(cpf.valida(), is(true));
    }

    @DisplayName(value = "Dados CPF's inválidos, a validação deve falhar.")
    @ParameterizedTest
    @CsvFileSource(files = "src/test/resources/csv/cpf_invalido.csv")
    void valicaoCPFValidoDeveFalhar(@ConvertWith(ConversorStringParaInteger.class) Integer numero, @ConvertWith(ConversorStringParaInteger.class) Integer digito) {
        CPF cpf = new CPF(numero, digito);
        assertThat(cpf.valida(), is(false));
    }

}
