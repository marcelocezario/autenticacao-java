package br.dev.mhc.authentication.services;

import java.text.ParseException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.dev.mhc.authentication.entities.User;
import br.dev.mhc.authentication.entities.enums.Profile;
import br.dev.mhc.authentication.repositories.UserRepository;

@Service
public class DBService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	public void databaseSeeding() throws ParseException {
		
		User user1 = new User(null, "admin@mhc.dev.br", pe.encode("teste"), true);
		User user2 = new User(null, "basic1@mhc.dev.br", pe.encode("teste"), true);
		User user3 = new User(null, "basic2@mhc.dev.br", pe.encode("teste"), true);
		
		user1.addProfile(Profile.ADMIN);
		
		userRepository.saveAll(Arrays.asList(user1, user2, user3));
	}

}
