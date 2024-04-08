package com.yjh.keycloak.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import com.yjh.keycloak.demo.security.JwtAuthConverter;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig  {

	public static final String ADMIN   = "admin";
	public static final String MANAGER = "manager";
	public static final String USER    = "user";
	public static final String CLIENT_USER    = "oauth2_demo2_client";//client_user
	
	private final JwtAuthConverter jwtAuthConverter;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		
		return http.authorizeHttpRequests(authorizeHttpRequests -> authorizeHttpRequests
        		
                .requestMatchers("/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs", "/v3/api-docs/**").permitAll()
                
                .requestMatchers("/api/v1/open/"   ,"/api/v1/open/**"   ).permitAll() // HttpMethod.GET, 
                .requestMatchers("/api/v1/user/"   ,"/api/v1/user/**"   ).hasAnyRole(ADMIN,MANAGER, USER)
                .requestMatchers("/api/v1/client/" ,"/api/v1/client/**" ).hasAnyRole(ADMIN,MANAGER,USER,CLIENT_USER)
                .requestMatchers("/api/v1/admin/"  ,"/api/v1/admin/**"  ).hasAnyRole(ADMIN)
                .requestMatchers("/api/v1/manager/","/api/v1/manager/**").hasAnyRole(ADMIN,MANAGER)
                .anyRequest().authenticated())
        
        .oauth2ResourceServer(oauth2ResourceServer -> oauth2ResourceServer.jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthConverter) ) )
        .sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .cors(Customizer.withDefaults())
//        .cors(cors -> cors.disable())
        .build();
		
		
		
	}

	
	
	//@Bean
//    public WebSecurityCustomizer webSecurityCustomizer() {
//
//        return (web) -> {
//            web.ignoring().requestMatchers(HttpMethod.POST,"/public/**","/users");
//            web.ignoring().requestMatchers(HttpMethod.GET,"/public/**");
//            web.ignoring().requestMatchers(HttpMethod.DELETE,"/public/**");
//            web.ignoring().requestMatchers(HttpMethod.PUT,"/public/**");
//            web.ignoring().requestMatchers(HttpMethod.OPTIONS,"/**")
//                          .requestMatchers("/v3/api-docs/**"
//                        		          , "/configuration/**"
//                        		          , "/swagger-ui/**"
//                        		          , "/swagger-resources/**"
//                        		          , "/swagger-ui.html"
//                        		          , "/webjars/**"
//                        		          , "/api-docs/**");
//        };
//    }
}
