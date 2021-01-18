package br.com.valhala.ponto.infraestrutura.app.configuracao;

import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import com.sun.faces.config.FacesInitializer;

public class PontoWebApplicationInitializer extends FacesInitializer implements WebApplicationInitializer {
	
    @Override
    public void onStartup(Set<Class<?>> classes, ServletContext servletContext) throws ServletException {
        super.onStartup(classes, servletContext);
    }

    @Override
    public void onStartup(final ServletContext sc) throws ServletException {
    	
        sc.setInitParameter("javax.faces.FACELETS_SKIP_COMMENTS", "true");
        sc.setInitParameter("primefaces.THEME", "omega");
        
        final AnnotationConfigWebApplicationContext root = new AnnotationConfigWebApplicationContext();
        root.register(PontoWebConfig.class, PontoJPAConfig.class, PontoViewScopeConfig.class);
        sc.addListener(new ContextLoaderListener(root));
    }


}