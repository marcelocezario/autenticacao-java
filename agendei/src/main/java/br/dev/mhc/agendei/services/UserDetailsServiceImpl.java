package br.dev.mhc.agendei.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.dev.mhc.agendei.entities.User;
import br.dev.mhc.agendei.repositories.UserRepository;
import br.dev.mhc.agendei.security.UserSS;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = repository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));

		return new UserSS(user.getId(), user.getUsername(), user.getPassword(), user.getProfiles());
	}

}
