package br.com.valhala.ponto.infraestrutura.app.configuracao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.config.CustomScopeConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.valhala.ponto.infraestrutura.web.escopos.jsf.ViewScope;

@Configuration
public class PontoViewScopeConfig {
	
	@Bean
    public static CustomScopeConfigurer customScopeConfigurer() {
        Map<String, Object> scopes = new HashMap<>();
        scopes.put("view", new ViewScope());
 
        CustomScopeConfigurer customScopeConfigurer = new CustomScopeConfigurer();
        customScopeConfigurer.setScopes(scopes);
 
        return customScopeConfigurer;
    }

}
