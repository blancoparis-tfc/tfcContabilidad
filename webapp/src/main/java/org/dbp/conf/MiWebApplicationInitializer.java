package org.dbp.conf;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.apache.commons.lang3.StringUtils;
import org.dbp.conf.profiles.ProfilesAplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

 
@Configuration
public class MiWebApplicationInitializer 
	//extends AbstractAnnotationConfigDispatcherServletInitializer 
	implements WebApplicationInitializer {
	
	@Override
	public void onStartup(ServletContext servletContext)
			throws ServletException {
		String entornoActivo=System.getProperty("spring.profiles.active");
		if(StringUtils.isBlank(entornoActivo)){
			entornoActivo=ProfilesAplication.dev;
		}
		servletContext.setInitParameter("spring.profiles.active",entornoActivo);		
	}
/*
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { WebConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] { WebConfig.class };
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}
*/
}
