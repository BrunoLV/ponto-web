package br.com.valhala.ponto.aplicacao.dtos.funcionario.enums;

public enum TipoTelefoneDTO {

    RESIDENCIAL("Residencial"), 
    CELULAR("Celular");

    private String descricao;

    private TipoTelefoneDTO(final String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

}
