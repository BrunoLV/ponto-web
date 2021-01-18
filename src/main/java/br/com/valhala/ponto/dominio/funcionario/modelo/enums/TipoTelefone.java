package br.com.valhala.ponto.dominio.funcionario.modelo.enums;

public enum TipoTelefone {

    RESIDENCIAL("Residencial"), CELULAR("Celular");

    private String descricao;

    private TipoTelefone(final String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

}
