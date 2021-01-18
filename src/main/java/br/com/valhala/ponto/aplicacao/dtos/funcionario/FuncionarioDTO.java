package br.com.valhala.ponto.aplicacao.dtos.funcionario;

import br.com.valhala.ponto.aplicacao.dtos.DTOTransmutavelParaModelo;
import br.com.valhala.ponto.dominio.funcionario.modelo.CPF;
import br.com.valhala.ponto.dominio.funcionario.modelo.CarteiraTrabalho;
import br.com.valhala.ponto.dominio.funcionario.modelo.Funcionario;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public final class FuncionarioDTO implements DTOTransmutavelParaModelo<Funcionario>, Serializable, Comparable<FuncionarioDTO> {

    private static final long serialVersionUID = 1L;

    private String uuid;

    private NomeDTO nome = new NomeDTO();
    private NomeDTO nomeMae = new NomeDTO();
    private NomeDTO nomePai = new NomeDTO();

    private LocalDate dataNascimento;

    private Long cpf;

    private EnderecoDTO endereco = new EnderecoDTO();

    private LocalDate dataAdmissao;

    private Long carteiraTrabalho;

    private Set<TelefoneDTO> telefones = new HashSet<>();

    private String email;

    public static FuncionarioDTO aPartirDe(final Funcionario funcionario) {

        return new FuncionarioDTO(funcionario.getUuid(), NomeDTO.aPartirDe(funcionario.getNome()),
                NomeDTO.aPartirDe(funcionario.getNomeMae()),
                funcionario.getNomePai() != null ? NomeDTO.aPartirDe(funcionario.getNomePai()) : new NomeDTO(),
                funcionario.getDataNascimento(), funcionario.getCpf().desformatado(),
                EnderecoDTO.aPartirDe(funcionario.getEndereco()), funcionario.getDataAdmissao(),
                funcionario.getCarteiraTrabalho().desformatado(),
                funcionario.getTelefones().stream().map(TelefoneDTO::aPartirDe).collect(Collectors.toSet()),
                funcionario.getEmail());
    }

    @Override
    public Funcionario converteParaModelo() {
        return new Funcionario(this.uuid, this.nome.converteParaModelo(), this.dataNascimento, CPF.aPartirDe(cpf),
                this.nomeMae.converteParaModelo(), this.nomePai.converteParaModelo(),
                this.endereco.converteParaModelo(), this.dataAdmissao,
                CarteiraTrabalho.aPartirDe(this.carteiraTrabalho),
                telefones.stream().map(TelefoneDTO::converteParaModelo).collect(Collectors.toList()), email);
    }

    @Override
    public int compareTo(FuncionarioDTO o) {
        return this.nome.compareTo(o.getNome());
    }

}
