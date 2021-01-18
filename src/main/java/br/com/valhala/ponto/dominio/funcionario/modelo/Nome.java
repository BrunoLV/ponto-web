package br.com.valhala.ponto.dominio.funcionario.modelo;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import br.com.valhala.ponto.dominio.ObjetoDeValor;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Nome implements ObjetoDeValor, Serializable {

    private static final long serialVersionUID = 1L;

    @NotBlank(message = "${nome.nome.required}")
    @EqualsAndHashCode.Include
    private String nome;

    @NotBlank(message = "${nome.sobrenome.required}")
    @EqualsAndHashCode.Include
    private String sobrenome;

    public String completo() {
        return String.format("%s %s", this.nome, this.sobrenome);
    }

}