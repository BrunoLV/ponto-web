package br.com.valhala.ponto.dominio.excecoes;

public class ModeloNaoEncontradoException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ModeloNaoEncontradoException(String message) {
        super(message);
    }

}
