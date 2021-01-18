package br.com.valhala.ponto.dominio.notificacao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Erro implements Serializable {

    private static final long serialVersionUID = 1L;

    private String campo;
    private String mensagem;
    private Throwable causa;

}
