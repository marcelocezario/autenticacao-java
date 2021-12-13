package br.dev.mhc.agendei.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.dev.mhc.agendei.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
