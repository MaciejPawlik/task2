package com.example.customers.application.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String[] PUBLIC_ENDPOINTS = {
            "/v2/api-docs",
            "/swagger-resources/**",
            "/webjars/**",
            "/swagger-ui.html"
    };

    private final AuthenticationEntryPoint entryPoint;
    private final PasswordEncoder passwordEncoder;

    public WebSecurityConfig(AuthenticationEntryPoint authenticationEntryPoint, PasswordEncoder passwordEncoder) {
        this.entryPoint = authenticationEntryPoint;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("admin").password(passwordEncoder.encode("admin")).authorities("ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(PUBLIC_ENDPOINTS).permitAll()
                .antMatchers("/customers", "/customers/**").hasAnyAuthority("ADMIN")
                .and()
                .httpBasic()
                .authenticationEntryPoint(entryPoint)
                .and()
                .formLogin().disable()
                .csrf().disable();
    }
}
