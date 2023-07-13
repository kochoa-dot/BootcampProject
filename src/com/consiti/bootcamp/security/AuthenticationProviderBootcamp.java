package com.consiti.bootcamp.security;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import java.util.ArrayList;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;;

public class AuthenticationProviderBootcamp implements AuthenticationProvider{

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		try {
			List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
			UserPrincipal userPrincipal = new UserPrincipal();
			
			roles.add(new SimpleGrantedAuthority("ROLE_USER"));
			
			String usuario = authentication.getPrincipal().toString();
			String password = authentication.getCredentials().toString();
			
			if (usuario != "Kevin") {
				throw new BadCredentialsException("Usuario o password incorrectos (" + usuario);
				
			}
			
			UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userPrincipal,
					password, roles);
			
			return (Authentication) usernamePasswordAuthenticationToken;
		} catch (Exception e) {
			mensaje(e.getMessage());
			e.printStackTrace();
		}
		
		return null;
		
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public void mensaje(String respuesta) {
		FacesMessage mensaje = new FacesMessage(respuesta);
		FacesContext.getCurrentInstance().addMessage(null, mensaje);
	}

}
