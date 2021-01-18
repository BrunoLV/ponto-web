package br.com.valhala.ponto.aplicacao.dtos.funcionario;

import br.com.valhala.ponto.aplicacao.dtos.DTOTransmutavelParaModelo;
import br.com.valhala.ponto.aplicacao.dtos.funcionario.enums.TipoTelefoneDTO;
import br.com.valhala.ponto.dominio.funcionario.modelo.Telefone;
import br.com.valhala.ponto.dominio.funcionario.modelo.enums.TipoTelefone;
import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class TelefoneDTO implements DTOTransmutavelParaModelo<Telefone>, Serializable {

    private static final long serialVersionUID = 1L;

    private TipoTelefoneDTO tipo;
    private Integer ddd;
    private Integer numero;

    public static TelefoneDTO aPartirDe(final Telefone telefone) {
        return new TelefoneDTO(TipoTelefoneDTO.valueOf(telefone.getTipo().name()), telefone.getDdd(),
                telefone.getNumero());
    }

    @Override
    public Telefone converteParaModelo() {
        return new Telefone(TipoTelefone.valueOf(this.tipo.name()), this.ddd, this.numero);
    }

}
