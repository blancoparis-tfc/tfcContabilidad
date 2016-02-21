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
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.inMemoryAuthentication()
				.withUser("user").password("password").roles("USER");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		super.configure(http);
		http.authorizeRequests()	// Le indicamos que la autorizacion va a ser a nivel de request.
			.anyRequest().authenticated()  // Le indicamos que cada solicitud requiere que el usuario de autentique.
			.and()
		.formLogin()	// Configurar el formato de login.
			.loginPage("/login") // aqui le indicamos donde se encuentra la pagina de login.
			.permitAll();// Aqui le indicamos que la pagina de login es publica.*/
	}
	
	
    @Override
	public void configure(WebSecurity web) throws Exception {
    	web
    		.ignoring()
    		.antMatchers("/resources/**")
    		.antMatchers("/node_modules/**");
		super.configure(web);
	}
	
}
