package br.com.valhala.ponto.aplicacao.dtos.funcionario;

import br.com.valhala.ponto.aplicacao.dtos.DTOTransmutavelParaModelo;
import br.com.valhala.ponto.aplicacao.dtos.funcionario.enums.EstadoDTO;
import br.com.valhala.ponto.aplicacao.dtos.funcionario.enums.TipoLogradouroDTO;
import br.com.valhala.ponto.dominio.funcionario.modelo.CEP;
import br.com.valhala.ponto.dominio.funcionario.modelo.Endereco;
import br.com.valhala.ponto.dominio.funcionario.modelo.enums.Estado;
import br.com.valhala.ponto.dominio.funcionario.modelo.enums.TipoLogradouro;
import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class EnderecoDTO implements DTOTransmutavelParaModelo<Endereco>, Serializable {

    private static final long serialVersionUID = 1L;

    private TipoLogradouroDTO tipoLogradouro;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String municipio;
    private EstadoDTO estado;
    private Long cep;

    public static EnderecoDTO aPartirDe(final Endereco endereco) {
        return new EnderecoDTO(TipoLogradouroDTO.valueOf(endereco.getTipoLogradouro().name()), endereco.getLogradouro(),
                endereco.getNumero(), endereco.getComplemento(), endereco.getBairro(), endereco.getMunicipio(),
                EstadoDTO.valueOf(endereco.getEstado().name()), endereco.getCep().desformatado());
    }

    @Override
    public Endereco converteParaModelo() {
        return new Endereco(TipoLogradouro.valueOf(this.tipoLogradouro.name()), this.logradouro, this.numero,
                this.complemento, this.bairro, this.municipio, CEP.aPartirDe(this.cep),
                Estado.valueOf(this.estado.name()));
    }

}
