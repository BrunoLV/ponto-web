package br.com.valhala.ponto.aplicacao.dtos.funcionario;

import br.com.valhala.ponto.aplicacao.dtos.DTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FuncionarioDataGridDTO implements DTO, Serializable {

    private static final long serialVersionUID = 1L;

    private String uuid;
    private NomeDTO nome;
    private NomeDTO nomeMae;
    private NomeDTO nomePai;
    private LocalDate dataNascimento;
    private LocalDate dataAdmissao;

}
