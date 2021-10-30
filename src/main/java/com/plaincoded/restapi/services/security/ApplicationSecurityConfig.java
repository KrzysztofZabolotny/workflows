package com.plaincoded.restapi.services.security;


import com.plaincoded.restapi.services.userServices.MyUserDetailsService;
import com.plaincoded.restapi.services.jwt.JwtTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private JwtTokenFilter jwtFilter;

    private final PasswordEncoder passwordEncoder;

    private MyUserDetailsService myUserDetailsService;

    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder, MyUserDetailsService myUserDetailsService) {
        this.passwordEncoder = passwordEncoder;
        this.myUserDetailsService = myUserDetailsService;
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .rememberMe()
                .and()
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers("/h2-console/**").permitAll()
                .antMatchers("/feedback/**").permitAll()
                .antMatchers(HttpMethod.POST, "/user").permitAll()
                .antMatchers( "/register/**").permitAll()
                .antMatchers( "/testEndpoint/**").permitAll()
                .antMatchers("/auth").permitAll()
                .antMatchers("/test/**").permitAll()
                .antMatchers("/tasks/**").permitAll()
                .antMatchers("/templates/**").permitAll()
                .antMatchers("/workflows/**").permitAll()
                .antMatchers( "/reset-password/**").permitAll()
                .antMatchers("/v2/api-docs", "/configuration/ui", "/swagger-resources", "/configuration/security", "/swagger-ui.html", "/webjars/**", "/swagger-resources/configuration/ui", "/swagger-ui.html").permitAll()
                .anyRequest()
                .authenticated();


        http.headers().frameOptions().disable();/*REQUIRED FOR H2-CONSOLE*/
    }


    @Bean
    public AuthenticationManager authenticationManagerBean(){

        try {
            return super.authenticationManagerBean();
        }catch (Exception e){

        }

        return null;
    }

    @Bean
    DaoAuthenticationProvider authenticationProvider() {

        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
        daoAuthenticationProvider.setUserDetailsService(this.myUserDetailsService);

        return daoAuthenticationProvider;
    }
    /*remove*/

}
