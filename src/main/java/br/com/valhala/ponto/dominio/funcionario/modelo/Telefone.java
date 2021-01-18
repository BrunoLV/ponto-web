package br.com.valhala.ponto.dominio.funcionario.modelo;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import br.com.valhala.ponto.dominio.ObjetoDeValor;
import br.com.valhala.ponto.dominio.funcionario.modelo.enums.TipoTelefone;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString(exclude = {"id", "funcionario"})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Telefone implements ObjetoDeValor, Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    @EqualsAndHashCode.Include
    private TipoTelefone tipo;

    @NotNull(message = "${telefone.ddd.required}")
    @EqualsAndHashCode.Include
    private Integer ddd;

    @NotNull(message = "${telefone.numero.required}")
    @EqualsAndHashCode.Include
    private Integer numero;

    @NotNull
    private Funcionario funcionario;

    public Telefone(@NotNull final TipoTelefone type, @NotNull(message = "${phone.ddd.required}") final Integer ddd,
                    @NotNull(message = "${phone.number.required}") final Integer number) {
        super();
        this.tipo = type;
        this.ddd = ddd;
        this.numero = number;
    }

    public Telefone(@NotNull final TipoTelefone type, @NotNull(message = "${phone.ddd.required}") final Integer ddd,
                    @NotNull(message = "${phone.number.required}") final Integer number, @NotNull final Funcionario employee) {
        super();
        this.tipo = type;
        this.ddd = ddd;
        this.numero = number;
        this.funcionario = employee;
    }

    public String numeroFormatado() {

        StringBuilder builder = new StringBuilder();

        builder.append(String.format("(%03d) ", this.ddd));

        final String numeroComoString = numero.toString();

        if (numeroComoString.length() == 9) {
            builder.append(String.format("%s-%s-%s", numeroComoString.substring(0, 1), numeroComoString.substring(1, 5),
                    numeroComoString.substring(5)));
        } else {
            builder.append(String.format("%s-%s", numeroComoString.substring(0, 4), numeroComoString.substring(4)));
        }

        return builder.toString();
    }

    void desvinculaFuncionario() {
        this.funcionario = null;
    }
}
