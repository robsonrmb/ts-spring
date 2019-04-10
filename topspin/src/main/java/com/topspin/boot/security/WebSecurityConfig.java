/*package com.topspin.boot.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.topspin.boot.domain.Amigo;

//@Configuration
//@EnableWebSecurity
public class WebSecurityConfig {
	
	//@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.csrf().disable().authorizeRequests()
			//.antMatchers("/home").permitAll()
			.antMatchers(HttpMethod.POST, "/login").permitAll()
			.anyRequest().authenticated()
			.and()
			
			// filtra requisições de login
			.addFilterBefore(new JWTLoginFilter("/login", authenticationManager()),
	                UsernamePasswordAuthenticationFilter.class)
			
			// filtra outras requisições para verificar a presença do JWT no header
			.addFilterBefore(new JWTAuthenticationFilter(),
	                UsernamePasswordAuthenticationFilter.class);
		
		//super.configure(httpSecurity);
	}
	
	private AuthenticationManager authenticationManager() {
		// TODO Auto-generated method stub
		
		//List<Amigo> listaDeAmigos = new ArrayList<Amigo>();
		//listaDeAmigos.stream()
		//	.filter(a -> a.getUsuario().getEstado().equals("DF"))
		//	.sorted((a1, a2) -> a1.getId().compareTo(a2.getId()))
		//	.map(a -> a.getUsuario().getNome())
		//	.forEach(System.out::println);
		
		return null;
		
		
	}

	//@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// cria uma conta default
		auth.inMemoryAuthentication()
			.withUser("admin")
			.password("password")
			.roles("ADMIN");
	}

}
*/
