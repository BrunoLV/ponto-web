package br.com.valhala.ponto.aplicacao.dtos;

public interface DTOTransmutavelParaModelo<M> extends DTO {

    M converteParaModelo();

}
