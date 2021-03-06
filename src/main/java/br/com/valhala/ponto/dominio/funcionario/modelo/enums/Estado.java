package br.com.valhala.ponto.dominio.funcionario.modelo.enums;

public enum Estado {

    AC("Acre"), AL("Alagoas"), AP("Amapá"), AM("Amazonas"), BA("Bahia"), CE("Ceará"), DF("Distrito Federal"),
    ES("Espírito Santo"), GO("Goiás"), MA("Maranhão"), MT("Mato Grosso"), MS("Mato Grosso do Sul"), MG("Minas Gerais"),
    PA("Pará"), PB("Paraíba"), PR("Paraná"), PE("Pernambuco"), PI("Piauí"), RJ("Rio de Janeiro"),
    RN("Rio Grande do Norte"), RS("Rio Grande do Sul"), RO("Rondônia"), RR("Raraima"), SC("Santa Catarina"),
    SP("São Paulo"), SE("Sergipe"), TO("Tocantins");

    private String nome;

    private Estado(final String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

}