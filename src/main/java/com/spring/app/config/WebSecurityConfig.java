package com.spring.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	  @Autowired
	  private UserDetailsService userDetailsService;
	  @Autowired
	  private BCryptPasswordEncoder bCryptPasswordEncoder;
	  
	  
	  @Bean
		public AuthenticationProvider authProvider() {
			
			DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
			provider.setUserDetailsService(userDetailsService);
						
			/* it is using with Bcrypt it decode the encrypt password from database */
			provider.setPasswordEncoder(bCryptPasswordEncoder);
			return provider;
		}
	  

	  @Override
	  protected void configure(HttpSecurity http) throws Exception {
		  
		  http
          .csrf().disable()
          .authorizeRequests().antMatchers("/login","/registstu","/savestu").permitAll()
          .anyRequest().authenticated()
          .and()
          .formLogin()
          .loginPage("/login").permitAll()
          .and()
          .logout().invalidateHttpSession(true)
          .clearAuthentication(true)
          .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
          .logoutSuccessUrl("/logout-success").permitAll();
	  }
}
