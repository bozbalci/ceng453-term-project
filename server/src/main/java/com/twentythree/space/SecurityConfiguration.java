package com.twentythree.space;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.POST, "/player").permitAll()
                .antMatchers(HttpMethod.GET, "/leaderboard").permitAll()
                .antMatchers(HttpMethod.GET, "/player").hasAuthority("ROLE_ADMIN")
                .anyRequest().fullyAuthenticated()
                .and().httpBasic()
                .and().csrf().disable();
    }
}
