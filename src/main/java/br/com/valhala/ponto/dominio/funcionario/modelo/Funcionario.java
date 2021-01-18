package br.com.valhala.ponto.dominio.funcionario.modelo;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.valhala.ponto.dominio.RaizAgregado;
import br.com.valhala.ponto.dominio.funcionario.validacao.CpfUnico;
import br.com.valhala.ponto.dominio.notificacao.Erro;
import br.com.valhala.ponto.dominio.notificacao.NofiticacaoDominio;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString(exclude = {"id"})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@CpfUnico
public class Funcionario implements RaizAgregado, Serializable {

    private static final Validator VALIDATOR = Validation.buildDefaultValidatorFactory().getValidator();

    private static final long serialVersionUID = 1L;

    private Long id;

    @NotNull(message = "${funcionario.uuid.required}")
    @EqualsAndHashCode.Include
    private String uuid;

    @NotNull(message = "${funcionario.nome.required}")
    @Valid
    private Nome nome;

    @NotNull(message = "${funcionario.dataNascimento.required}")
    private LocalDate dataNascimento;

    @NotNull(message = "${funcionario.cpf.required}")
    @Valid
    private CPF cpf;

    @NotNull(message = "${funcionario.nomeMae.required}")
    @Valid
    private Nome nomeMae;

    @Valid
    private Nome nomePai;

    @NotNull(message = "${funcionario.endereco.required}")
    @Valid
    private Endereco endereco;

    @NotNull(message = "${funcionario.dataAdmissao.required}")
    private LocalDate dataAdmissao;

    @NotNull(message = "${funcionario.carteiraTrabalho.required}")
    @Valid
    private CarteiraTrabalho carteiraTrabalho;

    @NotEmpty(message = "${funcionario.telefones.required}")
    @Size(min = 1, message = "${funcionario.telefones.min}")
    @Valid
    private Set<Telefone> telefones = new HashSet<>();

    @NotBlank(message = "${funcionario.email.required}")
    @Email(message = "${funcionario.email.invalid}")
    private String email;

    public Funcionario(final String uuid, final Nome nome, final LocalDate dataNascimento, final CPF cpf,
                       final Nome nomeMae, final Nome nomePai, final Endereco endereco, final LocalDate dataAdmissao,
                       final CarteiraTrabalho carteiraTrabalho, final Collection<Telefone> telefones, final String email) {

        this.uuid = uuid;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.cpf = cpf;
        this.nomeMae = nomeMae;
        this.nomePai = nomePai.getNome() == null && nomePai.getSobrenome() == null ? null : nomePai;
        this.endereco = endereco;
        this.dataAdmissao = dataAdmissao;
        this.carteiraTrabalho = carteiraTrabalho;
        this.email = email;

        if (telefones != null) {
            for (Telefone telefone : telefones) {
                adicionaTelefones(telefone);
            }
        }

    }

    private Funcionario(final Long id, final String uuid, final Nome nome, final LocalDate dataNascimento,
                        final CPF cpf, final Nome nomeMae, final Nome nomePai, final Endereco endereco,
                        final LocalDate dataAdmissao, final CarteiraTrabalho carteiraTrabalho, final Collection<Telefone> telefones,
                        final String email) {

        this(uuid, nome, dataNascimento, cpf, nomeMae, nomePai, endereco, dataAdmissao, carteiraTrabalho, telefones, email);
        this.id = id;

    }

    public static Funcionario criaNovo(final String uuid, final Nome nome, final LocalDate dataNascimento, final CPF cpf,
                                       final Nome nomeMae, final Nome nomePai, final Endereco endereco, final LocalDate dataAdmissao,
                                       final CarteiraTrabalho carteiraTrabalho, final Set<Telefone> telefones, final String email) {

        final Funcionario funcionario = new Funcionario(uuid, nome, dataNascimento, cpf, nomeMae, nomePai,
                endereco, dataAdmissao, carteiraTrabalho, telefones, email);

        return funcionario;
    }

    public Funcionario atualiza(final Funcionario dados) {

        Objects.requireNonNull(dados, "Dados são necessários para essa operação.");

        final Funcionario funcionarioAtualizado = new Funcionario(this.id, this.uuid, dados.nome, dados.dataNascimento,
                dados.cpf, dados.nomeMae, dados.nomePai, dados.endereco, dados.dataAdmissao, dados.carteiraTrabalho,
                dados.telefones, dados.email);

        return funcionarioAtualizado;
    }

    public NofiticacaoDominio valida() {

        NofiticacaoDominio notificacao = new NofiticacaoDominio();

        VALIDATOR.validate(this)
                .stream()
                .map(cv -> new Erro(cv.getPropertyPath().toString(), cv.getMessage(), null))
                .forEach(e -> notificacao.adicionaErro(e));

        return notificacao;
    }

    private void adicionaTelefones(final Telefone telefone) {
        if (this.telefones == null) {
            this.telefones = new HashSet<>();
        }
        this.telefones.add(new Telefone(telefone.getTipo(), telefone.getDdd(), telefone.getNumero(), this));
    }

}