package org.dbp.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SeguridadConfig extends WebSecurityConfigurerAdapter  {

	@Autowired
	public void configureGlobal(final AuthenticationManagerBuilder auth) throws Exception { //NOPMD
		auth
			.inMemoryAuthentication()
				.withUser("user").password("password").roles("USER","ADMINISTRACION","CONTABLE")
				.and()
				.withUser("contable").password("contable").roles("ADMINISTRACION")
				.and()
				.withUser("administrador").password("administrador").roles("CONTABLE");
	}

	@Override
	protected void configure(final HttpSecurity http) throws Exception  { //NOPMD
		super.configure(http);
		http.csrf().disable() // Para las peticiones aya
			.authorizeRequests()	// Le indicamos que la autorizacion va a ser a nivel de request.
			.anyRequest().authenticated()  // Le indicamos que cada solicitud requiere que el usuario de autentique.
			.and()
		.formLogin()	// Configurar el formato de login.
			.loginPage("/login") // aqui le indicamos donde se encuentra la pagina de login.
			.permitAll();// Aqui le indicamos que la pagina de login es publica.*/
	}
	
	
    @Override
	public void configure(final WebSecurity web) throws Exception { //NOPMD
    	web
    		.ignoring()
    		.antMatchers("/resources/**")
    		.antMatchers("/node_modules/**");
		super.configure(web);
	}
	
}
