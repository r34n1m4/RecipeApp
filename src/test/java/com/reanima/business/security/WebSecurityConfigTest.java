package com.reanima.business.security;

import com.reanima.business.service.impl.CustomUserDetailsServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;

import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class WebSecurityConfigTest {

    @Mock
    DaoAuthenticationProvider daoAuthProvider;

    @Mock
    HttpSecurity http;

    @Mock
    DataSource dataSource;

    private WebSecurityConfig webSecurityConfig;

    @BeforeEach
    public void setUp() {
        webSecurityConfig = new WebSecurityConfig();
        webSecurityConfig.dataSource = dataSource;
    }

    @Test
    public void userDetailsService_returnCustomUserDetailsServiceImpl() {
        UserDetailsService userDetailsService = webSecurityConfig.userDetailsService();
        assertEquals(userDetailsService.getClass(), CustomUserDetailsServiceImpl.class);
    }

    @Test
    public void passwordEncoder_returnBCryptPasswordEncoder() {
        BCryptPasswordEncoder encoder = webSecurityConfig.passwordEncoder();
        assertEquals(encoder.getClass(), BCryptPasswordEncoder.class);
    }

    @Test
    public void configure_setsAuthenticationProvider() throws Exception {

        WebSecurityConfig config = new WebSecurityConfig();
        AuthenticationManagerBuilder auth = mock(AuthenticationManagerBuilder.class);
        when(auth.authenticationProvider(any())).thenReturn(auth);
        config.configure(auth);
        verify(auth, times(1)).authenticationProvider(any(DaoAuthenticationProvider.class));
    }

    //FIX

//    @Test
//    public void authenticationProvider_returnDaoAuthenticationProvider() {
//        when(new DaoAuthenticationProvider()).thenReturn(daoAuthProvider);
//
//        // Create an instance of the WebSecurityConfig class and call the authenticationProvider() method
//        WebSecurityConfig webSecurityConfig = new WebSecurityConfig();
//        DaoAuthenticationProvider result = webSecurityConfig.authenticationProvider();
//
//        // Verify that the DaoAuthenticationProvider class is being constructed properly
//        verify(daoAuthProvider).setUserDetailsService(any());
//        verify(daoAuthProvider).setPasswordEncoder(any());
//
//        // Verify that the authenticationProvider() method is returning the mock instance of the DaoAuthenticationProvider class
//        assert result == daoAuthProvider;
//    }
//
//    @Test
//    public void configure_setsHttpSecurity() throws Exception {
//        webSecurityConfig.configure(http);
//        verify(http).authorizeRequests()
//                .antMatchers("/api").hasAnyAuthority("ADMIN", "USER")
//                .antMatchers("/api/user/userlist").hasAuthority("ADMIN")
//                .antMatchers("/api/recipe/saverecipe").hasAnyAuthority("ADMIN", "USER")
//                .antMatchers("/api/recipe/updaterecipe").hasAnyAuthority("ADMIN", "USER")
//                .antMatchers("/api/recipe/deleterecipe").hasAuthority("ADMIN")
//                .antMatchers("/api/ingredient/saveingredient").hasAnyAuthority("ADMIN", "USER")
//                .antMatchers("/api/ingredient/updateingredient").hasAnyAuthority("ADMIN", "USER")
//                .antMatchers("/api/ingredient/deleteingredient").hasAuthority("ADMIN")
//                .anyRequest().permitAll()
//                .and()
//                .formLogin().loginPage("/api/user/login")
//                .usernameParameter("userEmail")
//                .passwordParameter("userPassword")
//                .permitAll()
//                .and()
//                .logout()
//                .logoutUrl("/api/user/logout")
//                .logoutSuccessUrl("/api/user/home")
//                .clearAuthentication(true)
//                .permitAll()
//                .and()
//                .exceptionHandling().accessDeniedPage("/api/user/restricted_access");
//    }

}
