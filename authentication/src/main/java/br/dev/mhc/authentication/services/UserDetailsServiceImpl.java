package br.dev.mhc.authentication.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.dev.mhc.authentication.entities.User;
import br.dev.mhc.authentication.repositories.UserRepository;
import br.dev.mhc.authentication.security.UserSS;

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
