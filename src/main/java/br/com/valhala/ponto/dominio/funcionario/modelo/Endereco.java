package br.com.valhala.ponto.dominio.funcionario.modelo;

import java.io.Serializable;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.valhala.ponto.dominio.ObjetoDeValor;
import br.com.valhala.ponto.dominio.funcionario.modelo.enums.Estado;
import br.com.valhala.ponto.dominio.funcionario.modelo.enums.TipoLogradouro;
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
public class Endereco implements ObjetoDeValor, Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "${endereco.tipoLogradouro.required}")
    @Enumerated(EnumType.STRING)
    @EqualsAndHashCode.Include
    private TipoLogradouro tipoLogradouro;

    @NotBlank(message = "${endereco.logradouro.required}")
    @EqualsAndHashCode.Include
    private String logradouro;

    @NotBlank(message = "${endereco.numero.required}")
    @EqualsAndHashCode.Include
    private String numero;

    @EqualsAndHashCode.Include
    private String complemento;

    @NotBlank(message = "${endereco.bairro.required}")
    @EqualsAndHashCode.Include
    private String bairro;

    @NotBlank(message = "${endereco.municipio.required}")
    @EqualsAndHashCode.Include
    private String municipio;

    @NotNull(message = "${endereco.cep.required}")
    @Valid
    @EqualsAndHashCode.Include
    private CEP cep;

    @NotNull(message = "${endereco.estado.required}")
    @EqualsAndHashCode.Include
    private Estado estado;

}