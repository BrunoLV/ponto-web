package br.com.valhala.ponto.dominio.notificacao;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

@Getter
@NoArgsConstructor
@ToString
public class NofiticacaoDominio implements Serializable {

    private static final long serialVersionUID = 1L;

    private Collection<Erro> erros;

    public boolean temErros() {
        return erros != null && !erros.isEmpty();
    }

    public void adicionaErro(final Erro erro) {
        if (erros == null) {
            this.erros = new ArrayList<>();
        }
        this.erros.add(erro);
    }

}
