package br.dev.mhc.authentication.resources;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.dev.mhc.agendei.security.JWTUtil;
import br.dev.mhc.agendei.security.UserSS;
import br.dev.mhc.agendei.services.AuthService;

@RestController
@RequestMapping(value = "/auth")
public class AuthResource {

	@Autowired
	private JWTUtil jwtUtil;

	@PostMapping(value = "/refresh_token")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void refreshToken(HttpServletResponse response) {
		UserSS user = AuthService.getAuthenticatedUser();
		String token = jwtUtil.generateToken(user.getUsername());
		response.addHeader("Authorization", "Bearer " + token);
		response.addHeader("access-control-expose-headers", "Authorization");
	}

}
