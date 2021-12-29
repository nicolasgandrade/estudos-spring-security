package com.nicolasgandrade.securityapp.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    //Em todos os requests serão enviados o nome e a senha de forma automatizada
    //É impossível fazer logout
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "index", "/css/*", "/js/*") //Configura rotas sem autenticação
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic(); //Baic Authentication
    }
}
