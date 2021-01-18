package br.com.valhala.ponto.aplicacao.comandos;

public interface ServicoDeComando<C> {

    void executa(C comando);

}
