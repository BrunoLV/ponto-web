package br.com.valhala.ponto.aplicacao.comandos;

import br.com.valhala.ponto.dominio.notificacao.NofiticacaoDominio;
import lombok.Getter;

@Getter
public abstract class Comando {

    private NofiticacaoDominio notificacao = new NofiticacaoDominio();

}
