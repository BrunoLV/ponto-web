package br.com.valhala.ponto.dominio;

import java.util.Collection;

public interface Repositorio<R extends RaizAgregado> {

    R salva(final R r);

    void exclui(final String uuid);

    R buscaPorId(final String uuid);

    Collection<R> buscaTodos();

    long contagem();

}
