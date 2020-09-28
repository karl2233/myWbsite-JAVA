package com.company.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


import com.company.security.jwt.JwtConfigurer;
import com.company.security.jwt.JwtTokenProvider;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private JwtTokenProvider tokenProvider;
	
	
//	@Bean
//	public BCryptPasswordEncoder passwordEncoder() {
//		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
//		return bCryptPasswordEncoder;
//	}
//	
//	@Bean
//	@Override
//	public AuthenticationManager authenticationManagerBean() throws Exception {
//		return super.authenticationManagerBean();
//	}
//	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		    .cors().and()
			.httpBasic().disable()
			.csrf().disable()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
				.authorizeRequests()
				.antMatchers("/onlyfullstack-stomp-endpoint/**").permitAll()
				.antMatchers("/register/**").permitAll()
				.antMatchers("/ws/**").permitAll()
				.antMatchers("/app/**").permitAll()
				.antMatchers("/user/**").permitAll()
				//.antMatchers("/shop/**").permitAll()
				//.antMatchers(HttpMethod.GET, "/vehicles/**").permitAll()
                  .antMatchers("/admin/**").permitAll()//.hasRole("ADMIN")
                  
                //.antMatchers(HttpMethod.GET, "/v1/vehicles/**").permitAll()
                .anyRequest().authenticated()
			.and()
			.apply(new JwtConfigurer(tokenProvider));
		
	
	}
	
//	  @Bean
//	    public CorsConfigurationSource corsConfigurationSource() {
////	        CorsConfiguration configuration = new CorsConfiguration();
////	        configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
////	        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
////	        configuration.setAllowedHeaders(Arrays.asList("authorization", "content-type", "x-auth-token"));
////	        
////	       // configuration.setExposedHeaders(Arrays.asList("x-auth-token"));
////	        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
////	        source.registerCorsConfiguration("/**", configuration);
////	        return source;
//
//		   UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//	         CorsConfiguration configuration = new CorsConfiguration();
//	        //configuration.setExposedHeaders(Arrays.asList("Authorization"));
//	         configuration.setAllowedOrigins(Arrays.asList("*"));
//	         configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
//		        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
//		        configuration.setAllowedHeaders(Arrays.asList("authorization", "content-type", "x-auth-token"));
//	        configuration.applyPermitDefaultValues();
//	        source.registerCorsConfiguration("/**", configuration);
//	  
//	        return source;
//	    }
	
	@Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("authorization", "content-type", "x-auth-token"));
        configuration.setExposedHeaders(Arrays.asList("x-auth-token"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/register/**", configuration);
        source.registerCorsConfiguration("/user/**", configuration);
        source.registerCorsConfiguration("/admin/**", configuration);
        source.registerCorsConfiguration("/app/**", configuration);
       
        return source;
    }
	}


