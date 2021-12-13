package br.dev.mhc.agendei.services;

import org.springframework.security.core.context.SecurityContextHolder;

import br.dev.mhc.agendei.entities.enums.Profile;
import br.dev.mhc.agendei.security.UserSS;
import br.dev.mhc.agendei.services.exceptions.AuthorizationException;

public class AuthService {
	
	public static UserSS getAuthenticatedUser() {
		try {
			return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			return null;
		}
	}
	
	public static void validatesUserAuthorization(Long authorizedUserId) {
		UserSS userSS = getAuthenticatedUser();
		
		if(authorizedUserId == null || (authorizedUserId != userSS.getId() && !userSS.hasRole(Profile.ADMIN))) {
			throw new AuthorizationException("Access denied");			
		}
	}

}
