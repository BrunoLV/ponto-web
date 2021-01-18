package br.com.valhala.ponto.aplicacao.comandos.funcionario.comandos;

import br.com.valhala.ponto.aplicacao.comandos.Comando;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Getter
@RequiredArgsConstructor
@Builder
public class ComandoExcluiFuncionario extends Comando implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String uuid;
}
