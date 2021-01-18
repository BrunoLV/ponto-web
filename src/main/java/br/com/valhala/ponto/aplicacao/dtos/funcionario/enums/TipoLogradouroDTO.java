package br.com.valhala.ponto.aplicacao.dtos.funcionario.enums;

import java.util.Arrays;
import java.util.Optional;

public enum TipoLogradouroDTO {

    AEROPORTO("Aeroporto"), 
    ALAMEDA("Alameda"), 
    AREA("Área"), 
    AVENIDA("Avenida"), 
    CAMPO("Campo"), 
    CHACARA("Chácara"),
    COLONIA("Colônia"), 
    CONDOMINIO("Condomínio"),
    CONJUNTO("Conjunto"), 
    DISTRITO("Distrito"), 
    ESPLANADA("Esplanada"),
    ESTACAO("Estação"),
    ESTRADA("Estrada"), 
    FAVELA("Favela"),
    FEIRA("Feira"),
    JARDIM("Jardim"), 
    LADEIRA("Ladeira"),
    LAGO("Lago"),
    LAGOA("Lagoa"),
    LARGO("Largo"),
    LOTEAMENTO("Loteamento"),
    MORRO("Morro"),
    NUCLEO("Núcleo"),
    PARQUE("Parque"), 
    PASSARELA("Passarela"), 
    PATIO("Pátio"), 
    PRACA("Praça"),
    QUADRA("Quadra"),
    RECANTO("Recanto"),
    RESIDENCIAL("Residencial"),
    RODOVIA("Rodovia"),
    RUA("Rua"), 
    SETOR("Setor"), 
    SITIO("Sítio"), 
    TRAVESSA("Travessa"),
    TRECHO("Trecho"),
    TREVO("Trevo"),
    VALE("Vale"),
    VEREDA("Vereda"),
    VIA("Via"),
    VIADUTO("Viaduto"),
    VIELA("Viela"),
    VILA("Vila");

    private String descricao;

    private TipoLogradouroDTO(String description) {
        this.descricao = description;
    }

    public static Optional<TipoLogradouroDTO> obtemPorDescricao(final String descricao) {
        return Arrays.stream(TipoLogradouroDTO.values()).filter(t -> t.descricao.equalsIgnoreCase(descricao)).findFirst();
    }

    public String getDescricao() {
        return descricao;
    }

}