package br.com.valhala.ponto.dominio.funcionario.modelo;

import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

import br.com.valhala.ponto.dominio.ObjetoDeValor;
import br.com.valhala.ponto.dominio.funcionario.validacao.Cpf;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Cpf
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CPF implements ObjetoDeValor, Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "${cpf.numero.required}")
    @Max(value = 999999999, message = "${carteiraTrabalho.numero.max}")
    @EqualsAndHashCode.Include
    private Integer numero;

    @NotNull(message = "${cpf.digito.required}")
    @Max(value = 99, message = "${carteiraTrabalho.digito.max}")
    @EqualsAndHashCode.Include
    private Integer digito;

    public static CPF aPartirDe(final Long numeroCompleto) {

        final String numeroComoString = String.format("%011d", numeroCompleto);

        final Integer numero = Integer.parseInt(numeroComoString.substring(0, 9));
        final Integer digito = Integer.parseInt(numeroComoString.substring(9));

        final CPF cpf = new CPF(numero, digito);

        if (!cpf.valida()) {
            throw new IllegalStateException("CPF é inválido");
        }

        return cpf;
    }

    public String formatado() {

        final String numeroComoString = String.format("%09d", this.numero);
        final String digitoComoString = String.format("%02d", this.digito);

        return String.format("%s.%s.%s-%s", numeroComoString.substring(0, 3), numeroComoString.substring(3, 6),
                numeroComoString.substring(6), digitoComoString);
    }

    public int calculaDigito() {

        final String numeroComoString = String.format("%09d", this.numero);
        final int primeiroDigito = calculaDigito(numeroComoString, 10);
        final String numeroComPrimeiroDigito = numeroComoString.concat(String.valueOf(primeiroDigito));
        final int segundoDigito = calculaDigito(numeroComPrimeiroDigito, 11);

        return Integer.parseInt(String.valueOf(primeiroDigito).concat(String.valueOf(segundoDigito)));

    }

    private int calculaDigito(final String numero, final int operadorInicial) {

        int acumuladorOperacao = 0;

        int operador = operadorInicial;

        for (int i = 0; i < numero.length(); i++) {

            int n;
            if (i == numero.length() - 1) {
                n = Integer.parseInt(numero.substring(i));
            } else {
                n = Integer.parseInt(numero.substring(i, i + 1));
            }

            acumuladorOperacao = acumuladorOperacao + (n * operador);
            operador--;

        }

        final int digito = (acumuladorOperacao * 10) % 11;

        return digito;
    }

    public boolean valida() {
        if (this.numero == null || this.digito == null) {
            return false;
        }
        return this.getDigito().equals(this.calculaDigito());
    }

    public Long desformatado() {

        if (this.numero == null || this.digito == null) {
            throw new IllegalStateException();
        }

        final String numeroComoString = String.format("%09d", this.numero);
        final String digitoComoString = String.format("%02d", this.digito);

        return Long.parseLong(numeroComoString.concat(digitoComoString));
    }

}
