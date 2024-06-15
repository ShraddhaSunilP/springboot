package com.luv2code.springboot.demosecurity.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class DemoSecurityConfig {

    //Add support for JDBC... no more hardcoded users
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource){
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);

        jdbcUserDetailsManager.setUsersByUsernameQuery(
        //define query to retrive a user by username
        "select user_id, pw, active from members where user_id=?");

        // define query to retrieve the authoruities/roles by username
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
        "select user_id, role from roles where user_id=?"

        );
        return jdbcUserDetailsManager;
    }


    // configure security of web paths in application, login, logout etc.
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests(configurer ->
                configurer
                        .requestMatchers("/").hasRole("EMPLOYEE")
                        .requestMatchers("/leaders/**").hasRole("MANAGER")
                        .requestMatchers("/systems/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin(form ->
                        form
                                .loginPage("/showMyLoginPage")  //show our custom form at the any request mapping
                                .loginProcessingUrl("/authenticateTheUser") //login form should POST data to this URL for processing(check user id and password)
                                .permitAll()   //Allow everyone to see login page. No need to be logged in.
                )
                .logout(logout -> logout.permitAll()
                )

                .exceptionHandling(configurer ->
                        configurer.accessDeniedPage("/access-denied"));
        return http.build();
    }

}

//@Bean
//public InMemoryUserDetailsManager userDetailsManager(){
//
//    UserDetails john = User.builder()
//            .username("john")
//            .password("{noop}test123")
//            .roles("EMPLOYEE")
//            .build();
//
//    UserDetails mary = User.builder()
//            .username("mary")
//            .password("{noop}test123")
//            .roles("EMPLOYEE","MANAGER")
//            .build();
//
//    UserDetails susan = User.builder()
//            .username("susan")
//            .password("{noop}test123")
//            .roles("EMPLOYEE", "MANAGER", "ADMIN")
//            .build();
//
//    return new InMemoryUserDetailsManager(john, mary, susan);
//}