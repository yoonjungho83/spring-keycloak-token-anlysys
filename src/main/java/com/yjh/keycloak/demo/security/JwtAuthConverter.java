package com.yjh.keycloak.demo.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimNames;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
public class JwtAuthConverter implements Converter<Jwt, AbstractAuthenticationToken> {

	
	private static final JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();

	@Value("${jwt.auth.converter.resource-id}")
    private String resourceId;
	@Value("${keycloak.adminClientId}")
	private String clientId;
	
	
	@Override
    public AbstractAuthenticationToken convert(Jwt jwt)   {
		
        Collection<GrantedAuthority> authorities = Stream.concat(
                jwtGrantedAuthoritiesConverter.convert(jwt).stream(),
                extractResourceRoles(jwt).stream()).collect(Collectors.toSet());
        
        return new JwtAuthenticationToken(jwt, authorities, getPrincipalClaimName(jwt));
    }

    private String getPrincipalClaimName(Jwt jwt) {
        String claimName = JwtClaimNames.SUB;
        return jwt.getClaim(claimName);
    }

    private Collection<? extends GrantedAuthority> extractResourceRoles(Jwt jwt) {

        Map<String, Object> realmAccess = jwt.getClaim("realm_access");
        Map<String, Object> resourceAccess = jwt.getClaim("resource_access");

        Collection<String> allRoles = new ArrayList<>();
        Collection<String> resourceRoles;
        Collection<String> realmRoles ;

        log.info("clientId = {}",clientId);
        log.info("realmAccess = {}",realmAccess);
        log.info("resourceAccess = {} ",resourceAccess);
        
        //realm 권한
        if(resourceAccess != null && resourceAccess.get("account") != null){
            Map<String,Object> account =  (Map<String,Object>) resourceAccess.get("account");
            Map<String,Object> clientRoles =  (Map<String,Object>) resourceAccess.get(clientId);
            if(account.containsKey("roles") ){
                resourceRoles = (Collection<String>) account.get("roles");
                allRoles.addAll(resourceRoles);
            }
        }
        
        //client 권한 jwt.getClaim("azp") == 토큰이 생성된 client id
        if(resourceAccess != null && resourceAccess.get(clientId) != null
        		&& jwt.getClaim("azp").equals(clientId)){
        	Map<String,Object> clientRoles =  (Map<String,Object>) resourceAccess.get(clientId);
        	if(clientRoles.containsKey("roles") ){
        		resourceRoles = (Collection<String>) clientRoles.get("roles");
        		allRoles.addAll(resourceRoles);
        	}
        }

        if(realmAccess != null && realmAccess.containsKey("roles")){
            realmRoles = (Collection<String>) realmAccess.get("roles");
            allRoles.addAll(realmRoles);
        }
        if (allRoles.isEmpty()) {

            return Set.of();
        }

        return allRoles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                .collect(Collectors.toSet());
    }
	
}
