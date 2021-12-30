package com.nicolasgandrade.securityapp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    //Em todos os requests serão enviados o nome e a senha de forma automatizada
    //É impossível fazer logout
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "index", "/css/*", "/js/*").permitAll()
                .antMatchers("/api/**").hasRole(ApplicationUserRole.STUDENT.name())//Configura rotas sem autenticação
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic(); //Baic Authentication
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails nicolasgandradeUser = User.builder()
                .username("nicolasgandrade")
                .password(passwordEncoder.encode("password"))
                .roles(ApplicationUserRole.ADMIN.name())
                .build();

        UserDetails mariaUser = User.builder()
                .username("maria")
                .password(passwordEncoder.encode("maria123"))
                .roles(ApplicationUserRole.STUDENT.name())
                .build();

        return new InMemoryUserDetailsManager(
                nicolasgandradeUser,
                mariaUser
        );
    }
}
