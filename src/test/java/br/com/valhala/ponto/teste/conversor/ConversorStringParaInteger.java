package br.com.valhala.ponto.teste.conversor;

import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.SimpleArgumentConverter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConversorStringParaInteger extends SimpleArgumentConverter {

    @Override
    protected Object convert(Object source, Class<?> targetType) throws ArgumentConversionException {
        assertEquals(Integer.class, targetType, "Só é possível converter para Integer");
        return Integer.parseInt((String) source);
    }

}
