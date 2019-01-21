package com.gjsm.projectmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@SpringBootApplication
public class ProjectManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectManagerApplication.class, args);
	}
	
	
	@Configuration
    static class WebSecurityConfig extends WebSecurityConfigurerAdapter{
        @Override
        public void configure(WebSecurity web) throws Exception {
             web.ignoring().antMatchers("/**");
        }
    }
}