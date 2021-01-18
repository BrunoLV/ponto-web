package br.com.valhala.ponto.infraestrutura.app.fabrica;

import java.util.UUID;

public final class FabricaUUID {

    public static final String getUUID() {
        return UUID.randomUUID().toString().replace("-", "").toUpperCase();
    }

}
