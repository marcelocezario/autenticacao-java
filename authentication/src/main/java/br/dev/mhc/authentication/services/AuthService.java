package br.dev.mhc.authentication.services;

import org.springframework.security.core.context.SecurityContextHolder;

import br.dev.mhc.authentication.entities.enums.Profile;
import br.dev.mhc.authentication.security.UserSS;
import br.dev.mhc.authentication.services.exceptions.AuthorizationException;

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
