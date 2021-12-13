package br.dev.mhc.agendei.services;

import java.text.ParseException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.dev.mhc.agendei.entities.User;
import br.dev.mhc.agendei.entities.enums.Profile;
import br.dev.mhc.agendei.repositories.UserRepository;

@Service
public class DBService {
	
	@Autowired
	private UserRepository userRepository;
	
	public void databaseSeeding() throws ParseException {
		
		User user1 = new User(null, "admin@mhc.dev.br", "teste", true);
		User user2 = new User(null, "teste@teste.com.br", "teste", true);
		
		user1.addProfile(Profile.ADMIN);
		
		userRepository.saveAll(Arrays.asList(user1, user2));
	}

}