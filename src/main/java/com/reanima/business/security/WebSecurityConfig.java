package com.reanima.business.security;

import com.reanima.business.service.impl.CustomUserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailsServiceImpl();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/api").hasAnyAuthority("ADMIN", "USER")
                .antMatchers("/api/user/userlist").hasAuthority("ADMIN")
                .antMatchers("/api/recipe/saverecipe").hasAnyAuthority("ADMIN", "USER")
                .antMatchers("/api/recipe/updaterecipe").hasAnyAuthority("ADMIN", "USER")
                .antMatchers("/api/recipe/deleterecipe").hasAuthority("ADMIN")
                .antMatchers("/api/ingredient/saveingredient").hasAnyAuthority("ADMIN", "USER")
                .antMatchers("/api/ingredient/updateingredient").hasAnyAuthority("ADMIN", "USER")
                .antMatchers("/api/ingredient/deleteingredient").hasAuthority("ADMIN")
                .anyRequest().permitAll()
                .and()
                .formLogin().loginPage("/api/user/login")
                .usernameParameter("userEmail")
                .passwordParameter("userPassword")
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/api/user/logout")
                .logoutSuccessUrl("/api/user/home")
                .clearAuthentication(true)
                .permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/api/user/restricted_access");
    }
}
