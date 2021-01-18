package br.com.valhala.ponto.dominio.funcionario.modelo;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

import br.com.valhala.ponto.dominio.ObjetoDeValor;

import java.io.Serializable;

@Getter
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CarteiraTrabalho implements ObjetoDeValor, Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "carteira_trabalho_numero", nullable = false)
    @NotNull(message = "${carteiraTrabalho.numero.required}")
    @Max(value = 9999999, message = "${carteiraTrabalho.numero.max}")
    @EqualsAndHashCode.Include
    private Integer numero;

    @Column(name = "carteira_trabalho_digito", nullable = false)
    @NotNull(message = "${carteiraTrabalho.digito.required}")
    @Max(value = 999, message = "${carteiraTrabalho.digito.max}")
    @EqualsAndHashCode.Include
    private Integer digito;

    public static CarteiraTrabalho aPartirDe(final Long numero) {
        final String numeroEmString = String.format("%010d", numero);
        return new CarteiraTrabalho(Integer.parseInt(numeroEmString.substring(0, 7)),
                Integer.parseInt(numeroEmString.substring(7)));
    }

    public String formatado() {
        return String.format("%07d-%03d", this.numero, this.digito);
    }

    public Long desformatado() {
        if (numero == null || digito == null) {
            throw new IllegalStateException();
        }
        return Long.parseLong(String.format("%07d%03d", this.numero, this.digito));
    }

}
