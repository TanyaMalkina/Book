package com.example.bookshop.config;

import com.example.bookshop.services.PersonDetailsService;
import org.springframework.cglib.proxy.NoOp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig  {


   private final PersonDetailsService personDetailsService;
   @Bean
   public PasswordEncoder getPasswordEncoder(){
       return new BCryptPasswordEncoder();
   }
   @Bean
   public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{


       http
               .authorizeHttpRequests()
               .requestMatchers("admin").hasRole("ADMIN")
               .requestMatchers("/authentication", "/registration", "/error" , "/resources/**", "/static/**", "/css/**", "/js/**", "/img/**").permitAll()
               .anyRequest().hasAnyRole("USER", "ADMIN")
               .and()
               .formLogin().loginPage("/authentication")
               .loginProcessingUrl("/process_login")
               .defaultSuccessUrl("/index", true)
               .failureUrl("/authentication?error")
               .and()
               .logout().logoutUrl("/logout").logoutSuccessUrl("/authentication");
       return http.build();

   }
    public SecurityConfig(PersonDetailsService personDetailsService) {
        this.personDetailsService = personDetailsService;

    }


    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(personDetailsService)
                .passwordEncoder(getPasswordEncoder());
    }
}
