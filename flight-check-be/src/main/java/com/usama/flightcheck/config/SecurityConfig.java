package com.usama.flightcheck.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.usama.flightcheck.security.CustomUserDetailService;
import com.usama.flightcheck.security.JwtAuthenticationEntryPoint;
import com.usama.flightcheck.security.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;
	 
	@Autowired
	private JwtAuthenticationEntryPoint authenticationEntryPoint;
	
	@Autowired
	private CustomUserDetailService customUserDetailsService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(customUserDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder);

        return authProvider;
    }
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		

//	                http.authorizeRequests() 
//	                // URL matching for accessibility
//	                .antMatchers("/api/auth/login").permitAll()
////	                .antMatchers("/admin/**").hasAnyAuthority("ADMIN")
////	                .antMatchers("/account/**").hasAnyAuthority("USER")
//	                .anyRequest().authenticated()
//	                .and()
//	                // form login
//	                .csrf().disable().formLogin()
//	                .loginPage("/api/auth/login")
//	                .failureUrl("/login?error=true")
////	                .successHandler(sucessHandler)
//	                .usernameParameter("emailId")
//	                .passwordParameter("password")
//	                .and()
//	                // logout
//	                .logout()
//	                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//	                .logoutSuccessUrl("/")
//	                .and()
//	                .exceptionHandling()
//	                .accessDeniedPage("/access-denied");
//
////	                http.authenticationProvider(authenticationProvider());
//	                http.headers().frameOptions().sameOrigin();
//
//	                return http.build();
//	    }
		
		System.out.println("\n Inside security filter chain\n");
		http.cors().and().csrf()
		.disable()
		.authorizeHttpRequests()
		.antMatchers("/api/v1/auth/login", "/swagger-ui/index.html", "/api/v1/flight/allflights")
		.permitAll()
		.anyRequest()
		.authenticated()
		.and()
		.exceptionHandling()
		.authenticationEntryPoint(this.authenticationEntryPoint)
		.and()
		.authenticationProvider(authenticationProvider())
		.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS); 
		
//		return http.build();
		
		http.addFilterBefore(this.jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}
}
