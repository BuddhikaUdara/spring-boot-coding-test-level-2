package com.accenture.codingtest.springbootcodingtest.security;


import com.accenture.codingtest.springbootcodingtest.enums.UserRoles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class CustomWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
    
	
	
	  @Autowired
	  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	    auth
	      .inMemoryAuthentication()
	        .withUser("admin")  
	          .password("adminPassword")
	          .roles(UserRoles.ADMIN.toString())
	          .and()
	        .withUser("productOwner") 
	          .password("poPassword")
	          .roles(UserRoles.PRODUCT_OWNER.toString())
	          .and()
		    .withUser("user") 
		      .password("userPassword")
		      .roles(UserRoles.USER.toString());
	  }

	  @Override
	  public void configure(WebSecurity web) throws Exception {
	    web
	      .ignoring()
	         .antMatchers("/resources/**"); 
	  }

	  @Override
	  protected void configure(HttpSecurity http) throws Exception {
	    http
	    .httpBasic()
        .and()
        .authorizeRequests()
        .antMatchers("/api/v1/users/**").hasRole(UserRoles.ADMIN.toString())
        .antMatchers(HttpMethod.POST, "/api/v1/projects").hasRole(UserRoles.PRODUCT_OWNER.toString())
        .antMatchers(HttpMethod.POST, "/api/v1/projects/").hasRole(UserRoles.PRODUCT_OWNER.toString())
        .antMatchers(HttpMethod.PUT, "/api/v1/projects/**").hasRole(UserRoles.PRODUCT_OWNER.toString())
        .antMatchers(HttpMethod.PATCH, "/api/v1/projects/**").hasRole(UserRoles.PRODUCT_OWNER.toString())
        .antMatchers(HttpMethod.DELETE, "/api/v1/projects/**").hasRole(UserRoles.PRODUCT_OWNER.toString())
        .antMatchers(HttpMethod.POST, "/api/v1/tasks").hasRole(UserRoles.PRODUCT_OWNER.toString())
        .antMatchers(HttpMethod.POST, "/api/v1/tasks/").hasRole(UserRoles.PRODUCT_OWNER.toString())
        .antMatchers(HttpMethod.DELETE, "/api/v1/tasks/**").hasRole(UserRoles.PRODUCT_OWNER.toString())
        .anyRequest()
        .authenticated()
        .and()
        .formLogin();
	  }
	
	
}