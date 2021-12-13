package br.dev.mhc.agendei.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.dev.mhc.agendei.dto.UserDTO;
import br.dev.mhc.agendei.dto.UserNewDTO;
import br.dev.mhc.agendei.entities.User;
import br.dev.mhc.agendei.repositories.UserRepository;
import br.dev.mhc.agendei.services.exceptions.ObjectNotFoundException;
import br.dev.mhc.agendei.services.interfaces.CrudInterface;

@Service
public class UserService implements CrudInterface<User, Long> {

	@Autowired
	private UserRepository repository;
	
	@Autowired
	private BCryptPasswordEncoder pe;

	@Override
	public User insert(User obj) {
		obj.setId(null);
		return repository.save(obj);
	}

	@Override
	public User update(User obj) {
		User newObj = findById(obj.getId());
		updateData(newObj, obj);
		return repository.save(newObj);
	}

	@Override
	public void delete(Long id) {
		findById(id);
		repository.deleteById(id);
	}

	@Override
	public User findById(Long id) {
		Optional<User> obj = repository.findById(id);
		return obj.orElseThrow(
				() -> new ObjectNotFoundException("Object not found! Id: " + id + ", Type: " + User.class.getName()));
	}

	@Override
	public List<User> findAll() {
		return repository.findAll();
	}

	private void updateData(User newObj, User obj) {
		newObj.setUsername(obj.getUsername());
		newObj.setPassword(obj.getPassword());
	}
	
	public User updatePassword(User user) {
		User newObj = findById(user.getId());
		newObj.setPassword(pe.encode(user.getPassword()));
		return repository.save(newObj);
	}

	public User fromDTO(UserDTO objDTO) {
		User user = new User(objDTO.getId(), objDTO.getUsername(), null, objDTO.isActive());
		return user;
	}
	
	public User fromDTO(UserNewDTO objDTO) {
		return new User(null, objDTO.getUsername(), pe.encode(objDTO.getPassword()), true);
	}
	
	public UserDTO toDTO(User user) {
		return new UserDTO(user.getId(), user.getUsername(), user.isActive(), user.getProfiles());
	}

}
