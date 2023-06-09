package com.example._3_1_3.configs;


import com.example._3_1_3.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import org.springframework.security.provisioning.JdbcUserDetailsManager;

import javax.sql.DataSource;

//@Configuration
@EnableWebSecurity(debug = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/authenticated/**").authenticated()
                .antMatchers("/only_for_admins").hasRole("ADMIN")
                .antMatchers("/read_profile").hasAuthority("READ_PROFILE")
                .and()
                .formLogin()
//                .loginProcessingUrl("/hellologin")
                .and()
                .logout().logoutSuccessUrl("/");
    }


//in - Memory
//@Bean
//    public UserDetailsService users(){
//    UserDetails user = User.builder()
//            .username("user")
//            .password("{bcrypt}$2a$12$kroHgYLs3ETKuBSZTliMWeNu3UtWpNdPJnR23gWyPFsTdE2SDSCE2")
//            .roles("USER")
//            .build();
//
//    UserDetails adminj = User.builder()
//            .username("admin")
//            .password("{bcrypt}$2a$12$kroHgYLs3ETKuBSZTliMWeNu3UtWpNdPJnR23gWyPFsTdE2SDSCE2")
//            .roles("ADMIN","USER")
//            .build();
//    return new InMemoryUserDetailsManager(user,adminj);
//    }

//jdbcaouthenticated

//    @Bean
//    public JdbcUserDetailsManager users (DataSource dataSource){
//
////        UserDetails user = User.builder()
////            .username("user")
////            .password("{bcrypt}$2a$12$kroHgYLs3ETKuBSZTliMWeNu3UtWpNdPJnR23gWyPFsTdE2SDSCE2")
////            .roles("USER")
////            .build();
////
////    UserDetails adminj = User.builder()
////            .username("admin")
////            .password("{bcrypt}$2a$12$kroHgYLs3ETKuBSZTliMWeNu3UtWpNdPJnR23gWyPFsTdE2SDSCE2")
////            .roles("ADMIN","USER")
////            .build();
//        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
////        if(jdbcUserDetailsManager.userExists(user.getUsername())){   // это просто проверка есть ли такой юзер
////            jdbcUserDetailsManager.deleteUser(user.getUsername());
////        }
////        if(jdbcUserDetailsManager.userExists(adminj.getUsername())){
////            jdbcUserDetailsManager.deleteUser(adminj.getUsername());
////        }
////
////        jdbcUserDetailsManager.createUser(user);
////        jdbcUserDetailsManager.createUser(adminj);
//        return jdbcUserDetailsManager;
//    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
daoAuthenticationProvider.setUserDetailsService(userService);
        return daoAuthenticationProvider;
    }






}
