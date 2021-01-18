package br.com.valhala.ponto.aplicacao.comandos.funcionario.comandos;

import br.com.valhala.ponto.aplicacao.comandos.Comando;
import br.com.valhala.ponto.dominio.funcionario.modelo.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Getter
@AllArgsConstructor
@Builder
public class ComandoAtualizaFuncionario extends Comando implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String uuid;
    private final Nome nome;
    private final LocalDate dataNascimento;
    private final CPF cpf;
    private final Nome nomeMae;
    private final Nome nomePai;
    private final Endereco endereco;
    private final LocalDate dataAdmissao;
    private final CarteiraTrabalho carteiraTrabalho;
    private final Set<Telefone> telefones;
    private final String email;

}
