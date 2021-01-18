package br.com.valhala.ponto.aplicacao.dtos.funcionario;

import br.com.valhala.ponto.aplicacao.dtos.DTOTransmutavelParaModelo;
import br.com.valhala.ponto.dominio.funcionario.modelo.Nome;
import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class NomeDTO implements DTOTransmutavelParaModelo<Nome>, Serializable, Comparable<NomeDTO> {

    private static final long serialVersionUID = 1L;

    private String nome;
    private String sobrenome;

    public static NomeDTO aPartirDe(Nome nome) {
        return new NomeDTO(nome.getNome(), nome.getSobrenome());
    }

    @Override
    public Nome converteParaModelo() {
        return new Nome(this.nome, this.sobrenome);
    }

    @Override
    public int compareTo(NomeDTO o) {
        int result = this.nome.compareTo(o.getNome()) + this.sobrenome.compareTo(o.getSobrenome());
        return result;
    }

    public String completo() {
        return String.format("%s $s", this.nome, this.sobrenome);
    }

}
