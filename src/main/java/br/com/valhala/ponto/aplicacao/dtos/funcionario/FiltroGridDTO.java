package br.com.valhala.ponto.aplicacao.dtos.funcionario;

import java.time.LocalDate;

import br.com.valhala.ponto.aplicacao.dtos.DTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FiltroGridDTO implements DTO {

	private String uuid;
	private Long cpf;
	private NomeDTO nome;
	private NomeDTO nomeMae;
	private NomeDTO nomePai;
	private LocalDate dataNascimento;
	private LocalDate dataAdmissao;
	
}
