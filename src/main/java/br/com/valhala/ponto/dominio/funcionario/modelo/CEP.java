package br.com.valhala.ponto.dominio.funcionario.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

import br.com.valhala.ponto.dominio.ObjetoDeValor;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CEP implements ObjetoDeValor, Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "cep_prefixo", nullable = false)
    @NotNull(message = "${cep.prefixo.required}")
    @Max(value = 99999, message = "${cep.prefixo.max}")
    @EqualsAndHashCode.Include
    private Integer prefixo;

    @Column(name = "cep_sufixo", nullable = false)
    @NotNull(message = "${cep.sufixo.required}")
    @Max(value = 999, message = "${cep.sufixo.max}")
    @EqualsAndHashCode.Include
    private Integer sufixo;

    public static CEP aPartirDe(final Long numeroCompleto) {

        final String numeroComoString = String.format("%08d", numeroCompleto);

        final Integer prefixo = Integer.parseInt(numeroComoString.substring(0, 5));
        final Integer sufixo = Integer.parseInt(numeroComoString.substring(5));

        return new CEP(prefixo, sufixo);
    }

    public String formatado() {
        final String prefixoComoString = String.format("%05d", this.prefixo);
        final String sufixoComoString = String.format("%03d", this.sufixo);

        return String.format("%s-%s", prefixoComoString, sufixoComoString);
    }

    public Long desformatado() {

        if (this.prefixo == null || this.sufixo == null) {
            throw new IllegalStateException();
        }

        final String prefixoComoString = String.format("%05d", this.sufixo);
        final String sufixoComoString = String.format("%03d", this.prefixo);

        return Long.parseLong(prefixoComoString.concat(sufixoComoString));
    }

}
