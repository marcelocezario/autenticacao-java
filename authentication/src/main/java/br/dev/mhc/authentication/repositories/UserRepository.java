package br.dev.mhc.authentication.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.dev.mhc.authentication.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	@Transactional(readOnly=true)
	Optional<User> findByUsername(String username);

}
